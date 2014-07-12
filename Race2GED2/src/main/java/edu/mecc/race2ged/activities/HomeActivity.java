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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import edu.mecc.race2ged.GEDApplication;
import edu.mecc.race2ged.JSON.Region;
import edu.mecc.race2ged.R;
import edu.mecc.race2ged.fragments.CardListFragment;
import edu.mecc.race2ged.fragments.ClassCardListFragment;
import edu.mecc.race2ged.fragments.ClassViewPagerFragment;
import edu.mecc.race2ged.navigation.DrawerLayout;
import edu.mecc.race2ged.navigation.NavigationDrawerFragment;
import edu.mecc.race2ged.widgets.Card;
import edu.mecc.race2ged.widgets.Header;
import eu.inmite.android.lib.dialogs.SimpleDialogFragment;

/**
 * HomeActivity is the primary activity of the Race2GED app and acts as the launcher. Most content
 * is displayed here using fragments.
 *
 * @author Bryan Smith
 */
public class HomeActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
                   ClassViewPagerFragment.OnFragmentInteractionListener,
                   ClassCardListFragment.OnFragmentInteractionListener,
                   CardListFragment.OnFragmentInteractionListener {


    private static final String ARG_SELECTED = "selectedParam";
    private static final String ARG_REGION = "regionParam";

    private static final String HOME_FRAG_TAG = "homeFrag";
    private static final String CLASSES_FRAG_TAG = "classesFrag";
    private static final String PRACTICE_FRAG_TAG = "practiceFrag";
    private static final String TESTING_FRAG_TAG = "testingFrag";
    private static final String RESOURCES_FRAG_TAG = "resourcesFrag";

    private CharSequence mTitle;
    private Region mRegion = null;
    private ArrayList<View> mHomeCards = new ArrayList<View>();
    private ArrayList<View> mResourceCards  = new ArrayList<View>();
    private ArrayList<View> mTestingCards  = new ArrayList<View>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //For backwards compat. For some reason, the Drawer Glyph does not show up on older versions
        //unless these lines are added manually. Even though the ActionBarDrawerToggle calls them...
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);

        NavigationDrawerFragment mNavigationDrawerFragment = (NavigationDrawerFragment)
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

        populateCards();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, CardListFragment.newInstance(mHomeCards)).commit();

    }

    private void populateCards() {
        populateHomeCards();
        populateResourcesCards();
        populateTestingCards();
    }

    private void populateHomeCards() {
        mHomeCards.add(new Header(this,"Greetings"));
        mHomeCards.add(new Card(this,"TODO: Add greeting"));
        mHomeCards.add(new Header(this,"NEWS"));
        mHomeCards.add(new Card(this,"TODO: Add facebook feed"));
    }

    private void populateResourcesCards() {
        mResourceCards.add(new Header(this,"Category 1"));
        mResourceCards.add(new Card(this,"Resource Item 1"));
        mResourceCards.add(new Card(this,"Resource Item 2"));
        mResourceCards.add(new Card(this,"Resource Item 3"));
        mResourceCards.add(new Header(this,"Category 2"));
        mResourceCards.add(new Card(this,"Resource Item 4"));
    }

    private void populateTestingCards() {
        mTestingCards.add(new Header(this,"Testing"));
        mTestingCards.add(new Card(this,"TODO: Add Testing info"));
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
    protected void onRestoreInstanceState(@NotNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mRegion == null) {
            mRegion = (Region) savedInstanceState.getSerializable(ARG_REGION);
        }
    }

    private void replaceFragment(int numb,Fragment frag,String tag){
        replaceFragment(numb, frag, tag, true);
    }

    private void replaceFragment(int numb,Fragment frag,String tag, boolean addToBackStack){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment tmp = getSupportFragmentManager().findFragmentByTag(tag);
        ft.replace(R.id.container, (tmp != null ? tmp : frag),tag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if (addToBackStack) ft.addToBackStack(null);
        ft.commit();
        onSectionAttached(numb);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        int numb = position+1;
        getSupportFragmentManager().popBackStack();
        switch (numb) {
            case 1:
                replaceFragment(numb,CardListFragment.newInstance(mHomeCards),HOME_FRAG_TAG,false);
                break;
            case 2:
                replaceFragment(numb,ClassViewPagerFragment.newInstance(mRegion),CLASSES_FRAG_TAG);
                break;
            case 3:
                SimpleDialogFragment.createBuilder(this,
                        getSupportFragmentManager()).setTitle("NYI").setMessage("Not Yet Implemented").show();
                break;
            case 4:
                replaceFragment(numb,CardListFragment.newInstance(mTestingCards),TESTING_FRAG_TAG);
                break;
            case 5:
                replaceFragment(numb,CardListFragment.newInstance(mResourceCards),RESOURCES_FRAG_TAG);
                break;
            case 6:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                break;
            case 7:
                //TODO: About Nav Item
                break;
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

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        *//*switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }*//*
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClassPagerFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentInteraction(String id) {

    }

}
