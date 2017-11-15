package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // The request url
    private static String REQUEST_URL = "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test";

    // Logging made cool
    public static String LOG_TAG = "NewsList";

    private ArrayList<NewsArticle> newsArticleList = new ArrayList<NewsArticle>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the listView
        ListView newsList = (ListView) findViewById(R.id.list_view);

        // Create adapter and set it to the ListView
        newsList.setAdapter(new NewsListAdapter(getApplicationContext(), newsArticleList));

    }

    private static class EarthquakeTask extends AsyncTaskLoader<List<NewsArticle>> {

        String url;

        /**
         * Takes a string as input as the URL should stay modifiable.
         * @param context
         * @param url
         */
        public EarthquakeTask(Context context, String url) {
            super(context);
            this.url = url;
        }

        @Override
        public List<NewsArticle> loadInBackground() {
            // Download JSON data from API
            ArrayList<NewsArticle> newsArticles = QueryUtils.extractNewsArticles(url);
            return newsArticles;
        }
    }
}