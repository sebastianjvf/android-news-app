package com.example.android.newsapp;

import org.json.JSONArray;
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

        // Parse the given JSON and retrieve the array of news articles.
        JSONObject parsedJSON = new JSONObject();
        JSONArray newsArticleJSONArray = parsedJSON.optJSONArray("results");

        return newsArticles;
    }

}
