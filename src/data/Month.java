package data;

import java.util.ArrayList;

public class Month{
    private String monthName;
    private ArrayList<ClubEvent> events = new ArrayList<ClubEvent>();

    public Month(String name){
        this.monthName = name;
    }

    public void addEvent(ClubEvent e){
        events.add(0, e);
    }

    public String getName(){
        return monthName;
    }

    public ArrayList<ClubEvent> getEvents(){
        return events;
    }
}