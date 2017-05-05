package com.example.tuterdust.e_books;

/**
 * Created by tuterdust on 5/5/2560.
 */

public interface Presenter {
    public void addToCart(Book book);

    public void removeBook(Book book);

    public void setChanged();
}
