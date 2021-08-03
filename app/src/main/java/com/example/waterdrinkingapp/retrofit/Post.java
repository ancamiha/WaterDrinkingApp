package com.example.waterdrinkingapp.retrofit;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("quote")
    private String quote;

    @SerializedName("author")
    private String author;

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
