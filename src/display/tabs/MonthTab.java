package display.tabs;

import java.awt.Button;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import data.Member;
import data.Month;
import display.ApplicationWindow;
import display.tabs.*;
import main.Request;

public class MonthTab extends JPanel{
    private static final long serialVersionUID = 6976710718414322188L;
    
    // The Application Window
    ApplicationWindow window;
    
    // The month this tab is about
    String month;
    
    // The name of the member
    String first;
    String last;
    
    // The list of events this member participated in for this month
    ArrayList<String[]> events;

    public MonthTab(String month, String first, String last, ApplicationWindow window){
        this.window = window;
        
        this.month = month;
        
        this.first = first;
        this.last = last;
        
        events = Request.events(month, first, last);
        
        this.setBackground(new Color(150, 150, 150));
        
        setUp();
    }

    private void setUp(){
    	for(String[] event: events) {
    		this.add(new Label(event[0] + ": " + event[1]));
    	}
    }
}