package edu.mecc.race2ged.navigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Bryan on 10/23/13.
 */
public class NavDrawerListAdapter extends ArrayAdapter<String> {

    private int navView;
    private int textId;
    private int iconId;
    private String[] titles;
    private TypedArray icons;
    private Context context;


    public NavDrawerListAdapter(Context context1, int resource, int textViewResourceId,  String[] objects, int imageViewResourceId, TypedArray iconsL) {
        super(context1, resource, textViewResourceId, objects);
        navView= resource;
        context = context1;
        textId=textViewResourceId;
        titles=objects;
        iconId=imageViewResourceId;
        icons=iconsL;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(navView, parent, false);
        TextView textView = (TextView) rowView.findViewById(textId);
        ImageView imageView = (ImageView) rowView.findViewById(iconId);
        textView.setText(titles[position]);
        imageView.setImageResource(icons.getResourceId(position, -1));



        return rowView;
    }
}
