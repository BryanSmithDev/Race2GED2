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
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import edu.mecc.race2ged.GEDApplication;
import edu.mecc.race2ged.JSON.*;
import edu.mecc.race2ged.JSON.Class;
import edu.mecc.race2ged.R;
import edu.mecc.race2ged.alarm.Alarm;
import edu.mecc.race2ged.alarm.AlarmBootReceiver;
import edu.mecc.race2ged.alarm.AlarmReceiver;

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
    private static Typeface sRobotoBold;

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
     * Determine if a string is empty. A space is also considered empty.
     * @param str The string to check for emptiness.
     * @return True if the string is empty. False otherwise.
     */
    public static boolean isStringEmpty(String str){
        if (TextUtils.isEmpty(str) || str.equals(" ")){
            return true;
        }
        return false;
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
     * @param style The style for the font face. i.e Typeface.BOLD
     */
    public static void setRobotoThin(Context context, View view, int style) {
        if (sRobotoThin == null) {
            sRobotoThin = Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf");
        }
        setFont(view, sRobotoThin, style);
    }

    /**
     * Sets the font of a view to Roboto-Light
     * @param context The context of the activity.
     * @param view The view that will use Roboto-Light
     */
    public static void setRobotoThin(Context context, View view) {
        setRobotoThin(context, view, Typeface.NORMAL);
    }

    /**
     * Sets the font of a view to Roboto-Bold
     * @param context The context of the activity.
     * @param view The view that will use Roboto-Bold
     */
    public static void setRobotoBold(Context context, View view) {
        if (sRobotoBold == null) {
            sRobotoBold = Typeface.createFromAsset(context.getAssets(), "Roboto-Bold.ttf");
        }
        setFont(view, sRobotoBold);
    }

    /**
     * Sets the font of a view.
     * @param view The view that will use defined Typeface
     * @param robotoTypeFace The font typeface to use.
     */
    private static void setFont(View view, Typeface robotoTypeFace) {
        setFont(view, robotoTypeFace, Typeface.NORMAL);
    }

    /**
     * Sets the font of a view.
     * @param view The view that will use defined Typeface
     * @param robotoTypeFace The font typeface to use.
     * @param style The style for the font face. i.e Typeface.BOLD
     */
    private static void setFont(View view, Typeface robotoTypeFace, int style) {
        if (view instanceof ViewGroup) {
            int count = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < count; i++) {
                setFont(((ViewGroup) view).getChildAt(i), robotoTypeFace);
            }
        } else if (view instanceof TextView) {
            ((TextView) view).setTypeface(robotoTypeFace, style);
        }
    }

    /**
     * Send an email with supplied address, subject, and content.
     * @param emailAddress To: Email Address
     * @param emailSubject Email Subject
     * @param emailBody Email Body Content
     */
    public static void sendEmail(Context context,String emailAddress, String emailSubject, String emailBody) {
        try {
            final Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("plain/html");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {emailAddress});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);
            context.startActivity(Intent.createChooser(emailIntent, "Select your Email App:"));
        } catch (Exception e) {
            Log.e(context.getClass().getSimpleName(),"Error sending email. Email app installed? - "+e.getMessage());
        }
    }

    /**
     * Enables the alarm boot receiver.
     * @param context The context of the activity.
     */
    public static void enableAlarmReceiver(Context context){
        ComponentName receiver = new ComponentName(context, AlarmBootReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    /**
     * Disables the alarm boot receiver.
     * @param context The context of the activity.
     */
    public static void disableAlarmReceiver(Context context){
        ComponentName receiver = new ComponentName(context, AlarmBootReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    /**
     * Create a notification and display it.
     * @param context The context for the notification.
     * @param iconID The ID of the icon drawable for the notification
     * @param contentTitle The notification title.
     * @param contentText The body of the notification.
     */
    public static void createNotification(Context context,int iconID,String contentTitle, String contentText){

        final Intent emptyIntent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, emptyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(iconID)
                        .setContentTitle(contentTitle)
                        .setContentText(contentText)
                        .setContentIntent(pendingIntent)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder.build());
    }

    /**
     * Create a notification to remind that class is about to start and display it.
     * @param context The context for the notification.
     * @param contentTitle The notification title.
     * @param contentText The body of the notification.
     */
    public static void createClassNotification(Context context,String contentTitle, String contentText){

        final Intent emptyIntent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, emptyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_noti_mortarboard)
                        .setContentTitle(contentTitle)
                        .setContentText(contentText)
                        .setContentIntent(pendingIntent)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .addAction(R.drawable.ic_stat_map_marker,"Maps",pendingIntent) //TODO: Add intents here for button actions
                        .addAction(R.drawable.ic_stat_email,"Email",pendingIntent)
                        .addAction(R.drawable.ic_stat_phone,"Call",pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder.build());
    }

    public static void createClassAlarm(Context context,Class tempClass) {
        AlarmManager alarmMgr;
        PendingIntent alarmIntent;

        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent;
        Calendar calendar = Calendar.getInstance();

        for(Date obj :(tempClass).getAlarms()) {
            intent = new Intent(context, AlarmReceiver.class);
            alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            calendar.set(Calendar.DAY_OF_WEEK, obj.getDay()+1);
            calendar.set(Calendar.HOUR_OF_DAY, obj.getHours());
            calendar.set(Calendar.MINUTE, obj.getMinutes());
            if(calendar.getTimeInMillis() < System.currentTimeMillis()) {
                calendar.add(Calendar.DATE, 7);
            }

            Log.d(context.getClass().getSimpleName(),calendar.getTime().toString());
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY * 7, alarmIntent);
        }
    }

}