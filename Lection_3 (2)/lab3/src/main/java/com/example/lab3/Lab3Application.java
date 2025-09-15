package com.example.lab3;

import com.example.lab3.display.BookIndicator;
import com.example.lab3.model.Figure;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Lab3Application {
    public static void main(String[] args) {
        // Load the Spring XML configuration
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Get the BookIndicator bean
        BookIndicator bookIndicator = context.getBean("bookIndicator", BookIndicator.class);

        // Display the book details
        bookIndicator.displayBook();

        // Change the book to a different category
        bookIndicator.setBook((Figure) context.getBean("scienceFictionBook"));
        bookIndicator.displayBook();

        // Close the application context
        context.close();
    }
}