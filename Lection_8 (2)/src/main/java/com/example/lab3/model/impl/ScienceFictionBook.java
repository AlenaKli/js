package com.example.lab3.model.impl;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ScienceFictionBook extends AbstractBook {
    private String subgenre;
    private Map<String, String> techTerms = new HashMap<>();

    @Override
    public String getDescription() {
        return "A " + subgenre + " science fiction book titled '" + getTitle()
                + "' by " + getAuthor() + ". Pages: " + getPages()
                + ", Published: " + getPublishDate()
                + ", Contains " + techTerms.size() + " tech terms.";
    }

    @Override
    public String getCategory() {
        return "Science Fiction";
    }

    // Getters and setters
    public String getSubgenre() {
        return subgenre;
    }

    public void setSubgenre(String subgenre) {
        this.subgenre = subgenre;
    }

    public Map<String, String> getTechTerms() {
        return techTerms;
    }

    public void setTechTerms(Map<String, String> techTerms) {
        this.techTerms = techTerms;
    }
}