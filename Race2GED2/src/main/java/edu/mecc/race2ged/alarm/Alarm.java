package edu.mecc.race2ged.alarm;

import java.io.Serializable;

import edu.mecc.race2ged.JSON.Class;

/**
 * Class that contains all the data needed for a class alarm.
 * @author Bryan Smith
 * @date 8/13/2014.
 */
public class Alarm implements Serializable {


    private Class alarmsClass;

    public Alarm(Class alarmsClass){
        this.alarmsClass = alarmsClass;
    }

    public Class getAlarmsClass() {
        return alarmsClass;
    }

    public void setAlarmsClass(Class alarmsClass) {
        this.alarmsClass = alarmsClass;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "alarmsClass=" + alarmsClass +
                '}';
    }
}
