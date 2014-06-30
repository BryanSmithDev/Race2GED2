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
 * Wrapper class for JSON Data. Represents all of the Class Data for all Regions in the state.
 *
 * @author Bryan Smith
 */
public class State implements Serializable {

    private List<Region> region;

    /**
     * Gets the list of regions.
     * @return List of regions.
     */
    public List<Region> getRegion() {
        return region;
    }

    /**
     * Sets the list of Regions.
     * @param regions The list of regions to set to.
     */
    public void setRegion(List regions) {
        this.region = regions;
    }

    /**
     * Human Readable version of the state object.
     * @return Human readable string of the State object.
     */
    @Override
    public String toString() {
        return "State{" +
                "region=" + region +
                '}';
    }
}
