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
 * <code>NavDrawerListItemNoIcon</code> represents a list item in the Navigation Drawer that does not use an icon
 * . This class is primarily used for the primary navigation drawer items. Uses a predefined View
 * ID.
 *
 * @see edu.mecc.race2ged.navigation.NavDrawerListItem
 *
 * @author Bryan Smith
 */
public class NavDrawerListItemNoIcon extends NavDrawerListItem {
    /**
     * Constructs the <code>NavDrawerListItemNoIcon</code>
     * @param textViewID The ID of the Text View that will have a value inserted.
     * @param text  The text value to insert into the text view.
     */
    public NavDrawerListItemNoIcon(int textViewID, String text) {
        super(R.layout.side_menu_list_item_without_icon, textViewID, text, -1);
    }
}
