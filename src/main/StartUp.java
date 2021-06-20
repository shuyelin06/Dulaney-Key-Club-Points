package main;

import java.util.ArrayList;

import settings.Settings;
import data.Member;
import display.ApplicationWindow;

public class StartUp{
	// Begins the application
    public static void main(String args[]){
    	// Retrieve / instantiate settings
    	Settings.retrieveSettings();
    	
    	// Setting the bounds of the application frame
        ApplicationWindow frame = new ApplicationWindow(
        		(Integer) Settings.getSetting(new String[]{"Resolution", "Width"}), 
        		(Integer) Settings.getSetting(new String[]{"Resolution", "Height"})
        		);
        
//        Request.pull();
//        ArrayList<Member> members = new ArrayList<Member>();
//        
//        /* Processing and creating so many member objects takes time, needless to say there's
//        *  probably a faster way to do this; I'm looking into it.
//        */
//        for(int i=2; i<Request.getMaxRows()-1; i++){
//            members.add(Request.member(i));
//        }
//        
//        frame.setUp(members);
        frame.setVisible(true);

    }

}
