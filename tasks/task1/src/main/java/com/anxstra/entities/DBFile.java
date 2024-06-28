package com.anxstra.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DBFile {

    private List<User> users;

    private List<Credit> credits;

    private List<Discount> discounts;

    private List<Event> events;

    private List<Transaction> transactions;

    public DBFile() {
        users = new ArrayList<>();
        credits = new ArrayList<>();
        discounts = new ArrayList<>();
        events = new ArrayList<>();
        transactions = new ArrayList<>();
    }
}
