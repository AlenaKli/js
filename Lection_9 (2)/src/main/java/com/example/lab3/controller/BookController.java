package com.example.lab3.controller;

import com.example.lab3.display.BookIndicator;
import com.example.lab3.dto.BookForm;
import com.example.lab3.model.Figure;
import com.example.lab3.model.impl.HorrorBook;
import com.example.lab3.model.impl.ScienceFictionBook;
import com.example.lab3.model.impl.StoryBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles page navigation and book-related operations.
 */
@Controller
public class BookController {

    /* -------------------------------------------------
     *  Injected beans
     * ------------------------------------------------- */
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

    /* -------------------------------------------------
     *  Helper
     * ------------------------------------------------- */
    private String formatDate(java.util.Date date) {
        return date == null ? "" : dateFormatter.format(date);
    }

    /* -------------------------------------------------
     *  Generic list & detail routes
     * ------------------------------------------------- */

    /** Master list of every book held by {@link BookIndicator}. */
    @GetMapping("/books")
    public String listBooks(Model model) {
        Map<String, Figure> books = bookIndicator.getAvailableBooks();

        Map<String, String> formatted = new HashMap<>();
        books.forEach((k, v) -> formatted.put(k, formatDate(v.getPublishDate())));

        model.addAttribute("books", books);
        model.addAttribute("formattedDates", formatted);
        model.addAttribute("displayName", bookIndicator.getDisplayName());
        return "all-books";
    }

    /** Detail page addressed by its map key, e.g. <code>/books/MainSciFi</code>. */
    @GetMapping("/books/{key}")
    public String bookDetails(@PathVariable String key, Model model) {
        Figure book = bookIndicator.findByKey(key);
        if (book == null) {
            return "redirect:/books";
        }
        model.addAttribute("book", book);
        model.addAttribute("publishDate", formatDate(book.getPublishDate()));
        model.addAttribute("bookType", key + " Book");
        return "book-details";
    }

    /* -------------------------------------------------
     *  “Add book” workflow
     * ------------------------------------------------- */

    /** Form page. */
    @GetMapping("/books/new")
    public String newBookForm(Model model) {
        model.addAttribute("bookForm", new BookForm());
        return "add-book";
    }

    /** Handles form submission and persists the new book (in-memory). */
    @PostMapping("/books/new")
    public String createBook(@ModelAttribute("bookForm") BookForm form) {
        // Choose concrete class
        Figure book;
        switch (form.getType() == null ? "" : form.getType().toUpperCase()) {
            case "SCIFI" -> {
                ScienceFictionBook b = new ScienceFictionBook();
                b.setSubgenre(form.getSubgenre());
                book = b;
            }
            case "HORROR" -> {
                HorrorBook b = new HorrorBook();
                b.setScareRating(form.getScareRating() == null ? 0 : form.getScareRating());
                b.setContainsSupernaturalElements(
                        form.getContainsSupernatural() != null && form.getContainsSupernatural());
                book = b;
            }
            default -> {
                StoryBook b = new StoryBook();
                b.setAgeGroup(form.getAgeGroup());
                book = b;
            }
        }

        // Common fields
        book.setTitle(form.getTitle());
        book.setAuthor(form.getAuthor());
        if (form.getPages() != null) book.setPages(form.getPages());

        try {
            if (form.getPublishDate() != null && !form.getPublishDate().isBlank()) {
                book.setPublishDate(dateFormatter.parse(form.getPublishDate()));
            }
        } catch (Exception ignored) { }

        if (form.getTags() != null && !form.getTags().isBlank()) {
            book.setTags(
                    java.util.Arrays.stream(form.getTags().split(","))
                            .map(String::trim)
                            .toList());
        }

        // Store in BookIndicator
        String key = (form.getKey() == null || form.getKey().isBlank())
                ? ("User" + System.currentTimeMillis())
                : form.getKey();
        bookIndicator.addBook(key, book);

        // Redirect to its detail page
        return "redirect:/books/" + key;
    }

    /* -------------------------------------------------
     *  Legacy route – keep for backward compatibility
     * ------------------------------------------------- */
    @GetMapping("/all")
    public String legacyAll() {
        return "redirect:/books";
    }

    /* -------------------------------------------------
     *  Original specific pages (unchanged)
     * ------------------------------------------------- */

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("bookIndicator", bookIndicator);
        model.addAttribute("storyBook", storyBook);
        model.addAttribute("sciFiBook", sciFiBook);
        model.addAttribute("alternateSciFiBook", alternateSciFiBook);
        model.addAttribute("horrorBook", horrorBook);

        model.addAttribute("storyBookDate", formatDate(storyBook.getPublishDate()));
        model.addAttribute("sciFiBookDate", formatDate(sciFiBook.getPublishDate()));
        model.addAttribute("alternateSciFiBookDate", formatDate(alternateSciFiBook.getPublishDate()));
        model.addAttribute("horrorBookDate", formatDate(horrorBook.getPublishDate()));

        return "home";
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

    /* -------------------------------------------------
     *  Calculator (unchanged)
     * ------------------------------------------------- */

    @GetMapping("/calculate")
    public String calculate(@RequestParam(name = "num1", defaultValue = "0") double num1,
                            @RequestParam(name = "num2", defaultValue = "0") double num2,
                            @RequestParam(name = "operation", defaultValue = "add") String operation,
                            Model model) {

        double result = 0;
        String operationSymbol = "";

        switch (operation.toLowerCase()) {
            case "add" -> {
                result = num1 + num2;
                operationSymbol = "+";
            }
            case "subtract" -> {
                result = num1 - num2;
                operationSymbol = "-";
            }
            case "multiply" -> {
                result = num1 * num2;
                operationSymbol = "*";
            }
            case "divide" -> {
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    model.addAttribute("error", "Cannot divide by zero");
                }
                operationSymbol = "/";
            }
            default -> {
                model.addAttribute("error", "Invalid operation: " + operation);
                operationSymbol = "?";
            }
        }

        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("operation", operation);
        model.addAttribute("operationSymbol", operationSymbol);
        model.addAttribute("result", result);

        return "calculator-result";
    }
}
