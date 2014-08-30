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

import edu.mecc.race2ged.helpers.Utils;

/**
 * Wrapper class for JSON Data. Represents an Instructor or Teacher. Used in the Class class.
 *
 * @author Bryan Smith
 */
public class Instructor implements Serializable {

    private String name;
    private String email;

    /**
     * Get the name of the Instructor.
     * @return Name of the Instructor.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name for the instructor.
     * @param name Instructor's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the instructors email.
     * @return The email of the instructor.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email for the instructor.
     * @param email The email to set for the instructor.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Human Readable version of the Instructor object.
     * @return Human readable string of the Instructor object.
     */
    @Override
    public String toString() {
        return "Instructor{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * Convert the Instructor object to JSON string
     * @return The JSON string of the Instructor object.
     */
    public String toJSON(){
        Gson gson = Utils.getGSONBuilder();
        return gson.toJson(this, Instructor.class);
    }
}
