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
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

import java.util.ArrayList;

import edu.mecc.race2ged.R;
import edu.mecc.race2ged.activities.HomeActivity;
import edu.mecc.race2ged.adapters.CardAdapter;


/**
 * A fragment representing a list of Items.
 * <p />
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p />
 * Activities containing this fragment MUST implement the {@link AbsListView.OnItemClickListener}
 * interface.
 */
public class CardListFragment extends Fragment {

    private String mTitle;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private CardAdapter mAdapter;

    private ArrayList<View> mCards = new ArrayList<View>();
    private SwingBottomInAnimationAdapter animationAdapter;

    public static CardListFragment newInstance(ArrayList<View> cards) {
        CardListFragment fragment = new CardListFragment();
        fragment.mCards = cards;
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CardListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new CardAdapter(getActivity(),mCards);
        animationAdapter = new SwingBottomInAnimationAdapter(mAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_list, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(R.id.list);
        mListView.setSelector(getResources().getDrawable(R.drawable.transparent));
        animationAdapter.setAbsListView(mListView);
        ((AdapterView<ListAdapter>) mListView).setAdapter(animationAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mTitle != null)
            ((HomeActivity)getActivity()).getSupportActionBar().setTitle(mTitle);
        else
            mTitle = getString(R.string.app_name);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyText instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
