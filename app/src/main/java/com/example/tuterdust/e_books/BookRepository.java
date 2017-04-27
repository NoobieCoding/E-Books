package com.example.tuterdust.e_books;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by tuterdust on 20/4/2560.
 */

public abstract class BookRepository extends Observable{
    public abstract void fetchAllBooks();
    public abstract List<Book> getAllBooks();

}