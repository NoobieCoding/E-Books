package com.example.tuterdust.e_books;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.squareup.picasso.Picasso;

/**
 * Created by tuterdust on 20/4/2560.
 */

public class BookAdapter3 extends ArrayAdapter<Book>{

    public Presenter presenter;

    static class ViewHolder{
        public ImageView img;
        public TextView title;
        public TextView price;
        public ImageButton removeButton;
    }

    public BookAdapter3(Context context,List<Book> books, Presenter pr) {
        super(context, 0, books);
        presenter= pr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ViewHolder viewHolder = new ViewHolder();
        final Book book = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.book_view3, parent, false);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
            viewHolder.removeButton = (ImageButton) convertView.findViewById(R.id.remove_button);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Lookup view for data population
        // Populate the data into the template view using the data object
        viewHolder.title.setText(book.getTitle());
        viewHolder.price.setText(book.getPrice() + "$");
        viewHolder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.removeBook(book);
                notifyDataSetChanged();
            }
        });
        Picasso.with(getContext()).load(Uri.parse(book.getUrl())).into(viewHolder.img);
        // Return the completed view to render on screen
        return convertView;
    }



}
