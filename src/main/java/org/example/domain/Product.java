package org.example.domain;

public class Product {
    private int id;
    private float price;
    private String name;
    private String description;

    public Product(int id, float price, String name, String description) {
        this(price, name, description);
        this.id = id;
    }

    public Product(float price, String name, String description) {
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
