package com.example.tuterdust.e_books;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        user = (User) getIntent().getSerializableExtra("user");
        setComponent();
    }

    public void setComponent() {
        setName();
        setFund();
        setList();
    }

    public void setName() {
        TextView nameLabel = (TextView)findViewById(R.id.name_label);
        nameLabel.setText(user.name);
    }

    public void setFund() {
        TextView fundLabel = (TextView)findViewById(R.id.fund_label);
        fundLabel.setText("Fund: " + user.fund+"$");
    }

    public void setList() {
        if (user.ownBook != null) {
            ListView listView = (ListView) findViewById(R.id.book_list);
            listView.setAdapter(new BookAdapter2(this, user.ownBook));
        }
    }



}
