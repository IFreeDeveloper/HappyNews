package com.example.john.util;

import com.example.john.util.SourceBean;

public class ArticlesBean {
    /**
     * source : {"id":"bbc-news","name":"BBC News"}
     * author : BBC News
     * title : Huawei sues US government over product ban
     * description : The Chinese firm is facing international scrutiny tied to concerns over the security of its products.
     * url : http://www.bbc.co.uk/news/business-47478587
     * urlToImage : https://ichef.bbci.co.uk/news/1024/branded_news/8F0E/production/_105922663_gettyimages-1091456120-1.jpg
     * publishedAt : 2019-03-07T02:18:27Z
     * content : Image copyrightGetty Images
     * Huawei has filed a lawsuit against the US government over a ban that restricts government agencies from using its products.
     * In a statement, the firm said the US Congress has "failed to produce any evidence to support its restrict… [+937 chars]
     */

    private SourceBean source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    public SourceBean getSource() {
        return source;
    }

    public void setSource(SourceBean source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
