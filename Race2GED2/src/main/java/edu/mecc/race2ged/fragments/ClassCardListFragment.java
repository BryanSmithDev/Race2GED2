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

import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

import java.util.ArrayList;

import edu.mecc.race2ged.JSON.*;
import edu.mecc.race2ged.R;
import edu.mecc.race2ged.adapters.CardAdapter;
import edu.mecc.race2ged.cards.ClassCard;


/**
 * <code>ClassPageFragment</code> shows a scrollable list of all the class cards in the county.
 *
 * @author Bryan Smith
 * @date 5/26/2014.
 */
public class ClassCardListFragment extends CardListFragment {

    private County mCounty = null;
    private static final String ARG_COUNTY = "countyParam";

    private CardAdapter classListAdapter;
    private SwingBottomInAnimationAdapter animationAdapter;
    private ListView mListView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters. This fragment
     * shows a scrollable list of all the class cards in the county.
     *
     * @param county County data object for this fragment.
     *
     * @return A new instance of fragment ClassesFragment.
     */
    public static ClassCardListFragment newInstance(County county) {
        ClassCardListFragment fragment = new ClassCardListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_COUNTY, county);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Construct a <code>ClassPageFragment</code>
     */
    public ClassCardListFragment() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mCounty = (County)getArguments().getSerializable(ARG_COUNTY);
        }

        classListAdapter = new CardAdapter(getActivity().getBaseContext(),populateCards());
        animationAdapter = new SwingBottomInAnimationAdapter(classListAdapter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null){
            County tmp = (County)savedInstanceState.getSerializable(ARG_COUNTY);
            if (tmp != null) mCounty=tmp;
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card_list, container, false);
        mListView = (ListView)view.findViewById(R.id.list);

        //Setup list animations
        animationAdapter.setAbsListView(mListView);

        mListView.setAdapter(animationAdapter);

        return view;
    }

    /**
     * Creates the class cards from the stored county data.
     * @return ArrayList of the class cards.
     */
    public ArrayList<View> populateCards(){
        ArrayList<View> cards = new ArrayList<View>();
        for(int i=0; i<mCounty.getClasses().size();i++){
            ClassCard classCard = new ClassCard(getActivity().getBaseContext(), mCounty.getClasses().get(i));
            cards.add(classCard);
        }
        return cards;
    }

    /**
     * Called to ask the fragment to save its current dynamic state, so it
     * can later be reconstructed in a new instance of its process is
     * restarted.  If a new instance of the fragment later needs to be
     * created, the data you place in the Bundle here will be available
     * in the Bundle given to {@link #onCreate(android.os.Bundle)},
     * {@link #onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)}, and
     * {@link #onActivityCreated(android.os.Bundle)}.
     * <p/>
     * <p>This corresponds to {@link android.app.Activity#onSaveInstanceState(android.os.Bundle)
     * Activity.onSaveInstanceState(Bundle)} and most of the discussion there
     * applies here as well.  Note however: <em>this method may be called
     * at any time before {@link #onDestroy()}</em>.  There are many situations
     * where a fragment may be mostly torn down (such as when placed on the
     * back stack with no UI showing), but its state will not be saved until
     * its owning activity actually needs to save its state.
     *
     * @param outState Bundle in which to place your saved state.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARG_COUNTY,mCounty);
    }

    /**
     * Get the county data object of this fragment.
     * @return County data object
     */
    public County getCounty() {
        return mCounty;
    }

    /**
     * Set the county for this fragment.
     * @param county County data object to use.
     */
    public void setCounty(County county) {
        this.mCounty = county;
    }
}
