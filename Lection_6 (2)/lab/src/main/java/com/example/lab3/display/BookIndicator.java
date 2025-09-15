package com.example.lab3.display;

import com.example.lab3.model.Figure;
import com.example.lab3.model.impl.ScienceFictionBook;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Component
public class BookIndicator {

    // Using @Autowired on field
    @Autowired
    @Qualifier("storyBook")  // Specify which bean to use
    private Figure book;

    // Multiple ScienceFictionBooks defined - using @Qualifier to resolve
    @Autowired
    @Qualifier("alternateSciFiBook")
    private ScienceFictionBook sciFiBook;

    @Value("${app.name:Book Library}")
    private String displayName;

    private Map<String, Figure> availableBooks = new HashMap<>();

    // Autowiring a date formatter
    @Autowired
    private SimpleDateFormat dateFormatter;

    // Constructor injection with @Autowired
    @Autowired
    public BookIndicator(@Qualifier("horrorBook") Figure horrorBook) {
        availableBooks.put("Horror", horrorBook);
    }

    // Method injection with @Autowired
    @Autowired
    public void setAvailableSciFiBooks(
            @Qualifier("scienceFictionBook") ScienceFictionBook mainSciFi,
            @Qualifier("alternateSciFiBook") ScienceFictionBook altSciFi) {
        availableBooks.put("MainSciFi", mainSciFi);
        availableBooks.put("AltSciFi", altSciFi);
    }

    // Init method using @PostConstruct
    @PostConstruct
    public void setup() {
        System.out.println("Setting up BookIndicator with display name: " + displayName);
        System.out.println("Number of available books: " + availableBooks.size());

        // Add the main book if not already in the map
        if (!availableBooks.containsValue(book)) {
            availableBooks.put("Main", book);
        }
    }

    // Destroy method using @PreDestroy
    @PreDestroy
    public void teardown() {
        System.out.println("Tearing down BookIndicator");
        availableBooks.clear();
    }

    public void displayBook() {
        System.out.println("-----------------------------------");
        System.out.println("Display Name: " + displayName);
        System.out.println("Book Category: " + book.getCategory());
        System.out.println("Description: " + book.getDescription());
        System.out.println("Available: " + (book.isAvailable() ? "Yes" : "No"));
        System.out.println("Tags: " + String.join(", ", book.getTags()));

        if (dateFormatter != null && book.getPublishDate() != null) {
            System.out.println("Formatted Publish Date: " +
                    dateFormatter.format(book.getPublishDate()));
        }

        System.out.println("-----------------------------------");
    }

    public void displaySciFiBook() {
        System.out.println("-----------------------------------");
        System.out.println("Science Fiction Book: " + sciFiBook.getTitle());
        System.out.println("Subgenre: " + sciFiBook.getSubgenre());
        System.out.println("Description: " + sciFiBook.getDescription());
        System.out.println("-----------------------------------");
    }

    public void displayAllBooks() {
        System.out.println("===== Available Books =====");
        for (Map.Entry<String, Figure> entry : availableBooks.entrySet()) {
            Figure book = entry.getValue();
            System.out.println(entry.getKey() + ": " + book.getDescription());

            if (dateFormatter != null && book.getPublishDate() != null) {
                System.out.println("  Published: " + dateFormatter.format(book.getPublishDate()));
            }
        }
        System.out.println("==========================");
    }

    // Getters and setters
    public Figure getBook() {
        return book;
    }

    public void setBook(Figure book) {
        this.book = book;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Map<String, Figure> getAvailableBooks() {
        return availableBooks;
    }

    public void setAvailableBooks(Map<String, Figure> availableBooks) {
        this.availableBooks = availableBooks;
    }
}