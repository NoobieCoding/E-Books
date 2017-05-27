package com.example.tuterdust.e_books;

import android.database.Observable;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuterdust on 4/5/2560.
 */

public class Cart  implements Parcelable {

    private List<Book> inCartBook = new ArrayList<>();
    private User user;
    private Presenter pr;

    public Cart(User user, Presenter presenter) {
        this.user = user;
        pr = presenter;
    }

    protected Cart(Parcel in) {
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

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

    public int describeContents() {return 0;}

    public void writeToParcel(Parcel dest, int flags) {}
}
