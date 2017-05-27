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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        books = new ArrayList<Book>();
        books = (ArrayList<Book>)getIntent().getSerializableExtra("list");
        Cart cart = (Cart)getIntent().getSerializableExtra("cart");
        User user = (User)getIntent().getSerializableExtra("user");
        br = JSONBookRepository.getInstance();
        pr = new SearchActivityPresenter(this, books);
        pr.setUser(user);
        pr.setCart(cart);
    }

    public void search(View view) {
        EditText editText = (EditText)findViewById(R.id.search_text);
        String keyword = editText.getText().toString();
        pr.searchBook(keyword);

    }

    public void onBackPressed() {
        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
        intent.putExtra("user", pr.getUser());
        intent.putExtra("cart", pr.getCart());
        SearchActivity.this.startActivity(intent);
    }

    public void goUserActivity(View view) {
        Intent intent = new Intent(SearchActivity.this, UserActivity.class);
        intent.putExtra("user", pr.getUser());
        SearchActivity.this.startActivity(intent);
    }

    @Override
    public void setList(List<Book> outList) {
        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(new BookAdapter(this, outList, pr));
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

    public void openCart(View view) {
        Cart cart = pr.getCart();
        final ListView cartList = new ListView(this);
        cartList.setAdapter(new BookAdapter3(this, cart.getBook(), pr));

        new AlertDialog.Builder(this)
                .setTitle("Cart")
                .setMessage("Your cart")
                .setView(cartList)
                .setPositiveButton("Checkout", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //do something
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();
    }
}
