package com.example.android.newsapp;

public class NewsArticle {

    String title;
    String date;

    public NewsArticle(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}
