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

package edu.mecc.race2ged.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import edu.mecc.race2ged.JSON.*;
import edu.mecc.race2ged.JSON.Class;
import edu.mecc.race2ged.R;

/**
 * The <code>ClassPagerAdapter</code> serves the class fragments per county when paging.
 *
 * @author Bryan Smith
 */
public class ClassListAdapter extends BaseAdapter {

    private ArrayList<Class> items = new ArrayList<Class>();
    private Context context;

    /**
     * Constructs the <code>ClassListAdapter</code>
     *
     * @param context The context of the activity.
     * @param county The county data object to load classes from.
     */
    public ClassListAdapter(Context context, County county) {
        setContext(context);
        if (county != null)
            if (county.getClasses() != null)
                setItems((ArrayList<Class>)county.getClasses());
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return getItems().size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Class getItem(int position) {
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
     * {@inheritDoc}
     * <p>
     *     Inflates and populates the class card view
     * </p>
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Class mClass = getItem(position);

        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(row==null) {
            row = inflater.inflate(R.layout.card, parent, false);

            //Find the card
            ViewStub stub = (ViewStub) row.findViewById(R.id.cardStub);
            stub.setLayoutResource(R.layout.class_card);
            View inflatedStub = stub.inflate();

            //Setup location info
            TextView location = (TextView) inflatedStub.findViewById(R.id.location);
            location.setText(mClass.getLocation());

            //Setup locality info
            TextView locality = (TextView) inflatedStub.findViewById(R.id.locality);
            locality.setText(mClass.getName());

            //Setup address info
            TextView address = (TextView) inflatedStub.findViewById(R.id.address);
            address.setText(mClass.getAddress());

            //Setup time info
            TextView times = (TextView) inflatedStub.findViewById(R.id.times);
            String display = "";
            try {
                ArrayList<String> days = (ArrayList<String>) mClass.getDays();
                ArrayList<String> time = (ArrayList<String>) mClass.getTimes();
                final int count = days.size();
                for (int i = 0; i < count; i++) {
                    if (i!=0) display=display+"\n";
                    display = display + days.get(i) + ":   " + time.get(i);
                }
            } catch (Exception e){
                Log.e(this.getClass().getSimpleName(),"Error displaying time for class schedule. - "+e.getMessage());
            }
            times.setText(display);

            //Setup instructors info
            TextView instructors = (TextView) inflatedStub.findViewById(R.id.instructors);
            display = "";
            try {
                ArrayList<Instructor> ins = (ArrayList<Instructor>) mClass.getInstructors();
                final int count = ins.size();
                for (int i = 0; i < count; i++) {
                    if (i!=0) display=display+"\n";
                    display = display + ins.get(i).getName();
                }
            } catch (Exception e){
                Log.e(this.getClass().getSimpleName(),"Error displaying instructors for class schedule. - "+e.getMessage());
            }
            instructors.setText(display);


        }

        return row;
    }


    /**
     * Returns the array list of classes
     * @return Array list of classes
     */
    public ArrayList<Class> getItems() {
        return items;
    }

    /**
     * Set the ArrayList of classes to the desired ArrayList
     * @param items The ArrayList to use.
     */
    public void setItems(ArrayList<Class> items) {
        this.items = items;
    }

    /**
     * Get the currently stored context.
     * @return Currently stored context.
     */
    public Context getContext() {
        return context;
    }

    /**
     * Set the current app context.
     * @param context Context to use.
     */
    public void setContext(Context context) {
        this.context = context;
    }

}
