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

package edu.mecc.race2ged.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import edu.mecc.race2ged.GEDApplication;
import edu.mecc.race2ged.R;
import edu.mecc.race2ged.helpers.ClassDataReader;
import edu.mecc.race2ged.helpers.ClassDataUpdater;

/**
 * SplashScreen is shown on launch of the app and depending on settings, checks for updates of the
 * class schedule, downloads and installs them.
 *
 * @author Bryan Smith
 */
public class SplashScreen extends Activity {

    // Splash screen timer (milliseconds)
    private static int SPLASH_TIME_OUT = 2000;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //If user wants to check for updates then check.
        if (GEDApplication.getSettingsHelper().getCheckForUpdatesAtStartup()) {
            ClassDataUpdater classDataUpdater = new ClassDataUpdater(this,0);
            try {
                classDataUpdater.execute(0).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Load the class data
        ClassDataReader classDataReader = new ClassDataReader(this,0);
        try {
            GEDApplication.setRegionData(classDataReader.execute(0).get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Delayed time period to show logos
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}