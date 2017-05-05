package com.example.tuterdust.e_books;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.squareup.picasso.Picasso;

/**
 * Created by tuterdust on 20/4/2560.
 */

public class BookAdapter2 extends ArrayAdapter<Book> {

    static class ViewHolder{
        public ImageView img;
        public TextView title;
        public TextView price;
    }

    public BookAdapter2(Context context,List<Book> books) {
        super(context, 0, books);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ViewHolder viewHolder = new ViewHolder();
        Book book = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.book_view2, parent, false);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Lookup view for data population
        // Populate the data into the template view using the data object
        viewHolder.title.setText(book.getTitle());
        viewHolder.price.setText(book.getPrice() + "$");
        Picasso.with(getContext()).load(Uri.parse(book.getUrl())).into(viewHolder.img);
        // Return the completed view to render on screen
        return convertView;
    }



}
