package com.spring.library.domain;


import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "genre")
public class Genre {


    private String id;


    String name;



    public Genre() {
    }



    public Genre(String id, String name) {
        this.id = id;
        this.name = name;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
