package edu.mecc.race2ged.navigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.mecc.race2ged.R;
import edu.mecc.race2ged.helpers.Utils;


/**
 * Created by Bryan on 10/23/13.
 */
public class NavDrawerListAdapter extends BaseAdapter {

    private ArrayList<NavDrawerListItem> items = new ArrayList<NavDrawerListItem>();
    private Context context;


    public NavDrawerListAdapter(Context context) {
        setContext(context);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return getItems().size();
    }

    public ArrayList<NavDrawerListItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<NavDrawerListItem> items) {
        this.items = items;
    }

    @Override
    public NavDrawerListItem getItem(int position) {
        return getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NavDrawerListItem listItem = getItem(position);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(listItem.getViewResourceID(), parent, false);
        TextView textView = (TextView) rowView.findViewById(listItem.getTextViewID());

        Utils.setRobotoThin(context, textView);
        if (listItem.getIconResourceID() != -1) {
            textView.setText(listItem.getTextString().toUpperCase());
            textView.setCompoundDrawablePadding(30);
            textView.setCompoundDrawablesWithIntrinsicBounds(listItem.getIconResourceID(), 0, 0, 0);
            if (position == getCount() - 1)
                rowView.findViewById(R.id.last_item_line).setVisibility(View.VISIBLE);
        } else {
            textView.setText(listItem.getTextString());
        }
        return rowView;
    }

    public void push(NavDrawerListItem item) {
        items.add(item);
    }

    public void populate(int textViewID, String[] primaryItems, int iconViewID, String[] secondaryItems, TypedArray secondaryIcons) {
        for (String s : primaryItems) {
            push(new NavDrawerListItemNoIcon(textViewID, s));
        }
        int count = 0;
        for (String s : secondaryItems) {
            push(new NavDrawerListItemWithIcon(textViewID, s, secondaryIcons.getResourceId(count, 0)));
            count++;
        }
        secondaryIcons.recycle();
    }
}
