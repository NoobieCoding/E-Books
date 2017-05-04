package com.example.tuterdust.e_books;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity  implements SearchActivityView{

    private BookRepository br;
    private SearchActivityPresenter pr;
    private List<Book> books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        books = new ArrayList<Book>();
        books = (ArrayList<Book>)getIntent().getSerializableExtra("list");
        int fund = getIntent().getIntExtra("fund", 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        br = JSONBookRepository.getInstance();
        pr = new SearchActivityPresenter(this, books);
        pr.setFund(fund);
    }

    public void search(View view) {
        EditText editText = (EditText)findViewById(R.id.search_text);
        String keyword = editText.getText().toString();
        pr.searchBook(keyword);

    }

    public void onBackPressed() {
        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
        intent.putExtra("fund", pr.getFund());
        SearchActivity.this.startActivity(intent);
    }

    @Override
    public void setList(List<Book> outList) {
        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(new BookAdapter(this, outList));
    }

    public void setFund(int fund) {
        TextView fundLabel = (TextView)findViewById(R.id.fund_label);
        fundLabel.setText("Fund: "+ fund +"$");
    }

    public void addFund(View view) {
        final EditText txtUrl = new EditText(this);


        new AlertDialog.Builder(this)
                .setTitle("Add Fund")
                .setMessage("Enter money to be added")
                .setView(txtUrl)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String url = txtUrl.getText().toString();
                        pr.addFund(url);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();
    }
}
