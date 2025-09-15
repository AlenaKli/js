package com.example.lab3.model.impl;

import com.example.lab3.model.Figure;

public class ScienceFictionBook implements Figure {
    private String title;
    private String author;

    @Override
    public String getDescription() {
        return "A science fiction book titled '" + title + "' by " + author;
    }

    @Override
    public String getCategory() {
        return "Science Fiction";
    }

    // Getters and setters
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
}