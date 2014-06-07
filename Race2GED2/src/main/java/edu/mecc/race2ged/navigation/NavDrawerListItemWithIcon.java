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

import edu.mecc.race2ged.R;

/**
 * <code>NavDrawerListItemWithIcon</code> represents a list item in the Navigation Drawer that uses an icon.
 * This class is primarily used for the secondary navigation drawer items. Uses a predefined View
 * ID.
 *
 * @see edu.mecc.race2ged.navigation.NavDrawerListItem
 *
 * @author Bryan Smith
 */
public class NavDrawerListItemWithIcon extends NavDrawerListItem {
    /**
     * Construct the <code>NavDrawerListItemWithIcon</code>
     * @param textViewID The ID of the Text View that will have a value inserted.
     * @param text  The text value to insert into the text view.
     * @param iconResourceID The resource ID of the icon to display, if any.
     */
    public NavDrawerListItemWithIcon(int textViewID, String text, int iconResourceID) {
        super(R.layout.side_menu_list_item_with_icon, textViewID, text, iconResourceID);
    }
}
