package edu.mecc.race2ged.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SettingsHelper {

    public static String CLASS_DATA_VERSION = "class_data_version";
    public static String CHECK_CLASS_DATA_ONLY_ON_WIFI = "class_data_wifi";
    public static String CHECK_CLASS_DATA_FOR_NEW_VERSIONS = "class_data_checking";

    private SharedPreferences settings;

    public SettingsHelper(Context context) {

        settings = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public int getClassDataVersion() {
        return Integer.parseInt(settings.getString(CLASS_DATA_VERSION, "1"));
    }

    public boolean getCheckOnWifiOnly() {
        return settings.getBoolean(CHECK_CLASS_DATA_ONLY_ON_WIFI, true);
    }

    public boolean getCheckForUpdates() {
        return settings.getBoolean(CHECK_CLASS_DATA_FOR_NEW_VERSIONS, true);
    }

    private void savePreference(String preference, String value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(preference, value);
        editor.commit();
    }

    private void removePreference(String preference) {
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(preference);
        editor.commit();
    }
}
