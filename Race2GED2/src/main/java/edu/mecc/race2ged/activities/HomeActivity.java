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
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

import edu.mecc.race2ged.GEDApplication;
import edu.mecc.race2ged.JSON.Region;
import edu.mecc.race2ged.R;
import edu.mecc.race2ged.cards.ExpandableCard;
import edu.mecc.race2ged.cards.TextCard;
import edu.mecc.race2ged.fragments.CardListFragment;
import edu.mecc.race2ged.fragments.ClassViewPagerFragment;
import edu.mecc.race2ged.helpers.Utils;
import edu.mecc.race2ged.navigation.DrawerLayout;
import edu.mecc.race2ged.navigation.NavigationDrawerFragment;
import edu.mecc.race2ged.widgets.Card;
import edu.mecc.race2ged.widgets.Header;

/**
 * HomeActivity is the primary activity of the Race2GED app and acts as the launcher. Most content
 * is displayed here using fragments.
 *
 * @author Bryan Smith
 */
public class HomeActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {


    private static final String ARG_SELECTED = "selectedParam";
    private static final String ARG_REGION = "regionParam";

    private static final String HOME_FRAG_TAG = "homeFrag";
    private static final String CLASSES_FRAG_TAG = "classesFrag";
    private static final String PRACTICE_FRAG_TAG = "practiceFrag";
    private static final String TESTING_FRAG_TAG = "testingFrag";
    private static final String RESOURCES_FRAG_TAG = "resourcesFrag";
    private static final String FEEDBACK_FRAG_TAG = "feedbackFrag";
    private static final String ABOUT_FRAG_TAG = "aboutFrag";


    private ArrayList<String> navTitles = new ArrayList<String>();
    private Region mRegion = null;
    private ArrayList<View> mHomeCards = new ArrayList<View>();
    private ArrayList<View> mResourceCards  = new ArrayList<View>();
    private ArrayList<View> mTestingCards  = new ArrayList<View>();
    private ArrayList<View> mAboutCards  = new ArrayList<View>();


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

        //DEBUG: Temp enable alarm boot receiver
        Utils.enableAlarmReceiver(this);
        Utils.createClassNotification(this,"Class is about to start!","Class starts at 5:30pm. Hope to see you!");


        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, CardListFragment.newInstance(mHomeCards)).commit();

        try {
            navTitles.addAll(Arrays.asList(getResources().getStringArray(R.array.navPrimaryTitles)));
            navTitles.addAll(Arrays.asList(getResources().getStringArray(R.array.navSecondaryTitles)));
        } catch (Resources.NotFoundException e) {
            Log.e("HomeActivity","Could not add nav titles to list. - "+e.getMessage());
        }

        Utils.createClassAlarm(this,mRegion.getCounties().get(0).getClasses().get(0));
        Log.d("Home", mRegion.getCounties().get(0).getClasses().get(0).toString());
    }

    private void populateCards() {
        populateHomeCards();
        populateResourcesCards();
        populateTestingCards();
        populateAboutCards();
    }

    private void populateHomeCards() {
        mHomeCards.add(new Header(this,getResources().getString(R.string.greeting_header)));
        mHomeCards.add(new TextCard(this,getResources().getString(R.string.greeting)));
        mHomeCards.add(new Header(this,getResources().getString(R.string.facebook_feed_header)));
        mHomeCards.add(new TextCard(this,"TODO: Add facebook feed"));
    }

    private void populateResourcesCards() {
        mResourceCards.add(new Header(this,"Category 1"));
        mResourceCards.add(new TextCard(this,"Resource Item 1"));
        mResourceCards.add(new TextCard(this,"Resource Item 2"));
        mResourceCards.add(new TextCard(this,"Resource Item 3"));
        mResourceCards.add(new Header(this,"Category 2"));
        mResourceCards.add(new TextCard(this,"Resource Item 4"));
    }

    private void populateTestingCards() {
        mTestingCards.add(new Header(this, getResources().getString(R.string.testing_content_areas)));
        mTestingCards.add(new ExpandableCard(this,getResources().getString(R.string.testing_rla),getResources().getString(R.string.testing_rla_content)));
        mTestingCards.add(new ExpandableCard(this,getResources().getString(R.string.testing_math),getResources().getString(R.string.testing_math_content)));
        mTestingCards.add(new ExpandableCard(this,getResources().getString(R.string.testing_social_studies),getResources().getString(R.string.testing_social_studies_content)));
        mTestingCards.add(new ExpandableCard(this,getResources().getString(R.string.testing_science),getResources().getString(R.string.testing_science_content)));
        mTestingCards.add(new Header(this, getResources().getString(R.string.testing_centers_title)));
        mTestingCards.add(new TextCard(this,"TODO: Add testing center info. (Maybe on tabs to organize? This page will have a lot of info for one screen)"));
        mTestingCards.add(new Header(this, getResources().getString(R.string.testing_cost_title)));
        mTestingCards.add(new TextCard(this,"TODO: Add testing cost info. Again, most likely will me moved to a related tab."));
        mTestingCards.add(new Header(this, getResources().getString(R.string.testing_passing_title)));
        mTestingCards.add(new TextCard(this,"TODO: Add passing score info."));
    }

    private void populateAboutCards(){
        mAboutCards.add(new ExpandableCard(this,getResources().getString(R.string.about_contact),"TODO: Add contact and social media"));
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
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        int numb = position+1;
        getSupportFragmentManager().popBackStack();
        switch (numb) {
            case 1:
                CardListFragment frag = CardListFragment.newInstance(mHomeCards);
                frag.setTitle(getResources().getString(R.string.app_name));
                replaceFragment(numb, frag, HOME_FRAG_TAG, false);
                break;
            case 2:
                replaceFragment(numb,ClassViewPagerFragment.newInstance(mRegion),CLASSES_FRAG_TAG);
                break;
            case 3:
                frag = CardListFragment.newInstance(mResourceCards);
                frag.setTitle(navTitles.get(numb-1));
                replaceFragment(numb, frag, RESOURCES_FRAG_TAG);
                break;
            case 4:
                ArrayList<View> practiceCards = new ArrayList<View>();
                practiceCards.add(new TextCard(this, "TODO: Practice test questions"));
                frag = CardListFragment.newInstance(practiceCards);
                frag.setTitle(navTitles.get(numb-1));
                replaceFragment(numb, frag, PRACTICE_FRAG_TAG);
                break;
            case 5:
                frag = CardListFragment.newInstance(mTestingCards);
                frag.setTitle(navTitles.get(numb-1));
                replaceFragment(numb, frag, TESTING_FRAG_TAG);
                break;
            case 6:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                break;
            case 7:
                ArrayList<View> feedbackCards = new ArrayList<View>();
                feedbackCards.add(new TextCard(this,"TODO: Add feedback form."));
                frag = CardListFragment.newInstance(feedbackCards);
                frag.setTitle(navTitles.get(numb-1));
                replaceFragment(numb,frag,FEEDBACK_FRAG_TAG);
                break;
            case 8:
                frag = CardListFragment.newInstance(mAboutCards);
                frag.setTitle(navTitles.get(numb-1));
                replaceFragment(numb,frag,ABOUT_FRAG_TAG);
                break;
            default:
                break;
        }
    }

}
