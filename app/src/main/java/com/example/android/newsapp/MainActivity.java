package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsArticle>> {

    // The request url
    private static String REQUEST_URL = "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test";

    // Logging made cool
    public static String LOG_TAG = "NewsList";

    private ArrayList<NewsArticle> newsArticleList = new ArrayList<NewsArticle>();

    private static final int EARTHQUAKE_LOADER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start the loader with the callback functions in THIS class
        getLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this).forceLoad();
    }

    // What happens when the loader is created?
    @Override
    public Loader<List<NewsArticle>> onCreateLoader(int id, Bundle args) {
        return new EarthquakeTask(this, REQUEST_URL);
    }

    // What happens when the loader has finished loading?
    @Override
    public void onLoadFinished(Loader<List<NewsArticle>> loader, List<NewsArticle> data) {
        // Find the listView
        ListView newsList = (ListView) findViewById(R.id.list_view);
        newsArticleList = (ArrayList<NewsArticle>) data;

        // Create adapter and set it to the ListView
        NewsListAdapter adapter = new NewsListAdapter(getApplicationContext(), newsArticleList);
        newsList.setAdapter(adapter);

        // If news articles were loaded (= are not null/empty)
        if(data != null & !data.isEmpty()) {
            adapter.addAll(newsArticleList);
        }
    }

    // What happens when the loader is reset?
    @Override
    public void onLoaderReset(Loader<List<NewsArticle>> loader) {
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