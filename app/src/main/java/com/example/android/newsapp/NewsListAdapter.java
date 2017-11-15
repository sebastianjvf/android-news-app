package com.example.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsListAdapter extends ArrayAdapter<NewsArticle> {
    public NewsListAdapter(@NonNull Context context, @NonNull List<NewsArticle> objects) {
        super(context, R.layout.list_item, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final NewsArticle currentItem = getItem(position);

        // If the returned view has not already been cultivated, inflate it
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
        }

        // Extract the data from the current item and set the Text to the list item
        TextView title = convertView.findViewById(R.id.title);
        TextView date = convertView.findViewById(R.id.date);

        // Parse to a readable format
        try {
            String dateToParse = currentItem.getDate().replace("T", " ").replace("Z", "");
            Date dateParsed = (new SimpleDateFormat("yyyy-mm-dd HH:mm:ss")).parse(dateToParse);
            String dateToDisplay = (new SimpleDateFormat("dd MMM yyyy, HH:mm:ss")).format(dateParsed);
            date.setText(dateToDisplay);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("Bla", e.toString());
        };

        title.setText(currentItem.getTitle());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, (Uri.parse(currentItem.getUrl())));

                // If a programme is able to process the intent, open it
                if(intent.resolveActivity(getContext().getPackageManager()) != null) {
                    getContext().startActivity(intent);
                }
            }
        });

        return convertView;
    }
}
