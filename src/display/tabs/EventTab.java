package display.tabs;

import java.awt.Event;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.ArrayList;

import javax.swing.JPanel;

import data.*;

public class EventTab extends JPanel{
    private static final long serialVersionUID = 7185133448178686343L;
    
    ArrayList<ClubEvent> events;

    public EventTab(Month mon){
        this.events = mon.getEvents();
        this.setLayout(new GridLayout(events.size(), 1));

        setUp();
    }

    private void setUp(){
        if(events.size() == 0){
            this.add(new Label("No events found."));
        } else{
            for(ClubEvent e: events){
                String event = e.getEvent();
    
                this.add(new Label(event));
            }
        }
        
    }
}