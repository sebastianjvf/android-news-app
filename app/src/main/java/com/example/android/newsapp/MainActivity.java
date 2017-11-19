package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsArticle>> {

    // The request url
    private static String REQUEST_URL = "https://content.guardianapis.com/search"; // ?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test"

    // Logging made cool
    public static String LOG_TAG = "NewsList";

    private ArrayList<NewsArticle> newsArticleData = new ArrayList<NewsArticle>();

    private NewsListAdapter adapter;

    private static final int EARTHQUAKE_LOADER_ID = 0;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Show progress bar
        progressBar = (ProgressBar) findViewById(R.id.loading);
        progressBar.setVisibility(View.VISIBLE);

        // Check internet connection
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        TextView noConnection = ((TextView) findViewById(R.id.no_connection));

        if (isConnected) {
            // Start the loader with the callback functions in THIS class
            getLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this).forceLoad();
            noConnection.setVisibility(View.GONE);
        } else {
            // Hide the no connection TextView
            noConnection.setText(R.string.not_connected_to_the_internet);
            noConnection.setVisibility(View.VISIBLE);
        }
    }

    @Override
    // This method initialize the contents of the Activity's options menu.
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the Options Menu we specified in XML
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    // What happens when any menu item is clicked
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Id defined in the menu layout file
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // What happens when the loader is created?
    @Override
    public Loader<List<NewsArticle>> onCreateLoader(int id, Bundle args) {
        // Created shared preferences and load the values from the settings screen
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String minYear = sharedPreferences.getString(getString(R.string.settings_min_year_key), getString(R.string.settings_min_year_value));
        String tag = sharedPreferences.getString(getString(R.string.settings_view_category_key), getString(R.string.settings_view_category_default));

        // Parse the base URL to a URI
        Uri baseUri = Uri.parse(REQUEST_URL);

        // Prepare for adding
        Uri.Builder uriBuilder = baseUri.buildUpon();

        // Append the parameters
        uriBuilder.appendQueryParameter("q", "debate");
        uriBuilder.appendQueryParameter("from-date", minYear + "-01-01");
        uriBuilder.appendQueryParameter("tag", tag);
        uriBuilder.appendQueryParameter("api-key", "test");

        // Load from the new URL
        return new EarthquakeTask(this, uriBuilder.toString());
    }

    // What happens when the loader has finished loading?
    @Override
    public void onLoadFinished(Loader<List<NewsArticle>> loader, List<NewsArticle> data) {
        // Hide the progress bar - loading is finished!
        progressBar.setVisibility(View.GONE);

        // Find the listView
        ListView newsListView = (ListView) findViewById(R.id.list_view);
        newsArticleData = (ArrayList<NewsArticle>) data;

        // Create adapter and set it to the ListView
        adapter = new NewsListAdapter(getApplicationContext(), newsArticleData);
        newsListView.setAdapter(adapter);

        TextView noArticles = (TextView) findViewById(R.id.no_articles);

        // If news articles were loaded (= are not null/empty)
        if (data != null & !data.isEmpty()) {
            adapter.addAll(newsArticleData);
            noArticles.setVisibility(View.GONE);
        } else {
            // Show appropriate message: no articles available
            noArticles.setText(R.string.no_articles_available);
            noArticles.setVisibility(View.VISIBLE);
        }
    }

    // What happens when the loader is reset?
    @Override
    public void onLoaderReset(Loader<List<NewsArticle>> loader) {
        adapter.clear();
    }

    private static class EarthquakeTask extends AsyncTaskLoader<List<NewsArticle>> {

        String url;

        /**
         * Takes a string as input as the URL should stay modifiable.
         *
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