package com.example.lab3.model;

import java.util.Date;
import java.util.List;

public interface Figure {
    String getDescription();
    String getCategory();
    List<String> getTags();
    void setTags(List<String> tags);
    boolean isAvailable();
    void setAvailable(boolean available);

    // Added publish date methods
    Date getPublishDate();
    void setPublishDate(Date publishDate);
}