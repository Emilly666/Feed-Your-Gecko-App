package com.example.feed_your_gecko_app;

import android.content.Context;
import android.widget.ArrayAdapter;

public class AutocompleteArrayAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private String[] mItems;

    public AutocompleteArrayAdapter(Context context, int resource, String[] items) {
        super(context, resource, items);
        mContext = context;
        mItems = items;
    }
}
