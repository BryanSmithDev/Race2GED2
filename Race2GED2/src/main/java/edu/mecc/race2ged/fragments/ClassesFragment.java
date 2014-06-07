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

package edu.mecc.race2ged.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viewpagerindicator.TitlePageIndicator;
import java.util.ArrayList;
import edu.mecc.race2ged.JSON.County;
import edu.mecc.race2ged.JSON.Region;
import edu.mecc.race2ged.R;
import edu.mecc.race2ged.adapters.ClassPagerAdapter;

/**
 * <code>ClassesFragment</code> shows all counties via a ViewPager. Each page has that counties
 * class information.
 *
 * @author Bryan Smith
 * @date 5/26/2014.
 */
public class ClassesFragment extends Fragment {

    private Region reg = null;
    private ArrayList<String> titles = new ArrayList<String>();
    private ArrayList<Fragment> frags = new ArrayList<Fragment>();
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param reg The region object that stores the class data used.
     *
     * @return A new instance of fragment ClassesFragment.
     */
    public static ClassesFragment newInstance(Region reg) {
        ClassesFragment fragment = new ClassesFragment();
        fragment.setRegion(reg);
        if ( fragment.getRegion() != null){
            final int size = fragment.getRegion().getCounties().size();
            County c;
            for (int i = 0; i < size; i++)
            {
                c = fragment.getRegion().getCounties().get(i);
                fragment.getTitles().add(c.getName());
                fragment.getFrags().add(ClassPageFragment.newInstance(c));
            }
        }
        return fragment;
    }

    /**
     * Construct a <code>ClassesFragment</code>
     */
    public ClassesFragment() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * {@inheritDoc}
     * <p>
     *     Inflates and populates all of the counties fragments
     * </p>
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_classes, container, false);

        //Set the pager with an adapter
        ViewPager pager = (ViewPager)view.findViewById(R.id.pager);
        pager.setAdapter(new ClassPagerAdapter(getFragmentManager(),titles,frags));

        //Bind the title indicator to the adapter
        TitlePageIndicator titleIndicator = (TitlePageIndicator)view.findViewById(R.id.titles);
        titleIndicator.setSelectedColor(Color.BLACK);
        titleIndicator.setFooterColor(getResources().getColor(R.color.content_color));
        titleIndicator.setBackgroundColor(Color.WHITE);
        titleIndicator.setSelectedBold(true);
        titleIndicator.setTextColor(Color.GRAY);
        titleIndicator.setViewPager(pager);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    /**
     * Get the currently stored region data for the fragment.
     * @return The region data object currently stored.
     */
    //TODO: Possibly load from GEDApplication instead of storing in more than one place.
    public Region getRegion() {
        return reg;
    }

    /**
     * Sets the current region data object
     * @param reg The region object that stores the class data used.
     */
    public void setRegion(Region reg) {
        this.reg = reg;
    }

    /**
     * Get the list of titles currently stored.
     * @return List of titles currently stored.
     */
    public ArrayList<String> getTitles() {
        return titles;
    }

    /**
     * Sets the lists of titles to use.
     * @param titles Lists of titles to use.
     */
    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    /**
     * Get the list of fragments currently stored.
     * @return List of fragments currently stored.
     */
    public ArrayList<Fragment> getFrags() {
        return frags;
    }

    /**
     * Sets the lists of fragments to use.
     * @param frags Lists of fragments to use.
     */
    public void setFrags(ArrayList<Fragment> frags) {
        this.frags = frags;
    }
}
