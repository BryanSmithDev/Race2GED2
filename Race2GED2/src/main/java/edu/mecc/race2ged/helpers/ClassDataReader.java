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


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import edu.mecc.race2ged.JSON.*;
import edu.mecc.race2ged.R;

/** ClassDataReader loads JSON Class data and parses it to the associated objects to be easily used.
 *
 * @author Bryan Smith
 */
public class ClassDataReader extends AsyncTask<Integer, Void, Region> {
    private Context context;

    /**
     * Creates a new asynchronous task. This constructor must be invoked on the UI thread.
     */
    public ClassDataReader(Context context) {
        super();
        this.context = context;
    }

    //DEBUG
    @Override
    protected void onPostExecute(Region region) {
        Log.d(this.getClass().getSimpleName(),region.toString());
    }

    /**
     * Determines which data file to read, and then parses the JSON class data. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The region number to retrieve.
     * @return Region object for the specified region.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected Region doInBackground(Integer... params) {
        String filePath = context.getFilesDir().getAbsolutePath()+
                "/"+context.getString(R.string.class_data_file_name);
        File file = new File(filePath);
        if(file.exists()) {
            Log.d(this.getClass().getSimpleName(),filePath+" => Exists. Parsing it.");
            try {
                return loadJsonFromInternal(params[0]);
            } catch (IOException e) {
                Log.e(this.getClass().getSimpleName(),"Error loading JSON from internal storage.\n"+e.toString());
            }
        } else {
            Log.d(this.getClass().getSimpleName(),filePath+" => Does not exist. Parsing built in data.");
            try {
                return loadJsonFromPrePackaged(params[0]);
            } catch (IOException e) {
                Log.e(this.getClass().getSimpleName(),"Error loading JSON from pre-packaged file.\n"+e.toString());
            }
        }
        return null;
    }

    /**
     * Creates objects from a JSON file that has been saved to internal storage.
     * @param regionNumb The region number to retrieve.
     * @return Region object for the specified region.
     * @throws IOException
     */
    protected Region loadJsonFromInternal(int regionNumb) throws IOException{
        FileInputStream iS = null;
        Reader reader = null;
        Region region =  null;
        try{
            iS = context.openFileInput(context.getString(R.string.class_data_file_name));
            reader = new InputStreamReader(iS, "UTF-8");
            Gson gson = new GsonBuilder().create();
            State state = gson.fromJson(reader, State.class);
            if (state != null) region = state.getRegion().get(regionNumb);
            else throw new NullPointerException();
        } catch (Exception e) {
            Log.e(this.getClass().getSimpleName(),"GSON Error constructing objects from JSON.\n"+e.toString());
        } finally {
            if (iS != null)
                iS.close();
            if (reader != null)
                reader.close();
        }
        Log.d(this.getClass().getSimpleName(),"Done Parsing");
        return region;
    }

    /**
     * Creates objects from a JSON file that is pre-packaged with the App. (RAW)
     * @param regionNumb The region number to retrieve.
     * @return Region object for the specified region.
     * @throws IOException
     */
    protected Region loadJsonFromPrePackaged(int regionNumb) throws IOException{
        InputStream iS = null;
        Reader reader = null;
        Region region =  null;
        try{
            iS = context.getResources().openRawResource(R.raw.classschedule);
            reader = new InputStreamReader(iS, "UTF-8");
            Gson gson = new GsonBuilder().create();
            State state = gson.fromJson(reader, State.class);
            if (state != null) region = state.getRegion().get(regionNumb);
            else throw new NullPointerException();
        } catch (Exception e) {
            Log.e(this.getClass().getSimpleName(),"GSON Error constructing objects from JSON.\n"+e.toString());
        } finally {
            if (iS != null)
                iS.close();
            if (reader != null)
                reader.close();
        }
        Log.d(this.getClass().getSimpleName(),"Done Parsing");
        return region;
    }


}
