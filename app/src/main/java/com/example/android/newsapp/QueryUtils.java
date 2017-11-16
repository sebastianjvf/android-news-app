package com.example.android.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static com.example.android.newsapp.MainActivity.LOG_TAG;

public class QueryUtils {

    /**
     * Empty constructor to prohibit any instances from being created.
     */
    public QueryUtils() {
    }

    protected static ArrayList<NewsArticle> extractNewsArticles(String inputUrl) {
        ArrayList<NewsArticle> newsArticles = new ArrayList<NewsArticle>();

        try {
            // Parse the given JSON and retrieve the array of news articles.
            String JSONString = makeHttpRequest(inputUrl);
            JSONObject parsedJSON = new JSONObject(JSONString).optJSONObject("response");

            JSONArray newsArticleJSONArray = (JSONArray) parsedJSON.optJSONArray("results");

            for (int i = 0; i < newsArticleJSONArray.length(); i++) {
                // Create a new NewsArticle and add data to the list
                JSONObject currentItem = (JSONObject) newsArticleJSONArray.get(i);
                String title = currentItem.getString("webTitle");
                String date = currentItem.getString("webPublicationDate");
                String url = currentItem.getString("webUrl");

                NewsArticle currentNewsArticle = new NewsArticle(title, date, url);
                newsArticles.add(currentNewsArticle);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return newsArticles;
    }

    /**
     * Returns a JSON formatted String from the given url.
     *
     * @param urlString
     * @return
     */
    protected static String makeHttpRequest(String urlString) {
        URL url = null;

        // Convert to URL
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String jsonResponse = null;

        // If there was an error, return null.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return jsonResponse;
    }

    /**
     * Converts the InputStream into a String which contains the
     * whole JSON response from the server.
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            // Takes pure data as an input stream
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));

            // Provides meaning by converting the input stream to characters
            BufferedReader reader = new BufferedReader(inputStreamReader);

            // After one line was read, readLine() points to the next
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}