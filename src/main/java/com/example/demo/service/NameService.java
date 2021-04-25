package com.example.demo.service;

public class NameService {

    public String fullName(String firstName, String lastName) {
        if (firstName == null || firstName.isBlank())
            throw new RuntimeException("invalid");
        if (lastName == null || lastName.isBlank())
            throw new RuntimeException("invalid");
        return firstName + "" + lastName;
    }


}
