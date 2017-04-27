package com.example.tuterdust.e_books;

import android.os.AsyncTask;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by tuterdust on 27/4/2560.
 */

public class JSONBookRepository extends BookRepository {

    private List<Book> books;
    private static JSONBookRepository instance;

    private JSONBookRepository() {
        books = new ArrayList<Book>();
    }

    public static JSONBookRepository getInstance() {
        if(instance == null)
            instance = new JSONBookRepository();
            return instance;
    }

    @Override
    public void fetchAllBooks() {
        BookFetcherTask task = new BookFetcherTask();
        task.execute();

    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    public class BookFetcherTask extends AsyncTask<Void,Void,ArrayList<Book>> {
        @Override
        protected ArrayList<Book> doInBackground(Void... params) {
            String bookListJsonStr = loadJSON();
            if (bookListJsonStr != null) {
                return BookJSONDecoder.createListFromJSONStr(bookListJsonStr);
            } else {
                return null;
            }
        }

        private String loadJSON() {
            URL booksURL = null;
            try {
                booksURL = new URL("https://theory.cpe.ku.ac.th/~jittat/courses/sw-spec/ebooks/books.json");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return (new UrlFetcher(booksURL)).fetch();
        }

        @Override
        protected void onPostExecute(ArrayList<Book> results) {
            if (results != null) {
                books.clear();
                books.addAll(results);
                setChanged();
                notifyObservers();
            }
        }
    }
}
