package com.example.tuterdust.e_books;

import android.support.annotation.NonNull;

import java.net.URL;

/**
 * Created by tuterdust on 20/4/2560.
 */

public class Book implements Comparable{

    private int price, id;
    private String title, url, year;

    public Book(int price, int id, String title, String year, String url) {
        this.price = price;
        this.id = id;
        this.url = url;
        this.year = year;
        this.title = title;
    }

    public int getId() {
        return id;
    }


    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getYear() {return year;}

    public String toString() {
        return id + "  " + title + " " + year + " " + price;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        Book book = (Book) o;
        if(this.id > book.id)
            return 1;
        else
            return -1;
    }
}
