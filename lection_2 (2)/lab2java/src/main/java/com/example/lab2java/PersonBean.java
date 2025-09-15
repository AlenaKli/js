package com.example.lab2java;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonBean {
    private String firstName;
    private String lastName;
    private SimpleDateFormat dateOfBirth;

    // Constructor
    public PersonBean(String firstName, String lastName, SimpleDateFormat dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    // toString method to display bean data
    @Override
    public String toString() {
        return "PersonBean{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth.format(new Date()) +
                '}';
    }
}
