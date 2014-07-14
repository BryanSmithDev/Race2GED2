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

package edu.mecc.race2ged.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import edu.mecc.race2ged.R;

/**
 * SettingsHelper provides methods for saving and retrieving preferences and other saved data.
 *
 * @author Bryan Smith
 */
public class SettingsHelper {

    /**
     * Preference Names
     */
    public static String CLASS_DATA_VERSION;
    public static String CHECK_CLASS_DATA_ONLY_ON_WIFI;
    public static String CHECK_CLASS_DATA_FOR_NEW_VERSIONS;
    public static String CHECK_CLASS_DATA_FOR_NEW_VERSIONS_AT_STARTUP;
    public static String SHOW_ANIMATIONS;

    private SharedPreferences settings;

    /**
     * Constructs the SettingsHelper
     * @param context The context of the activity.
     */
    public SettingsHelper(Context context) {
        settings = PreferenceManager.getDefaultSharedPreferences(context);
        CLASS_DATA_VERSION = context.getResources().getString(R.string.pref_class_data_version);
        CHECK_CLASS_DATA_ONLY_ON_WIFI = context.getResources().getString(R.string.pref_check_class_data_only_on_wifi);
        CHECK_CLASS_DATA_FOR_NEW_VERSIONS = context.getResources().getString(R.string.pref_check_class_data_for_new_versions);
        CHECK_CLASS_DATA_FOR_NEW_VERSIONS_AT_STARTUP = context.getResources().getString(R.string.pref_check_class_data_for_new_versions_at_startup);
        SHOW_ANIMATIONS = context.getResources().getString(R.string.pref_show_animations);
    }

    /**
     * What version of class data are we at?
     * @return Saved Class Data Version number. Or 1 if it does not exist.
     */
    public int getClassDataVersion() {
        return Integer.parseInt(settings.getString(CLASS_DATA_VERSION, "1"));
    }

    /**
     * Check for updates only on Wifi?
     * @return Saved value indicating whether or not to check for updates only on
     * WiFi. Or true if it does not exist.
     */
    public boolean getCheckOnWifiOnly() {
        return settings.getBoolean(CHECK_CLASS_DATA_ONLY_ON_WIFI, true);
    }

    /**
     * Check for updates at all?
     * @return saved value indicating whether or not to check for updates. Or True if it does not
     * exist.
     */
    public boolean getCheckForUpdates() {
        return settings.getBoolean(CHECK_CLASS_DATA_FOR_NEW_VERSIONS, true);
    }

    /**
     * Check for updates at startup?
     * @return saved value indicating whether or not to check for updates. Or True if it does not
     * exist.
     */
    public boolean getCheckForUpdatesAtStartup() {
        return (getCheckForUpdates() && settings.getBoolean(CHECK_CLASS_DATA_FOR_NEW_VERSIONS_AT_STARTUP, true));
    }

    /**
     * Show the card slide in animations?
     * @return saved value indicating whether or not to show animations. Or True if it does not
     * exist.
     */
    public boolean getShowAnimations() {
        return settings.getBoolean(SHOW_ANIMATIONS,true);
    }

    /**
     * Saves a String preference and the accompanying value.
     * @param preference The name of the preference.
     * @param value The string value stored in the preference.
     */
    public void savePreference(String preference, String value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(preference, value);
        editor.commit();
    }

    /**
     * Removes a saved preference.
     * @param preference The name of the preference to remove.
     */
    public void removePreference(String preference) {
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(preference);
        editor.commit();
    }
}
