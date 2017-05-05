package com.example.tuterdust.e_books;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuterdust on 4/5/2560.
 */

public class User implements Serializable{

    String name;
    List<Book> ownBook = new ArrayList<>();
    int fund;

    public User(String name) {
        this.name = name;
        fund = 0;
    }

}
