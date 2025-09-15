package com.example.lab3.model;

import java.util.Date;
import java.util.List;

/**
 * Common contract for anything that can be “displayed” as a book-like figure.
 * <p>
 * Adding title / author / page-count accessors makes it possible to work
 * with {@code Figure} references directly (e.g. in controllers and templates)
 * without casting back to {@code AbstractBook}.
 */
public interface Figure {

    /* ---------- descriptive ---------- */
    String getDescription();
    String getCategory();

    /* ---------- core book metadata ---------- */
    String getTitle();
    void   setTitle(String title);

    String getAuthor();
    void   setAuthor(String author);

    int  getPages();
    void setPages(int pages);

    Date getPublishDate();
    void setPublishDate(Date publishDate);

    /* ---------- state / tags ---------- */
    List<String> getTags();
    void         setTags(List<String> tags);

    boolean isAvailable();
    void    setAvailable(boolean available);
}
