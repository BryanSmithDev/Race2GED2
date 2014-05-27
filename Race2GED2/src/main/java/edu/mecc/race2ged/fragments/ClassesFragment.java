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
import android.support.v4.app.FragmentManager;
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
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClassesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClassesFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ClassesFragment extends Fragment {

    private Region reg = null;
    private ArrayList<String> titles = new ArrayList<String>();
    private ArrayList<Fragment> frags = new ArrayList<Fragment>();
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment ClassesFragment.
     */
    // TODO: Rename and change types and number of parameters
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

    public ClassesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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

    public Region getRegion() {
        return reg;
    }

    public void setRegion(Region reg) {
        this.reg = reg;
    }

    public ArrayList<String> getTitles() {
        return titles;
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    public ArrayList<Fragment> getFrags() {
        return frags;
    }

    public void setFrags(ArrayList<Fragment> frags) {
        this.frags = frags;
    }
}
