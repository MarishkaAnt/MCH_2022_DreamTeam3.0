package com.dreamteam3.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    String name;
    String description;
    float price;
    File image;

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Product(String name, float price, File image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Product(String name, String description, File image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Product(String name, String description, float price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
