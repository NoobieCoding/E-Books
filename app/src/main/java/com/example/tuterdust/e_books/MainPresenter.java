package com.example.tuterdust.e_books;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by tuterdust on 20/4/2560.
 */

@SuppressWarnings("Since15")
public class MainPresenter implements Observer {

    private MainView view;
    private int fund;
    private BookRepository respo;

    public MainPresenter(MainView view, BookRepository respo) {
        this.view = view;
        this.respo = respo;
        setList();
    }

    public void setList() {
        respo.addObserver(this);
        respo.fetchAllBooks();
    }


    @Override
    public void update(Observable o, Object arg) {
        if(o == respo) {
            List<Book> books = respo.getAllBooks();
            sortBooks(books);
            view.setUpListView(books);
        }
    }

    public void sortBooks(List<Book> books) {
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.compareTo(o2);
            }
        });
    }

    public void setFund(int fund) {
        this.fund = fund;
        view.setFund(fund);
    }

    public int getFund() {
        return fund;
    }

    public void addFund(String added) {
        try {
            fund += Integer.parseInt(added);
            view.setFund(fund);
        }catch (Exception e) {
            //do nothing
        }
    }

}
