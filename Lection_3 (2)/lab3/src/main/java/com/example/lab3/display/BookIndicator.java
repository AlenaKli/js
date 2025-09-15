package com.example.lab3.display;

import com.example.lab3.model.Figure;

public class BookIndicator {
    private Figure book;

    public void displayBook() {
        System.out.println("Book Category: " + book.getCategory());
        System.out.println("Description: " + book.getDescription());
    }

    // Getter and setter
    public Figure getBook() {
        return book;
    }

    public void setBook(Figure book) {
        this.book = book;
    }
}
