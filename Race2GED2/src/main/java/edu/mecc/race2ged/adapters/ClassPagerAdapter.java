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

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * The <code>ClassPagerAdapter</code> serves the <code>ClassesFragment</code> per county when paging.
 *
 * @see edu.mecc.race2ged.fragments.ClassesFragment
 *
 * @author Bryan Smith
 */
public class ClassPagerAdapter extends FragmentPagerAdapter {

    private List<String> titles;
    private List<Fragment> fragments;

    /**
     * Constructs the <code>ClassPagerAdapter</code>
     *
     * @param fm FragmentManager to manage each <code>ClassesFragment</code>
     * @param titles List of titles for each of the fragments.
     * @param fragments List of the fragments to use.
     *
     * @return A new instance of fragment ClassesFragment.
     */
    public ClassPagerAdapter(FragmentManager fm,List<String> titles, List<Fragment> fragments) {
        super(fm);
        setFragments(fragments);
        setTitles(titles);
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
     */
    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    /**
     * This method may be called by the ViewPager to obtain a title string
     * to describe the specified page. This method may return null
     * indicating no title for this page. The default implementation returns
     * null.
     *
     * @param position The position of the title requested
     * @return A title for the requested page
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return getTitles().get(position % getTitles().size());
    }

    /* (non-Javadoc)
         * @see android.support.v4.view.PagerAdapter#getCount()
         */
    @Override
    public int getCount() {
        return this.fragments.size();
    }

    /**
     * Get the list of current fragment titles.
     * @return List of current fragment titles.
     */
    public List<String> getTitles() {
        return titles;
    }

    /**
     * Import the list of titles from another variable.
     * @param titles The list of titles to import.
     */
    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    /**
     * Get the list of fragments being used.
     * @return List of fragments.
     */
    public List<Fragment> getFragments() {
        return fragments;
    }

    /**
     * Import the list of fragments from another variable.
     * @param fragments The list of fragments to import.
     */
    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }
}