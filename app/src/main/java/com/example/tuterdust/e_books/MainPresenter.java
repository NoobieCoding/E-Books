package com.example.tuterdust.e_books;

import android.app.Activity;

import java.util.ArrayList;
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
           // sortBooks(books);
            view.setUpListView(books);
        }
    }

    public void sortBooks(List<Book> books) {
        System.out.println("PREPARING");
        books.sort(new Comparator<Comparable>() {
            @Override
            public int compare(Comparable o1, Comparable o2) {
                return o1.compareTo(o2);
            }
        });
    }
}
