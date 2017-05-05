package com.example.tuterdust.e_books;

import android.database.Observable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuterdust on 4/5/2560.
 */

public class Cart  implements Serializable{

    private List<Book> inCartBook = new ArrayList<>();
    private User user;
    private Presenter pr;

    public Cart(User user, Presenter presenter) {
        this.user = user;
        pr = presenter;
    }

    public void add(Book book) {
        inCartBook.add(book);
    }

    public void remove(Book book) {
        int index = -99;
        for(int i = 0; i < inCartBook.size(); i++) {
            if (inCartBook.get(i).getId() == book.getId())
                index = i;
        }
        if (index >=0)
            inCartBook.remove(index);
    }

    public void checkout() {
        if (getSumMoney() <= user.fund) {
            user.fund -= getSumMoney();
            user.addBooks(inCartBook);
            clear();
            System.out.println("Success");
            pr.setChanged();
        }
        else {
            System.out.println("Fail");
        }
    }

    public int getSumMoney() {
        int sum = 0;
        for(int i = 0; i < inCartBook.size(); i++) {
            sum += inCartBook.get(i).getPrice();
        }
        return sum;
    }

    public void clear() {
        inCartBook.clear();
    }

    public List<Book> getBook() {
        return inCartBook;
    }
}
