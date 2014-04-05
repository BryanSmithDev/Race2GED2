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

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utils class includes commonly used methods often relating to system information such
 * as network states or date and time.
 *
 * @author Bryan Smith
 */
public class Utils {

    /**
     * Network States
     */
    public static int NO_CONNECTION = 0;
    public static int WIFI = 1;
    public static int MOBILE_DATA = 2;

    private static Typeface sRobotoThin;

    /**
     * Returns the current system date.
     */
    public static String getDateAndTime() {
        return new SimpleDateFormat("yyyy-MM-dd.HH.mm.ss").format(new Date(System
                .currentTimeMillis()));
    }

    /**
     * Are we connected to WiFi, Mobile Data, or nothing?
     * @return The devices current network state.
     */
    public static int getNetworkStatus(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final android.net.NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final android.net.NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifi != null && wifi.isAvailable() && wifi.isConnected()) return WIFI;
        else if (mobile != null && mobile.isAvailable() && mobile.isConnected()) return MOBILE_DATA;
        return NO_CONNECTION;
    }

    /**
     * Displays a Toast Message on the UI Thread
     * @param context The context of the activity.
     * @param resourceId The resource ID of the text to display in the message.
     */
    public static void showToastOnUiThread(final Context context, final int resourceId) {
        ((Activity) context).runOnUiThread(new Runnable() {

            public void run() {
                Toast.makeText(context, resourceId, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Displays a Toast Message on the UI Thread
     * @param context The context of the activity.
     * @param string The string to display in the message.
     */
    public static void showToastOnUiThread(final Context context, final String string) {
        ((Activity) context).runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(context, string, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Sets the font of a view to Roboto-Light
     * @param context The context of the activity.
     * @param view The view that will use Roboto-Light
     */
    public static void setRobotoThin(Context context, View view) {
        if (sRobotoThin == null) {
            sRobotoThin = Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf");
        }
        setFont(view, sRobotoThin);
    }

    /**
     * Sets the font of a view.
     * @param view The view that will use defined Typeface
     * @param robotoTypeFace The font typeface to use.
     */
    private static void setFont(View view, Typeface robotoTypeFace) {
        if (view instanceof ViewGroup) {
            int count = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < count; i++) {
                setFont(((ViewGroup) view).getChildAt(i), robotoTypeFace);
            }
        } else if (view instanceof TextView) {
            ((TextView) view).setTypeface(robotoTypeFace);
        }
    }
}