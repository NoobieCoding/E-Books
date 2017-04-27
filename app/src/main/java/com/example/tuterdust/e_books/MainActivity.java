package com.example.tuterdust.e_books;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView{

    private ListView view;
    private MainPresenter mp;
    private BookRepository br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        br = JSONBookRepository.getInstance();
        mp = new MainPresenter(this, br);
    }

    public void setUpListView(List list) {

        view = (ListView) findViewById(R.id.listview);
        ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, list);
        view.setAdapter(adapter);
    }

    public void goSearchActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        MainActivity.this.startActivity(intent);
    }
}
