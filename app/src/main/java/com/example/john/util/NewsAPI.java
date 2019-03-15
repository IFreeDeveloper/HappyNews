package com.example.john.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NewsAPI {
    private String topHeadlines = "https://newsapi.org/v2/top-headlines?";
    private String everything = "https://newsapi.org/v2/everything?";
    private String apiKey = "df04caece5ba4c48a7848f6611bc6659";
    private String country = "";
    private String category = "";
    private String sources = "";
    private String q = "";
    private String pageSize = "";
    private String page = "";
    private String domains = "";
    private String excludeDomains = "";
    private String from = "";
    private String to = "";
    private String language = "";
    private String sortBy = "";
    private String results = "";

    public NewsAPI(String country, String category, String sources, String q, String pageSize, String page) {
        this.country = country;
        this.category = category;
        this.sources = sources;
        this.q = q;
        this.pageSize = pageSize;
        this.page = page;
    }

    public NewsAPI(String sources, String q, String pageSize, String page, String domains, String excludeDomains, String from, String to, String language, String sortBy) {
        this.sources = sources;
        this.q = q;
        this.pageSize = pageSize;
        this.page = page;
        this.domains = domains;
        this.excludeDomains = excludeDomains;
        this.from = from;
        this.to = to;
        this.language = language;
        this.sortBy = sortBy;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getDomains() {
        return domains;
    }

    public void setDomains(String domains) {
        this.domains = domains;
    }

    public String getExcludeDomains() {
        return excludeDomains;
    }

    public void setExcludeDomains(String excludeDomains) {
        this.excludeDomains = excludeDomains;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getTopHeadlinesNews(){
        String URL = topHeadlines+
                "country="+country+"&"+
                "category="+category+"&"+
                "sources="+sources+"&"+
                "q="+q+"&"+
                "pageSize="+pageSize+"&"+
                "page="+page+"&"+
                "apiKey="+apiKey;
        return URL;
    }
    public String getEverythingNews(){
        String URL = everything+
                "q="+q+"&"+
                "sources="+sources+"&"+
                "domains="+domains+"&"+
                "excludeDomains="+excludeDomains+"&"+
                "from="+from+"&"+
                "to="+to+"&"+
                "language="+language+"&"+
                "sortBy="+sortBy+"&"+
                "pageSize="+pageSize+"&"+
                "page="+page+"&"+
                "apiKey="+apiKey;
        return URL;
    }
}
