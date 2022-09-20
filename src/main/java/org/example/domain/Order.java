package org.example.domain;

import java.time.LocalDate;

public class Order {
    private int id;
    private String number;
    private LocalDate date;

    public Order(LocalDate date, String number) {
        this.date = date;
        this.number = number;
    }

    public Order(int id, String number, LocalDate date) {
        this(date, number);
        this.id = id;
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
}