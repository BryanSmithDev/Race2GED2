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

import java.io.Serializable;
import java.util.List;

/**
 * Wrapper class for JSON Data. Represents a County in a state. Used by Region class.
 *
 * @author Bryan Smith
 */
public class County implements Serializable {

    private String name;
    private List<Class> classes;

    /**
     * Gets the name of the county.
     * @return Name of the county.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the county.
     * @param name Value to set the name to.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets a list of the classes in the county.
     * @return List of classes in the county.
     */
    public List<Class> getClasses() {
        return classes;
    }

    /**
     * Sets the list of classes in the county.
     * @param classes The list of classes to set for the county.
     */
    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    /**
     * Human Readable version of the County object.
     * @return Human readable string of the County object.
     */
    @Override
    public String toString() {
        return "County{" +
                "name='" + name + '\'' +
                ", classes=" + classes +
                '}';
    }
}
