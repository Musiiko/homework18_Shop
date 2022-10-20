package org.example.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private int id;
    private String number;
    private LocalDate date;

    private List<Product> products;

    public Order(LocalDate date, String number) {
        this.date = date;
        this.number = number;
        products = new ArrayList<>();
    }

    public Order(int id, String number, LocalDate date) {
        this(date, number);
        this.id = id;
        products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", date=" + date +
                ", products=" + products +
                '}';
    }

}