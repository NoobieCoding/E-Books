package com.example.tuterdust.e_books;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity  implements SearchActivityView{

    private BookRepository br;
    private SearchActivityPresenter pr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        br = JSONBookRepository.getInstance();
        pr = new SearchActivityPresenter(this, br);

    }

    public void search(View view) {
        Log.d("12", "search: Hello");
        EditText editText = (EditText)findViewById(R.id.search_text);
        String keyword = editText.getText().toString();
        pr.searchBook(keyword);

    }
}
