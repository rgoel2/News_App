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
    private String url;



    public NewsItem(String url,String title, String description, String imageUrl, String author, String publishedAt) {
        setUrl(url);
        setTitle(title);
        setDescription(description);
        setImageUrl(imageUrl);
        setAuthor(author);
        setPublishedAt(publishedAt);
    }

    public String getUrl() {return url;}

    public void setUrl(String url) {this.url = url;}

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
