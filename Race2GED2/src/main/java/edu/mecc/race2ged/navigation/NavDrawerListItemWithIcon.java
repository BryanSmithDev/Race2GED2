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
 * Created by Bryan on 3/16/14.
 */
public class NavDrawerListItemWithIcon extends NavDrawerListItem {
    public NavDrawerListItemWithIcon(int textViewID, String text, int iconResourceID) {
        super(R.layout.side_menu_list_item_with_icon, textViewID, text, iconResourceID);
    }
}
