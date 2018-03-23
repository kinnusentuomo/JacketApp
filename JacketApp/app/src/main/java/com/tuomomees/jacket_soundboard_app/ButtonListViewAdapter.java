package com.tuomomees.jacket_soundboard_app;

//LISTVIEWIÄ VARTEN LUOTU ADAPTERI

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class ButtonListViewAdapter extends ArrayAdapter<RowItemModel> {

    private final Context context;
    //Lista, joka käsittää 3 painikkeiset rivit
    private final ArrayList<RowItemModel> modelsArrayList;

    ButtonListViewAdapter(Context context, ArrayList<RowItemModel> modelsArrayList) {
        super(context, R.layout.listview_item, modelsArrayList);
        this.context = context;
        this.modelsArrayList = modelsArrayList;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView;

        assert inflater != null;
        rowView = inflater.inflate(R.layout.listview_item, parent, false);

        // 3. Get icon,title & counter views from the rowView
        Button button1 = rowView.findViewById(R.id.button1);
        Button button2 = rowView.findViewById(R.id.button2);
        Button button3 = rowView.findViewById(R.id.button3);

        // 4. Set the text for textView
        //imgView.setImageResource(modelsArrayList.get(position).getIcon());
        button1.setId(modelsArrayList.get(position).getButtonId1());
        button1.setText(modelsArrayList.get(position).getText(1));
        //button1.setText(String.valueOf(button1.getId()));
        button2.setId(modelsArrayList.get(position).getButtonId2());
        button2.setText(modelsArrayList.get(position).getText(2));
        //button2.setText(String.valueOf(button2.getId()));
        button3.setId(modelsArrayList.get(position).getButtonId3());
        button3.setText(modelsArrayList.get(position).getText(3));
        //button3.setText(String.valueOf(button3.getId()));

        // 5. retrn rowView
        return rowView;
    }
}
