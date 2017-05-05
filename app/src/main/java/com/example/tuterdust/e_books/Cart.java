package com.example.tuterdust.e_books;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuterdust on 4/5/2560.
 */

public class Cart {

    private List<Book> inCartBook = new ArrayList<>();
    private User user;

    public Cart(User user) {
        this.user = user;
    }

    public void add(Book book) {
        inCartBook.add(book);
    }

    public void remove(int index) {
        inCartBook.remove(index);
    }

    public void checkout() {

    }

    public void clear() {
        inCartBook.clear();
    }
}
