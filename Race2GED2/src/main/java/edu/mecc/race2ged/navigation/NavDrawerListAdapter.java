/*
 * Copyright 2014 Regional Adult Education Program of Lee, Scott, Wise, and Norton Public Schools
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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
 * NavDrawerListAdapter is a list adapter than manages the layout of the Navigation Drawer list.
 * @see android.widget.BaseAdapter
 * @see android.widget.ListAdapter
 *
 * @author Bryan Smith
 */
public class NavDrawerListAdapter extends BaseAdapter {

    private ArrayList<NavDrawerListItem> items = new ArrayList<NavDrawerListItem>();
    private Context context;

    /**
     * Constructs the NavDrawerListAdapter
     * @param context The context of the activity.
     */
    public NavDrawerListAdapter(Context context) {
        setContext(context);
    }

    /**
     * Returns the Navigation Drawers currently stored context.
     * @return The Navigation Drawers currently stored context.
     */
    public Context getContext() {
        return context;
    }

    /**
     * Sets the navigation drawer's stored context
     * @param context The context to store.
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Returns the number of navigation drawer items.
     * @return The number of navigation drawer items.
     */
    @Override
    public int getCount() {
        return getItems().size();
    }

    /**
     * Returns the number of navigation drawer items.
     * @return The number of navigation drawer items.
     */
    public ArrayList<NavDrawerListItem> getItems() {
        return items;
    }

    /**
     * Sets the Navigation Drawer's array list of items to the values stored in another array list.
     * @param items The item array list to set the navigation drawer's item array list to.
     */
    public void setItems(ArrayList<NavDrawerListItem> items) {
        this.items = items;
    }

    /**
     * Returns a Navigation Drawer list item at the specified position.
     * @param position The position of the item to retrieve.
     * @return The NavDrawerListItem at the specified position.
     */
    @Override
    public NavDrawerListItem getItem(int position) {
        return getItems().get(position);
    }

    /**
     * NOT CURRENTLY USED BUT REQUIRED DUE TO BASE CLASS
     * @param position NOT USED
     * @return -1
     */
    @Override
    public long getItemId(int position) {
        return -1;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link android.view.LayoutInflater#inflate(int, android.view.ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position The position of the item within the adapter's data set of the item whose view
     *        we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *        is non-null and of an appropriate type before using. If it is not possible to convert
     *        this view to display the correct data, this method can create a new view.
     *        Heterogeneous lists can specify their number of view types, so that this View is
     *        always of the right type (see {@link #getViewTypeCount()} and
     *        {@link #getItemViewType(int)}).
     * @param parent The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
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

    /**
     * Adds a Navigation item to the navigation drawers list.
     * @param item A NavDrawerListItem to add to the list.
     */
    public void push(NavDrawerListItem item) {
        items.add(item);
    }

    /**
     * Populates the navigation drawer with items read from arrays. Primary items are large and do not
     * have an icon. Secondary items are smaller and have a icon.
     * @param textViewID The ID of the text view that will have its text value replaced.
     * @param primaryItems An array of strings that contains the text data that will be stored in the
     *                     corresponding primary items text view.
     * @param secondaryItems An array of strings that contains the text data that will be stored in the
     *                     corresponding secondary items text view.
     * @param secondaryIcons A typed array of that contains the icons used for each item to display.
     */
    public void populate(int textViewID, String[] primaryItems, String[] secondaryItems, TypedArray secondaryIcons) {
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
