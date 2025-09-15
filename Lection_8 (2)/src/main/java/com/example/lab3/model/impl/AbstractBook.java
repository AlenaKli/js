package com.example.lab3.model.impl;

import com.example.lab3.model.Figure;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public abstract class AbstractBook implements Figure {
    private String title;
    private String author;
    private int pages;
    private Date publishDate;
    private boolean available = true;
    private List<String> tags = new ArrayList<>();

    // Default constructor
    public AbstractBook() {
    }

    // Constructor with title and author
    public AbstractBook(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // Initialization method called after properties are set
    @PostConstruct
    public void initialize() {
        System.out.println("Initializing book: " + title + " by " + author);
        if (publishDate == null) {
            publishDate = new Date(); // Default to current date
        }
    }

    // Destruction method called before bean is destroyed
    @PreDestroy
    public void cleanup() {
        System.out.println("Cleaning up book: " + title + " by " + author);
    }

    @Override
    public List<String> getTags() {
        return tags;
    }

    @Override
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Common getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}