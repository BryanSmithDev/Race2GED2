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
import java.util.Date;
import java.util.List;

import edu.mecc.race2ged.helpers.Utils;

/**
 * Wrapper class for JSON Data. Represents a Class Room. Used in the County Class
 *
 * @author Bryan Smith
 */
public class Class implements Serializable {

    private String name;
    private String location;
    private String address;
    private List<Instructor> instructors;
    private String contact;
    private List<String> days;
    private List<String> times;
    private String url;
    private boolean enabled;
    private boolean alarmsEnabled;
    private List<Date> alarms;

    /**
     * Gets the name of the class.
     * @return Name of the class.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the class.
     * @param name Name to set for the class.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the class's location.
     * @return Class's location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set the class's location.
     * @param location Location to set for the class.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Get the Address of the class.
     * @return Address of the class.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the class.
     * @param address Address to use for the class.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get a list of the class's instructors.
     * @return List of the class's instructors.
     */
    public List<Instructor> getInstructors() {
        return instructors;
    }

    /**
     * Sets the class's instructors.
     * @param instructors List of instructor to use for the class.
     */
    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    /**
     * Get the contact phone number for the class.
     * @return Contact phone number for the class.
     */
    public String getContact() {
        return contact;
    }

    /**
     * Sets the contact phone number for the class.
     * @param contact Value to set the contact phone number to.
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Get a list of the days the class is held on.
     * @return List of days the class is held on.
     */
    public List<String> getDays() {
        return days;
    }

    /**
     * Sets the list of the days the class is held on.
     * @param days List to use of days the class is held on.
     */
    public void setDays(List<String> days) {
        this.days = days;
    }

    /**
     * Get a list of times when the class is held.
     * @return List of times when the class is held.
     */
    public List<String> getTimes() {
        return times;
    }

    /**
     * Sets the list of times when the class is held.
     * @param times List of times to use.
     */
    public void setTimes(List<String> times) {
        this.times = times;
    }

    /**
     * Get the URL of the Google Maps class location.
     * @return URL of the class.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the URL of the class.
     * @param url URL to use for the class.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get if the class is enabled.
     * @return True for enabled, false for disabled.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Set if the class is enabled.
     * @param enabled True for enabled, false for disabled.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Get if the class alarmTime is enabled.
     * @return True for enabled, false for disabled.
     */
    public boolean areAlarmsEnabled() {
        return alarmsEnabled;
    }

    /**
     * Set if the class alarmTime is enabled.
     * @param alarmEnabled True for enabled, false for disabled.
     */
    public void setAlarmsEnabled(boolean alarmEnabled) {
        this.alarmsEnabled = alarmsEnabled;
    }

    /**
     * Get the date object for when the alarmTime will go off.
     * @return Date object
     */
    public List<Date> getAlarms() {
        return alarms;
    }

    public void setAlarms(List<Date> alarms) {
        this.alarms = alarms;
    }

    /**
     * Human Readable version of the Class object.
     * @return Human readable string of the Class object.
     */
    @Override
    public String toString() {
        return "Class{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", address='" + address + '\'' +
                ", instructors=" + instructors +
                ", contact='" + contact + '\'' +
                ", days=" + days +
                ", times=" + times +
                ", url='" + url + '\'' +
                ", enabled=" + enabled +
                ", alarmEnabled=" + alarmsEnabled +
                ", alarms=" + alarms +
                '}';
    }

    /**
     * Convert the Class object to JSON string
     * @return The JSON string of the Class object.
     */
    public String toJSON(){
        Gson gson = Utils.getGSONBuilder();
        return gson.toJson(this, Class.class);
    }
}
