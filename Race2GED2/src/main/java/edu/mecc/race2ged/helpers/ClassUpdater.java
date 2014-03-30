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

import edu.mecc.race2ged.R;

/**
 * Created by Bryan on 3/13/14.
 */
public class ClassUpdater {
    private SettingsHelper settings;
    private Context context;


    public ClassUpdater(Context cont) {
        context = cont;
        settings = new SettingsHelper(context);
    }

    public void checkForNewVersion() {
        if (settings.getCheckForUpdates()) {
            Utils.showToastOnUiThread(context, R.string.checking_for_class_updates);
            int connection = Utils.getNetworkStatus(context);
            if (connection != Utils.NO_CONNECTION) {
                if (settings.getCheckOnWifiOnly()) {
                    if (connection == Utils.WIFI) {
                        //TODO: Retrieve JSON file
                        Utils.showToastOnUiThread(context, "Retrieved via WIFI");
                    } else {
                        Utils.showToastOnUiThread(context, R.string.error_no_wifi);
                    }
                } else {
                    //TODO: Retrieve JSON file
                    if (connection == Utils.WIFI)
                        Utils.showToastOnUiThread(context, "Retrieved via WIFI-2");
                    if (connection == Utils.MOBILE_DATA)
                        Utils.showToastOnUiThread(context, "Retrieved via Mobile Data");
                }
            } else {
                Utils.showToastOnUiThread(context, R.string.error_no_connection);
            }
        }
    }
}
