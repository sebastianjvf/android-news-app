package com.example.android.newsapp;

public class NewsArticle {

    private String title;
    private String date;
    private String author;
    private String url;

    public NewsArticle(String title, String date, String author, String url) {
        this.title = title;
        this.date = date;
        this.author = author;
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAuthor(String author) { this.author = author; }

    public void setUrl(String url) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {return author; }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Date: " + date + ", Author: " + author + ", URL: " + url;
    }
}
