package main;

import settings.Settings;
import display.ApplicationWindow;

public class StartUp {
	// Begins the application
    public static void main(String args[]){
    	// Instantiate the default settings (for now)
    	Settings.instantiateSettings();
    	
    	// Retrieve / instantiate settings
    	Settings.retrieveSettings();
    	
    	// Setting the bounds of the application frame
        ApplicationWindow frame = new ApplicationWindow(
        		Settings.getResolution("Width"), 
        		Settings.getResolution("Height")
        		);
        
        
        // Sets up the application frame
        frame.setUp();  
    }


}
