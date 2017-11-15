package com.example.android.newsapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QueryUtils {

    /**
     * Empty constructor to prohibit any instances from being created.
     */
    public QueryUtils() {
    }

    protected static ArrayList<NewsArticle> extractNewsArticles(String JSON) {
        ArrayList<NewsArticle> newsArticles = new ArrayList<NewsArticle>();

        try {
            // Parse the given JSON and retrieve the array of news articles.
            JSONObject parsedJSON = (new JSONObject(JSON)).optJSONObject("response");

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

}
