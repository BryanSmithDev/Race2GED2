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

package edu.mecc.race2ged.JSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.List;

import edu.mecc.race2ged.helpers.Utils;

/**
 * Wrapper class for JSON Data. Represents a Region object. This will store class information for
 * an entire state region.
 *
 * @author Bryan Smith
 */
public class Region implements Serializable {

    private String name;
    private int version;
    private String date;
    private List<County> counties;

    /**
     * Get the name of the region.
     * @return Name of the region.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the region.
     * @param name The value to set the name to.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the version of the region.
     * @return Region's version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Set the version of the region.
     * @param version Value to set the version to.
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Get the date of the region.
     * @return Date of the region.
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the date of the region.
     * @param date Value to set the region's date to.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Get a list of counties in the region.
     * @return List of counties in the region.
     */
    public List<County> getCounties() {
        return counties;
    }

    /**
     * Sets the list of counties in the region.
     * @param counties List of counties to set for the region.
     */
    public void setCounties(List<County> counties) {
        this.counties = counties;
    }

    /**
     * Human Readable version of the Region object.
     * @return Human readable string of the Region object.
     */
    @Override
    public String toString() {
        return "Region{" +
                "name='" + name + '\'' +
                ", version=" + version +
                ", date='" + date + '\'' +
                ", counties=" + counties +
                '}';
    }

    /**
     * Convert the Region object to JSON string
     * @return The JSON string of the Region object.
     */
    public String toJSON(){
        Gson gson = Utils.getGSONBuilder();
        return gson.toJson(this, Region.class);
    }
}
