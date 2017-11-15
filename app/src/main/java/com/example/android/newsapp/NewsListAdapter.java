package com.example.android.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

public class NewsListAdapter extends ArrayAdapter<NewsArticle> {
    public NewsListAdapter(@NonNull Context context, int resource, @NonNull List<NewsArticle> objects) {
        super(context, resource, objects);
    }
}
