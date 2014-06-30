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

package edu.mecc.race2ged.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.mecc.race2ged.GEDApplication;
import edu.mecc.race2ged.JSON.Region;
import edu.mecc.race2ged.R;
import edu.mecc.race2ged.fragments.ClassPageFragment;
import edu.mecc.race2ged.fragments.ClassesFragment;
import edu.mecc.race2ged.navigation.DrawerLayout;
import edu.mecc.race2ged.navigation.NavigationDrawerFragment;

/**
 * HomeActivity is the primary activity of the Race2GED app and acts as the launcher. Most content
 * is displayed here using fragments.
 *
 * @author Bryan Smith
 */
public class HomeActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, ClassesFragment.OnFragmentInteractionListener, ClassPageFragment.OnFragmentInteractionListener{


    private static final String ARG_SELECTED = "selectedParam";
    private static final String ARG_REGION = "regionParam";

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private Region mRegion = null;
    private int mSelectedItem = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        GEDApplication.setStoredContext(this);

        if (savedInstanceState == null) {
            mRegion = (Region)getIntent().getExtras().getSerializable(ARG_REGION);
        } else {
            mRegion = (Region)savedInstanceState.getSerializable(ARG_REGION);
        }
    }

    /**
     * Save all appropriate fragment state.
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mRegion != null) outState.putSerializable(ARG_REGION,mRegion);
        outState.putInt(ARG_SELECTED,mSelectedItem);
    }

    /**
     * This method is called after {@link #onStart} when the activity is
     * being re-initialized from a previously saved state, given here in
     * <var>savedInstanceState</var>.  Most implementations will simply use {@link #onCreate}
     * to restore their state, but it is sometimes convenient to do it here
     * after all of the initialization has been done or to allow subclasses to
     * decide whether to use your default implementation.  The default
     * implementation of this method performs a restore of any view state that
     * had previously been frozen by {@link #onSaveInstanceState}.
     * <p/>
     * <p>This method is called between {@link #onStart} and
     * {@link #onPostCreate}.
     *
     * @param savedInstanceState the data most recently supplied in {@link #onSaveInstanceState}.
     * @see #onCreate
     * @see #onPostCreate
     * @see #onResume
     * @see #onSaveInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            mRegion = (Region) savedInstanceState.getSerializable(ARG_REGION);
            mSelectedItem = savedInstanceState.getInt(ARG_SELECTED,0);
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        int numb = position+1;
        if (mSelectedItem == numb) return;
        mSelectedItem = numb;
        switch (numb) {
            case 2:
                // update the main content by replacing fragments
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.container, ClassesFragment.newInstance(mRegion));
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();
                onSectionAttached(numb);
                break;
            case 6:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                break;
            default:
                // update the main content by replacing fragments
                ft = fragmentManager.beginTransaction();
                ft.replace(R.id.container, PlaceholderFragment.newInstance(position + 1));
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();
                onSectionAttached(numb);
        }
    }

    /**
     * When a fragment is replaced, what will happen?
     * @param number The position of the list item that invoked the fragment transaction.
     */
    public void onSectionAttached(int number) {
        String[] titles = getResources().getStringArray(R.array.navPrimaryTitles);
        switch (number) {
            case 1:
                mTitle = getString(R.string.app_name);
                break;
            default:
                if (titles[number - 1] != null) mTitle = titles[number - 1];
                else mTitle = getString(R.string.app_name);
        }
    }



    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            //getMenuInflater().inflate(R.menu.home, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }*/
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClassPagerFragmentInteraction(Uri uri) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public void onResume() {
            super.onResume();
            // Set title
            try {
                ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle(R.string.app_name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((HomeActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
