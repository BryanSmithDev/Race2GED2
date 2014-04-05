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
import android.util.Log;

import java.io.File;

import edu.mecc.race2ged.R;

/**
 * Created by Bryan on 4/4/2014.
 */
public class ClassDataReader extends AsyncTask<Integer, Void, Boolean> {
    private Context context;

    /**
     * Creates a new asynchronous task. This constructor must be invoked on the UI thread.
     */
    public ClassDataReader(Context context) {
        super();
        this.context = context;
    }

    /**
     * TODO
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
        //TODO
    }

    /**
     * Determines which data file to read, and then parses the JSON class data. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params TODO
     * @return TODO
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected Boolean doInBackground(Integer... params) {
        String filePath = context.getFilesDir().getAbsolutePath()+
                "/"+context.getString(R.string.class_data_file_name);
        File file = new File(filePath);
        if(file.exists()) {
            Log.d(this.getClass().getSimpleName(),filePath+" => Exists. Parsing it.");
            //TODO: Parse JSON from internally saved file.
        } else {
            Log.d(this.getClass().getSimpleName(),filePath+" => Does not exist. Parsing built in data.");
            //TODO: Parse JSON from pre-packaged file.
        }
        return true;
    }
}
