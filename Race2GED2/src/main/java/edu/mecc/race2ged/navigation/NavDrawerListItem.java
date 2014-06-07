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

package edu.mecc.race2ged.navigation;

/**
 * <code>NavDrawerListItem</code> represents, and is the base class for, a list item in the Navigation Drawer.
 *
 * @author Bryan Smith
 */
public class NavDrawerListItem {

    private String textString;
    private int iconResourceID;
    private int textViewID;
    private int viewResourceID;

    /**
     * Constructs the <code>NavDrawerListItem</code>
     * @param viewResourceID The view's ID that will represent the list item.
     * @param textViewID The ID of the Text View that will have a value inserted.
     * @param text  The text value to insert into the text view.
     * @param iconResourceID The resource ID of the icon to display, if any.
     */
    public NavDrawerListItem(int viewResourceID, int textViewID, String text, int iconResourceID) {
        setTextViewID(textViewID);
        setViewResourceID(viewResourceID);
        setTextString(text);
        setIconResourceID(iconResourceID);
    }

    /**
     * What is the Icon's ID?
     * @return The resource ID of the Icon.
     */
    public int getIconResourceID() {
        return iconResourceID;
    }

    /**
     * Sets the resource ID of the Icon.
     * @param iconResourceID The resource ID of the icon to display, if any.
     */
    public void setIconResourceID(int iconResourceID) {
        this.iconResourceID = iconResourceID;
    }

    /**
     * What text is this Item displaying?
     * @return The text string for this Item.
     */
    public String getTextString() {
        return textString;
    }

    /**
     * Sets the text of the item.
     * @param textString The text to set for the item.
     */
    public void setTextString(String textString) {
        this.textString = textString;
    }

    /**
     * What is the item's text view ID?
     * @return The ID of the text view.
     */
    public int getTextViewID() {
        return textViewID;
    }

    /**
     * Set the item's text view ID.
     * @param textResourceID The ID to set for the item's text view.
     */
    public void setTextViewID(int textResourceID) {
        this.textViewID = textResourceID;
    }

    /**
     * What is the item's view ID?
     * @return The ID of the item's view.
     */
    public int getViewResourceID() {
        return viewResourceID;
    }

    /**
     * Sets the item's View ID.
     * @param viewResourceID The ID to set for the item's View.
     */
    public void setViewResourceID(int viewResourceID) {
        this.viewResourceID = viewResourceID;
    }
}
