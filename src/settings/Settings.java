package settings;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONStringer;

public class Settings {
	private static String path = "./src/settings/";
	private static String fileName = "settings.json";
	
	public static JSONObject settings = new JSONObject();
	
	// Method for testing the Settings class
	public static void main(String[] args) {
		instantiateSettings();
	}
	
	
	// Rebuild the settings.json file (and variable) with default parameters
	public static void instantiateSettings() {
		// Clear the settings JSON object
		settings.clear();
		
		// --- Add settings to the JSON object ---
		
		// Adding resolution 
		settings.append("Resolution", new JSONObject(){{
			put("Width", 750);
			put("Height", 500);
		}});
		
		// Adding the login password
		settings.append("Login", new JSONObject(){{
			put("Password", "ubfkr");
		}});
		
		// Adding request options
		settings.append("Requests", new JSONObject(){{
			put("APIKey", "AIzaSyC3AAzbHKTV3G1Brywmak8uhbhYLaFN9AI");
			put("SpreadsheetID", "1L88aiK9tt3bM2OZzvztkVLktJeiBv1XYzeIjrTg6vPE");
		}});

		// Save the settings to the settings.json file
		saveSettings();
	}
	
	// Given a String path, return the setting associated with it
	public static Object getSetting(String[] path) {
		JSONObject retrieval = settings;
		
		for(int i=0; i<path.length; i++) {
			System.out.println(retrieval);
			if(i == path.length - 1) {
				System.out.println(path[i]);
				return retrieval.get(path[i]);
			} else {
				System.out.println(path[i]);
				retrieval = retrieval
						.getJSONArray(path[i])
						.getJSONObject(0);
			}
		}
		
		// If no setting is found, return null
		return null;
	}
	
	// Retrieve all settings from the settings.json file
	public static void retrieveSettings() {
		// Clear the settings JSON object so it can save data
		settings.clear();
		
		// Read the settings.json file and parse it into a JSONObject
		try {
			FileReader reader = new FileReader(path + fileName);
			
			// Read the file, and append each character to a string
			String s = new String("");
			
			boolean nextChar = true;
			while(nextChar) {
				int encoding = reader.read();
				
				if(encoding == -1) {
					nextChar = false;
				} else {
					s += Character.toString(encoding);
				}
				
			}
			
			// Parse the string into a JSONObject
			settings = new JSONObject(s);
		} 
		catch(IOException error) {
			System.out.println("An error occured when retrieving settings");
			System.out.println(error);
		}

	}
	
	// Save the JSON object and its values into the settings.json file
	public static void saveSettings() {
		try {			
			FileWriter file = new FileWriter(path + fileName);
			
			file.write(settings.toString());
			file.close();
			
			System.out.println("Settings successfully saved");
		} 
		catch(IOException error) {
			System.out.println("An error occured when saving settings");
			System.out.println(error);
		}

	}
}