package com.example.lab3.controller;

import com.example.lab3.display.BookIndicator;
import com.example.lab3.model.Figure;
import com.example.lab3.model.impl.HorrorBook;
import com.example.lab3.model.impl.ScienceFictionBook;
import com.example.lab3.model.impl.StoryBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BookController {

    @Autowired
    private BookIndicator bookIndicator;

    @Autowired
    @Qualifier("storyBook")
    private Figure storyBook;

    @Autowired
    @Qualifier("scienceFictionBook")
    private ScienceFictionBook sciFiBook;

    @Autowired
    @Qualifier("alternateSciFiBook")
    private ScienceFictionBook alternateSciFiBook;

    @Autowired
    @Qualifier("horrorBook")
    private HorrorBook horrorBook;

    @Autowired
    private SimpleDateFormat dateFormatter;

    @GetMapping("/")
    public String home(Model model) {
        // Add all book beans to the model
        model.addAttribute("bookIndicator", bookIndicator);
        model.addAttribute("storyBook", storyBook);
        model.addAttribute("sciFiBook", sciFiBook);
        model.addAttribute("alternateSciFiBook", alternateSciFiBook);
        model.addAttribute("horrorBook", horrorBook);

        // Format and add dates
        model.addAttribute("storyBookDate", formatDate(storyBook.getPublishDate()));
        model.addAttribute("sciFiBookDate", formatDate(sciFiBook.getPublishDate()));
        model.addAttribute("alternateSciFiBookDate", formatDate(alternateSciFiBook.getPublishDate()));
        model.addAttribute("horrorBookDate", formatDate(horrorBook.getPublishDate()));

        return "home";
    }

    // Helper method to format dates
    private String formatDate(java.util.Date date) {
        if (date == null) return "";
        return dateFormatter.format(date);
    }

    @GetMapping("/story")
    public String storyBookDetails(Model model) {
        model.addAttribute("book", storyBook);
        model.addAttribute("publishDate", formatDate(storyBook.getPublishDate()));
        model.addAttribute("bookType", "Story Book");
        return "book-details";
    }

    @GetMapping("/scifi")
    public String sciFiBookDetails(Model model) {
        model.addAttribute("book", sciFiBook);
        model.addAttribute("publishDate", formatDate(sciFiBook.getPublishDate()));
        model.addAttribute("bookType", "Science Fiction Book");
        return "book-details";
    }

    @GetMapping("/scifi-alt")
    public String alternateSciFiBookDetails(Model model) {
        model.addAttribute("book", alternateSciFiBook);
        model.addAttribute("publishDate", formatDate(alternateSciFiBook.getPublishDate()));
        model.addAttribute("bookType", "Alternate Science Fiction Book");
        return "book-details";
    }

    @GetMapping("/horror")
    public String horrorBookDetails(Model model) {
        model.addAttribute("book", horrorBook);
        model.addAttribute("publishDate", formatDate(horrorBook.getPublishDate()));
        model.addAttribute("bookType", "Horror Book");
        return "book-details";
    }

    @GetMapping("/all")
    public String allBooks(Model model) {
        model.addAttribute("bookIndicator", bookIndicator);

        // Format dates for all books in the BookIndicator
        Map<String, String> formattedDates = new HashMap<>();
        for (Map.Entry<String, Figure> entry : bookIndicator.getAvailableBooks().entrySet()) {
            formattedDates.put(entry.getKey(), formatDate(entry.getValue().getPublishDate()));
        }
        model.addAttribute("formattedDates", formattedDates);

        return "all-books";
    }
}