package com.example.tuterdust.e_books;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView{

    private ListView view;
    private MainPresenter mp;
    private BookRepository br;
    private List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int fund = getIntent().getIntExtra("fund", 0);
        br = JSONBookRepository.getInstance();
        mp = new MainPresenter(this, br);
        mp.setFund(fund);
    }

    public void setUpListView(List list) {

        view = (ListView) findViewById(R.id.listview);
        books = list;
        ArrayAdapter<Book> adapter = new BookAdapter(this, list);
        view.setAdapter(adapter);
    }

    public void goSearchActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        intent.putExtra("list", (Serializable)(books));
        intent.putExtra("fund", mp.getFund());
        MainActivity.this.startActivity(intent);
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
                        mp.addFund(url);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();
    }

    public void setFund(int fund) {
        TextView fundLabel = (TextView)findViewById(R.id.fund_label);
        fundLabel.setText("Fund: "+ fund +"$");
    }
}
