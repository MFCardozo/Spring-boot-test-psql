package com.example.demo.model;

import javax.validation.constraints.NotBlank;

public class Album {

    private final Long id;

    @NotBlank
    private final String title;
    @NotBlank
    private final String year;


    public Album (Long id, String title, String year){

        this.id = id;
        this.year = year;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }
}
