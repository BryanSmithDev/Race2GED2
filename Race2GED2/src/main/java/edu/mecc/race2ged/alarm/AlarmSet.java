package edu.mecc.race2ged.alarm;

import java.io.Serializable;
import java.util.List;

/**
 * @author Bryan Smith
 * @date 8/13/2014.
 */
public class AlarmSet implements Serializable {
    private List<Alarm> alarms;

    public List<Alarm> getAlarms() {
        return alarms;
    }

    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
    }

    public void addAlarm(Alarm alarm){
        alarms.add(alarm);
    }

    @Override
    public String toString() {
        return "AlarmSet{" +
                "alarms=" + alarms +
                '}';
    }
}
