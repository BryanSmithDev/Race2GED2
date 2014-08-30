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
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Set;

import edu.mecc.race2ged.GEDApplication;
import edu.mecc.race2ged.JSON.Region;
import edu.mecc.race2ged.JSON.State;
import edu.mecc.race2ged.R;
import edu.mecc.race2ged.alarm.Alarm;
import edu.mecc.race2ged.alarm.AlarmSet;

/**
 * SettingsHelper provides methods for saving and retrieving preferences and other saved data.
 *
 * @author Bryan Smith
 */
public class SettingsHelper {

    /**
     * Preference Names
     */
    public static String CLASS_DATA;
    public static String CLASS_DATA_VERSION;
    public static String CHECK_CLASS_DATA_ONLY_ON_WIFI;
    public static String CHECK_CLASS_DATA_FOR_NEW_VERSIONS;
    public static String CHECK_CLASS_DATA_FOR_NEW_VERSIONS_AT_STARTUP;
    public static String SHOW_ANIMATIONS;
    public static String ALARMS;

    private SharedPreferences settings;
    private Region savedClassData = null;

    /**
     * Constructs the SettingsHelper
     * @param context The context of the activity.
     */
    public SettingsHelper(Context context) {
        settings = PreferenceManager.getDefaultSharedPreferences(context);
        CLASS_DATA=context.getResources().getString(R.string.pref_class_data);
        CLASS_DATA_VERSION = context.getResources().getString(R.string.pref_class_data_version);
        CHECK_CLASS_DATA_ONLY_ON_WIFI = context.getResources().getString(R.string.pref_check_class_data_only_on_wifi);
        CHECK_CLASS_DATA_FOR_NEW_VERSIONS = context.getResources().getString(R.string.pref_check_class_data_for_new_versions);
        CHECK_CLASS_DATA_FOR_NEW_VERSIONS_AT_STARTUP = context.getResources().getString(R.string.pref_check_class_data_for_new_versions_at_startup);
        SHOW_ANIMATIONS = context.getResources().getString(R.string.pref_show_animations);
        ALARMS = context.getResources().getString(R.string.pref_alarms);
    }

    /**
     * Get the class data that is saved locally.
     * @return Region class data object that is saved to the device in JSON.
     */
    public Region getSavedClassData() {
        Region region = null;
        if (savedClassData == null) {
            try{
                String savedClassJSON = settings.getString(CLASS_DATA,null);
                if (savedClassJSON == null) throw new NullPointerException();
                Gson gson = Utils.getGSONBuilder();
                region = gson.fromJson(savedClassJSON, Region.class);
            } catch (Exception e) {
                Log.e(this.getClass().getSimpleName(),"GSON Error constructing objects from JSON.\n"+e.toString());
            }
            Log.d(this.getClass().getSimpleName(),"Class data retrieved from saved JSON.");
        } else {
            region = savedClassData;
        }
        return region;
    }

    /**
     * Save the Region data as JSON to the preferences.
     * @param region The Region object to save as JSON to preferences
     */
    public void saveClassData(Region region){
        if (region == null) {
            savePreference(CLASS_DATA,null);
            Log.d(this.getClass().getSimpleName(),"Deleting saved class data since null region was passed.");
            return;
        }
        try {
            String json = region.toJSON();
            savePreference(CLASS_DATA_VERSION,region.getVersion()+"");
            savePreference(CLASS_DATA, json);
        } catch (Exception e){
            Log.e(this.getClass().getSimpleName(),"Error saving region data JSON.\n"+e.toString());
        }
    }

    /**
     * What version of class data are we at?
     * @return Saved Class Data Version number. Or 1 if it does not exist.
     */
    public int getSavedClassDataVersion() {
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

    /**
     * Retrieve saved alarm JSON and parse it to an AlarmSet
     * @return The AlarmSet object that contains the Alarms
     */
    public AlarmSet getAlarms(){
        String alarmsString = settings.getString(ALARMS,"");
        Log.d(getClass().getSimpleName(),"Retrieving saved alarms.");
        if (!Utils.isStringEmpty(alarmsString)) {
            try {
                Gson gson = Utils.getGSONBuilder();
                AlarmSet alarms =  gson.fromJson(alarmsString, AlarmSet.class);
                if (alarms != null){
                    Log.d(getClass().getSimpleName(),"Alarms retrieved and parsed.");
                    Log.d(getClass().getSimpleName(),alarms.toString());
                }
                return  alarms;
            } catch (Exception e){
                Log.e(getClass().getSimpleName(),"Error: Could not retrieve saved alarms from JSON string. - "+e.getMessage());
                return null;
            }
        }
        return null;
    }

    /**
     * Add an alarm
     * @param alarm Alarm to add to the set.
     */
    public void addAlarm(Alarm alarm){
        AlarmSet alarms = getAlarms();
        alarms.addAlarm(alarm);
        saveAlarms(alarms);
    }

    /**
     * Save alarms JSON to shared preferences
     * @param alarms Alarms to save
     */
    private void saveAlarms(AlarmSet alarms) {
        Gson gson = Utils.getGSONBuilder();
        String alarmsString = gson.toJson(alarms);
        if (Utils.isStringEmpty(alarmsString)) savePreference(ALARMS,alarmsString);
    }
}
