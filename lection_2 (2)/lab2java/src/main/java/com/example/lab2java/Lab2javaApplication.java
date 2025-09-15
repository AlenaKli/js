package com.example.lab2java;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Lab2javaApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ContextApplication.xml");

        PersonBean person = (PersonBean) context.getBean("personBean");

        System.out.println(person);
    }
}
