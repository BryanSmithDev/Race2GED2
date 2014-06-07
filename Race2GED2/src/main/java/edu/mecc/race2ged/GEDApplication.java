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

package edu.mecc.race2ged;

import android.app.Application;
import edu.mecc.race2ged.JSON.Region;
import edu.mecc.race2ged.helpers.SettingsHelper;

/**
 * The main application class.
 *
 * @author Bryan Smith
 * @date 5/18/2014.
 */
public class GEDApplication extends Application {
    private static SettingsHelper settingsHelper;
    private static Region regionData;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate() {
        super.onCreate();
        settingsHelper = new SettingsHelper(getApplicationContext());
    }

    /**
     * Get the applications settings helper.
     * @return Applications settings helper. Makes settings simpler.
     */
    public static SettingsHelper getSettingsHelper() {
        return settingsHelper;
    }

    /**
     * Get the currently stored Regional class data.
     * @return Currently stored Regional class data.
     */
    public static Region getRegionData() {
        return regionData;
    }

    /**
     * Set the current region data.
     * @param regionDataT Region data to set to.
     */
    public static void setRegionData(Region regionDataT) {
        regionData = regionDataT;
    }
}
