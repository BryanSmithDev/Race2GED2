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
