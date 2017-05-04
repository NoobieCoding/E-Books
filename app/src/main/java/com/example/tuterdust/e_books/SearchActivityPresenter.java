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

class SearchActivityPresenter{

    private String keyword;
    private SearchActivityView view;
    private List<Book> books;
    private int fund;

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
