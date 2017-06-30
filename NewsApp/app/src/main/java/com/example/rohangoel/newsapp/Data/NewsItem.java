package com.example.rohangoel.newsapp.Data;

/**
 * Created by rohangoel on 6/27/17.
 */

public class NewsItem {
    private String title;
    private String description;
    private String publishedAt;
    private String imageUrl;
    private String author;

    public NewsItem(String title, String description, String imageUrl, String author, String publishedAt) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.author=author;
        this.publishedAt=publishedAt;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
