package com.example.tuterdust.e_books;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by tuterdust on 27/4/2560.
 */

class SearchActivityPresenter implements Observer{

    private String keyword;
    private SearchActivityView view;
    private BookRepository respo;

    public SearchActivityPresenter(SearchActivityView view, BookRepository br) {
            this.view = view;
            respo = br;
    }

    public void searchBook(String keyword) {
        this.keyword = keyword;
        respo.addObserver(this);
        respo.fetchAllBooks();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o == respo) {
            List<Book> books = respo.getAllBooks();
            searchFromList(books);
        }
    }

    public Book searchFromList(List<Book> books) {
        return null;
    }
}
