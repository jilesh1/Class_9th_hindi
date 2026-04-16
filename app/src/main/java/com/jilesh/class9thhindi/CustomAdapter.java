package com.jilesh.class9thhindi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    Context context;
    String[] data;
    private String bgType;   // ✅ Step 2 (ADD HERE)

    // ✅ Updated constructor
    public CustomAdapter(Context context, String[] data, String bgType) {
        this.context = context;
        this.data = data;
        this.bgType = bgType;   // ✅ store value
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.list_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.textItem);
        textView.setText(data[position]);

        // ✅ Apply background based on activity
        View layout = convertView.findViewById(R.id.rootLayout);
        // 👆 IMPORTANT: add id in XML (see below)

        if (bgType.equals("gadya")) {
            layout.setBackgroundResource(R.drawable.gradient_card_gadya);
        } else if (bgType.equals("kavya")) {
            layout.setBackgroundResource(R.drawable.gradient_card_kavya);
        } else if (bgType.equals("sanskrit")) {
            layout.setBackgroundResource(R.drawable.gradient_card_sanskrit);
        }

        return convertView;
    }
}