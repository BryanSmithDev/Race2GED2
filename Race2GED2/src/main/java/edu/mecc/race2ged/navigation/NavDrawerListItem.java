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

public class NavDrawerListItem {

    private String textString;
    private int iconResourceID;
    private int textViewID;
    private int viewResourceID;

    public NavDrawerListItem(int viewResourceID, int textViewID, String text, int iconResourceID) {
        setTextViewID(textViewID);
        setViewResourceID(viewResourceID);
        setTextString(text);
        setIconResourceID(iconResourceID);
    }

    public int getIconResourceID() {
        return iconResourceID;
    }

    public void setIconResourceID(int iconResourceID) {
        this.iconResourceID = iconResourceID;
    }

    public String getTextString() {
        return textString;
    }

    public void setTextString(String textString) {
        this.textString = textString;
    }

    public int getTextViewID() {
        return textViewID;
    }

    public void setTextViewID(int textResourceID) {
        this.textViewID = textResourceID;
    }

    public int getViewResourceID() {
        return viewResourceID;
    }

    public void setViewResourceID(int viewResourceID) {
        this.viewResourceID = viewResourceID;
    }
}
