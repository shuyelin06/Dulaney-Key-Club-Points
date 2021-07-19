package main;

import settings.Settings;
import display.ApplicationWindow;

public class StartUp {
	// Begins the application
    public static void main(String args[]){
    	// Overwriting the settings JSON file with the default settings
    	Time.startTimer("Instantiating Settings");
    	Settings.instantiateSettings();
    	Time.endTimer();
    	
    	// Loading the settings into a JSON Object
    	Time.startTimer("Retrieving/Loading Settings");
    	Settings.retrieveSettings();
    	Time.endTimer();
    	
    	// Setting the bounds of the application frame
        Time.startTimer("Initializing Application Frame");
    	ApplicationWindow frame = new ApplicationWindow(
        		Settings.getResolution("Width"), 
        		Settings.getResolution("Height")
        		);
        Time.endTimer();
        
        // Sets up the application frame
        Time.startTimer("Setting Up Application Frame");
        frame.setUp();  
        Time.endTimer();
    }


}
