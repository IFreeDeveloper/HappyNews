package com.example.john.util;

import java.util.List;

public class TopHeadlinesBean {
    /**
     * status : ok
     * totalResults : 10
     * articles : [{"source":{"id":"bbc-news","name":"BBC News"},"author":"BBC News","title":"Huawei sues US government over product ban","description":"The Chinese firm is facing international scrutiny tied to concerns over the security of its products.","url":"http://www.bbc.co.uk/news/business-47478587","urlToImage":"https://ichef.bbci.co.uk/news/1024/branded_news/8F0E/production/_105922663_gettyimages-1091456120-1.jpg","publishedAt":"2019-03-07T02:18:27Z","content":"Image copyrightGetty Images\r\nHuawei has filed a lawsuit against the US government over a ban that restricts government agencies from using its products.\r\nIn a statement, the firm said the US Congress has \"failed to produce any evidence to support its restrict\u2026 [+937 chars]"},{"source":{"id":"bbc-news","name":"BBC News"},"author":"BBC News","title":"Trump dealt blow as trade deficit jumps","description":"The gap between US imports and exports has widened despite the president's pledge to narrow it.","url":"http://www.bbc.co.uk/news/business-47472282","urlToImage":"https://ichef.bbci.co.uk/news/1024/branded_news/1483/production/_105915250_trump.jpg","publishedAt":"2019-03-06T13:42:55Z","content":"Image copyrightGetty Images\r\nThe US trade gap with the rest of the world widened to $621bn (£472.5bn) last year, dealing a blow to President Donald Trump's deficit reduction plan.\r\nThe trade deficit is the difference between how much goods and services the US\u2026 [+477 chars]"}]
     */

    private String status;
    private int totalResults;
    private List<ArticlesBean> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticlesBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesBean> articles) {
        this.articles = articles;
    }
}

