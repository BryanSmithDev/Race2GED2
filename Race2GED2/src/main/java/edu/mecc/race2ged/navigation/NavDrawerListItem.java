package edu.mecc.race2ged.navigation;

/**
 * Created by Bryan on 3/16/14.
 */
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
