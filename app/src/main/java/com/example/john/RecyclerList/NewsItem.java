package com.example.john.RecyclerList;

public class NewsItem {
    private String imageURL="";
    private String newsURL="";
    private String title="";
    private String source="";
    private String username="";
    private String time = "";
    public NewsItem(String imageURL, String newsURL, String title, String source,String time) {
        this.imageURL = imageURL;
        this.newsURL = newsURL;
        this.title = title;
        this.source = source;
        this.time = time;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getNewsURL() {
        return newsURL;
    }

    public void setNewsURL(String newsURL) {
        this.newsURL = newsURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
