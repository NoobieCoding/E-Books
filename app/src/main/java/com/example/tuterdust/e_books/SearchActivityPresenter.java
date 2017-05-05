package com.example.tuterdust.e_books;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by tuterdust on 27/4/2560.
 */

class SearchActivityPresenter implements Presenter{

    private String keyword;
    private SearchActivityView view;
    private List<Book> books;
    private User user;
    private Cart cart;

    public SearchActivityPresenter(SearchActivityView view, List<Book> br) {
            this.view = view;
            books = br;
    }

    public void searchBook(String keyword) {
        this.keyword = keyword;
        searchFromList();
    }


    public void searchFromList() {
        System.out.println(keyword);
        List<Book> outList = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            Book currBook = books.get(i);
            if (currBook.getTitle().contains(keyword) || currBook.getYear().contains(keyword))
                outList.add(currBook);
        }
        Collections.sort(outList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.compareTo(o2);
            }
        });

        if(outList.size()> 0)
            view.setList(outList);
    }

    public void setUser(User user) {
        if (user == null)
            this.user = new User("Guest user");
        else
            this.user = user;
        view.setFund(user.fund);
    }

    public void setCart(Cart cart) {
        if (cart == null)
            this.cart = new Cart(user, this);
        else
            this.cart = cart;;
    }

    public Cart getCart() {
        return cart;
    }

    public User getUser() {
        return user;
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
        System.out.println("Success2");
    }

    @Override
    public void setChanged() {
        view.setFund(user.fund);
    }
}
