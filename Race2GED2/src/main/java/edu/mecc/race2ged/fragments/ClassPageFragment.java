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
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import edu.mecc.race2ged.JSON.*;
import edu.mecc.race2ged.R;
import edu.mecc.race2ged.adapters.ClassListAdapter;

/**
 * <code>ClassPageFragment</code> shows a scrollable list of all the class cards in the county.
 *
 * @author Bryan Smith
 * @date 5/26/2014.
 */
public class ClassPageFragment extends Fragment {

    private County county = null;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters. This fragment
     * shows a scrollable list of all the class cards in the county.
     *
     * @param county County data object for this fragment.
     *
     * @return A new instance of fragment ClassesFragment.
     */
    public static ClassPageFragment newInstance(County county) {
        ClassPageFragment fragment = new ClassPageFragment();
        fragment.setCounty(county);
        return fragment;
    }

    /**
     * Construct a <code>ClassPageFragment</code>
     */
    public ClassPageFragment() {
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
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_class_page, container, false);
        ListView mListView = (ListView)view.findViewById(R.id.list);
        mListView.setAdapter(new ClassListAdapter(getActivity().getApplicationContext(),getCounty()));

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onClassPagerFragmentInteraction(uri);
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
        public void onClassPagerFragmentInteraction(Uri uri);
    }

    /**
     * Get the county data object of this fragment.
     * @return County data object
     */
    public County getCounty() {
        return county;
    }

    /**
     * Set the county for this fragment.
     * @param county County data object to use.
     */
    public void setCounty(County county) {
        this.county = county;
    }
}
