package edu.mecc.race2ged.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import edu.mecc.race2ged.helpers.Utils;

/**
 * @author Bryan Smith
 * @date 8/13/2014.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Utils.createClassNotification(context,"Test","Alarms working.");
    }
}
