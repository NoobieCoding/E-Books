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
public class MainPresenter implements Observer, Presenter {

    private MainView view;
    private BookRepository respo;
    private User user;
    private Cart cart;


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

    public void setUser(User user) {
        if (user == null)
            this.user = new User("Guest user");
        else
            this.user = user;
        view.setFund(this.user.fund);
    }

    public void setCart(Cart cart) {
        if (cart == null)
            this.cart = new Cart(user, this);
        else
            this.cart = cart;;
    }


    public User getUser() {
        return user;
    }

    public Cart getCart() {
        return cart;
    }

    public void addFund(String added) {
        try {
            user.fund += Integer.parseInt(added);
            view.setFund(user.fund);
        }catch (Exception e) {
            //do nothing
        }
    }

    @Override
    public void addToCart(Book book) {
        cart.add(book);
    }

    @Override
    public void removeBook(Book book) {
        cart.remove(book);
    }

    @Override
    public void setChanged() {
        view.setFund(user.fund);
    }

}
