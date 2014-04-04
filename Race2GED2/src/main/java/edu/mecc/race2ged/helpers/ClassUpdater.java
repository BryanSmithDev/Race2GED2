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
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import edu.mecc.race2ged.R;

/**
 * ClassUpdater provides methods for retrieving, storing, and managing Class Schedule data retrieved
 * from www.race2ged.org
 *
 * @author Bryan Smith
 */
public class ClassUpdater extends AsyncTask<Integer, Void, Boolean> {
    private SettingsHelper settings;
    private Context context;
    private String version;

    /**
     * Creates a new asynchronous task. This constructor must be invoked on the UI thread.
     */
    public ClassUpdater(Context context) {
        super();
        this.context = context;
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected Boolean doInBackground(Integer... params) {
        if (canUpdate()) {
            if (retrieveRemoteVersion(params[0]) > settings.getClassDataVersion()) {
                Utils.showToastOnUiThread(context, "Remote Class Info Is Newer, Downloading."); //DEBUG
                try { return updateClassData(); } catch (IOException e) {e.printStackTrace();}
            }
        }
        return false;
    }

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p/>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param aBoolean The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (aBoolean) {
            settings.savePreference(SettingsHelper.CLASS_DATA_VERSION, version);
            Utils.showToastOnUiThread(context, "File downloaded."); //DEBUG
        }
    }

    /**
     * Retrieves the JSON Class data from the web server.
     * @throws IOException
     */
    private boolean updateClassData() throws IOException{
            URL url = new URL("http://race2ged.org/wp-content/themes/adulted/ClassSchedule.json");
            boolean value = false;
            InputStream input = null;
            FileOutputStream output = null;

            try {
                String outputName = "ClassSchedule.json";
                input = url.openConnection().getInputStream();
                output = context.openFileOutput(outputName, Context.MODE_PRIVATE);

                int read;
                byte[] data = new byte[1024];
                while ((read = input.read(data)) != -1)
                    output.write(data, 0, read);

                value=true;
            } finally {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            }
        return value;
    }

    /**
     * Retrieves the current version of the class data on the web server
     * @param region The number for the region to get the version of.
     * @return
     */
    private int retrieveRemoteVersion(int region) {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://race2ged.org/wp-content/themes/adulted/getClassScheduleVersion.php?region="+region);
        try {
            HttpResponse execute = client.execute(httpGet);
            InputStream content = execute.getEntity().getContent();

            BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
            String s; String response = "";
            while ((s = buffer.readLine()) != null) {
                response += s;
            }
            Utils.showToastOnUiThread(context,response);
            version = response.trim();
            return Integer.parseInt(response.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Determine if we can check for updates or not.
     * @return
     */
    public Boolean canUpdate() {
        settings = new SettingsHelper(context);
        if (settings.getCheckForUpdates()) {
            Utils.showToastOnUiThread(context, R.string.checking_for_class_updates);
            int connection = Utils.getNetworkStatus(context);
            if (connection != Utils.NO_CONNECTION) {
                if (settings.getCheckOnWifiOnly()) {
                    if (connection == Utils.WIFI) {
                        //TODO: Retrieve JSON file
                        Utils.showToastOnUiThread(context, "Retrieved via WIFI"); //DEBUG
                        return true;
                    } else {
                        Utils.showToastOnUiThread(context, R.string.error_no_wifi); //DEBUG
                        return false;
                    }
                } else {
                    //TODO: Retrieve JSON file
                    if (connection == Utils.WIFI){
                        Utils.showToastOnUiThread(context, "Retrieved via WIFI-2"); //DEBUG
                        return true;
                    }
                    if (connection == Utils.MOBILE_DATA) {
                        Utils.showToastOnUiThread(context, "Retrieved via Mobile Data"); //DEBUG
                        return true;
                    }
                }
            } else {
                Utils.showToastOnUiThread(context, R.string.error_no_connection); //DEBUG
                return false;
            }
        }
        return false;
    }
}
