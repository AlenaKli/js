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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    /* ---------- NEW GENERIC ROUTES ---------- */

/** List view of every Figure produced by BookIndicator. */
@GetMapping("/books")
public String listBooks(Model model) {
Map<String, Figure> books = bookIndicator.getAvailableBooks();

// prettified publish dates
Map<String, String> formatted = new HashMap<>();
books.forEach((k, v) -> formatted.put(k, formatDate(v.getPublishDate())));

model.addAttribute("books", books);
model.addAttribute("formattedDates", formatted);
model.addAttribute("displayName", bookIndicator.getDisplayName());
return "all-books";     // template already exists
}
/** Single-item view addressed by its map key (e.g. /books/MainSciFi). */
@GetMapping("/books/{key}")
String bookDetails(@PathVariable String key, Model model) {
Figure book = bookIndicator.findByKey(key);

if (book == null) {
// unknown key → send the user back to the list
return "redirect:/books";
}

model.addAttribute("book", book);
model.addAttribute("publishDate", formatDate(book.getPublishDate()));
model.addAttribute("bookType", key + " Book");
return "book-details";
}


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

    @GetMapping("/calculate")
    public String calculate(
            @RequestParam(name = "num1", defaultValue = "0") double num1,
            @RequestParam(name = "num2", defaultValue = "0") double num2,
            @RequestParam(name = "operation", defaultValue = "add") String operation,
            Model model) {

        double result = 0;
        String operationSymbol = "";

        switch(operation.toLowerCase()) {
            case "add":
                result = num1 + num2;
                operationSymbol = "+";
                break;
            case "subtract":
                result = num1 - num2;
                operationSymbol = "-";
                break;
            case "multiply":
                result = num1 * num2;
                operationSymbol = "*";
                break;
            case "divide":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    model.addAttribute("error", "Cannot divide by zero");
                }
                operationSymbol = "/";
                break;
            default:
                model.addAttribute("error", "Invalid operation: " + operation);
                operationSymbol = "?";
        }

        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("operation", operation);
        model.addAttribute("operationSymbol", operationSymbol);
        model.addAttribute("result", result);

        return "calculator-result";
    }
}