package edu.mecc.race2ged.navigation;

import edu.mecc.race2ged.R;

/**
 * Created by Bryan on 3/16/14.
 */
public class NavDrawerListItemNoIcon extends NavDrawerListItem {
    public NavDrawerListItemNoIcon(int textViewID, String text) {
        super(R.layout.side_menu_list_item_without_icon, textViewID, text, -1);
    }
}
