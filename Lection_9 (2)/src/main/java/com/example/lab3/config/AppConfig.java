package com.example.lab3.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.lab3.model.impl.HorrorBook;
import com.example.lab3.model.impl.ScienceFictionBook;
import com.example.lab3.model.impl.StoryBook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = "com.example")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${story.book.ageGroup:Young Adult}")
    private String defaultAgeGroup;

    @Value("${default.book.available:true}")
    private boolean defaultAvailable;

    // Story Book bean - Singleton scope (default)
    @Bean
    public StoryBook storyBook() {
        StoryBook book = new StoryBook();
        book.setTitle("The Great Adventure");
        book.setAuthor("John Doe");
        book.setPages(256);
        book.setAgeGroup(defaultAgeGroup);
        book.setAvailable(defaultAvailable);

        List<String> tags = new ArrayList<>();
        tags.add("adventure");
        tags.add("coming-of-age");
        tags.add("fantasy");
        book.setTags(tags);

        book.setPublishDate(new Date(1577836800000L)); // Jan 1, 2020

        return book;
    }

    // Science Fiction Book bean - Prototype scope
    @Bean
    @Scope("prototype")
    public ScienceFictionBook scienceFictionBook() {
        ScienceFictionBook book = new ScienceFictionBook();
        book.setTitle("Space Odyssey");
        book.setAuthor("Jane Smith");
        book.setPages(422);
        book.setSubgenre("Space Opera");
        book.setAvailable(defaultAvailable);

        List<String> tags = new ArrayList<>();
        tags.add("space");
        tags.add("future");
        tags.add("technology");
        book.setTags(tags);

        Map<String, String> techTerms = new HashMap<>();
        techTerms.put("FTL", "Faster Than Light Travel");
        techTerms.put("AI", "Artificial Intelligence");
        techTerms.put("Terraform", "Process of making a planet habitable");
        book.setTechTerms(techTerms);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            book.setPublishDate(sdf.parse("2022-05-15"));
        } catch (Exception e) {
            book.setPublishDate(new Date());
        }

        return book;
    }

    // Alternate Science Fiction Book - For demonstrating @Qualifier
    @Bean
    public ScienceFictionBook alternateSciFiBook() {
        ScienceFictionBook book = new ScienceFictionBook();
        book.setTitle("Cybernetic Revolution");
        book.setAuthor("Robert Chen");
        book.setPages(368);
        book.setSubgenre("Cyberpunk");
        book.setAvailable(true);

        List<String> tags = new ArrayList<>();
        tags.add("cyberpunk");
        tags.add("dystopia");
        tags.add("artificial-intelligence");
        book.setTags(tags);

        Map<String, String> techTerms = new HashMap<>();
        techTerms.put("Neural Interface", "Direct brain-computer connection");
        techTerms.put("Cyberspace", "Virtual reality network");
        book.setTechTerms(techTerms);

        book.setPublishDate(new Date());

        return book;
    }

    // Horror Book bean
    @Bean
    public HorrorBook horrorBook() {
        HorrorBook book = new HorrorBook("Midnight Terror", "Mike Johnson");
        book.setPages(318);
        book.setScareRating(8);
        book.setContainsSupernaturalElements(true);
        book.setAvailable(false);

        List<String> tags = new ArrayList<>();
        tags.add("horror");
        tags.add("supernatural");
        tags.add("thriller");
        book.setTags(tags);

        book.setPublishDate(new Date(1609459200000L)); // Jan 1, 2021

        return book;
    }

    // Date formatter bean
    @Bean
    public SimpleDateFormat dateFormatter() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }
}