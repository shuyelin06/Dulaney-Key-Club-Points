package settings;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import main.Request;

public class Settings {
	private static String path = "./src/settings/";
	private static String fileName = "settings.json";
	
	public static JSONObject settings = new JSONObject();
	
	// Method for testing the Settings class
	public static void main(String[] args) {
		instantiateSettings();
		
		retrieveSettings();
	}
	
	
	// Rebuild the settings.json file (and variable) with default parameters
	public static void instantiateSettings() {
		// Clear the settings JSON object
		settings.clear();
		
		// --- Add settings to the JSON object ---
		
		/*
		 * Application Settings: Contains settings pertaining to the application itself
		 * Currently Has: Resolution, Login Password
		 */
		settings.append("Application Settings", new JSONObject() {{
			put("Resolution", new JSONObject() {{
				put("Width", 750);
				put("Height", 750);
			}});
			
			put("Login", "DHSKeyClub");
		}});
		
		/*
		 * HTTP Request Settings: Contains settings pertaining to making HTTP requests to the Google Sheets API service
		 * Currently Has: API Key, Point Spreadsheet ID 
		 */
		settings.append("HTTP Request Settings", new JSONObject() {{
			put("API Key", "AIzaSyC3AAzbHKTV3G1Brywmak8uhbhYLaFN9AI");
			
			put("Spreadsheet ID", "1L88aiK9tt3bM2OZzvztkVLktJeiBv1XYzeIjrTg6vPE");
		}});
		
		/*
		 * Point Spreadsheet Settings: Contains the Format of the Point Spreadsheet
		 * Currently Has: Columns of the main/month sheets, and their names
		 */
		// Create a JSONArray of all of the month sheets (to add to the settings)
		JSONArray monthSheetList = new JSONArray();
		
		settings.append("Point Spreadsheet", new JSONObject() {{
			put("Main Sheet", new JSONObject() {{
				put("Name", "General");
				
				put("Format", new JSONObject() {{
					put("Last Name Column", "A");
					put("First Name Column", "B");
					
					put("Grade Column", "C");
					put("Dues Paid Column", "D");
					
					put("Total Points Column", "E");
				}});
			}});
			
			put("Month Sheets", new JSONObject() {{
				put("List", monthSheetList);
				
				put("Format", new JSONObject() {{
					put("Last Name Column", "B");
					put("First Name Column", "C");
					
					put("Total Points Column", "E");
				}});
			}});
			
		}});
		
		// Fill the JSON Array with all of the names of the month sheets
		for(String s: Request.sheets()) {
			monthSheetList.put(s);
		}
		

		// Save the settings to the settings.json file
		saveSettings();
	}
	
	// Return the resolution setting (given a width/height specification)
	public static int getResolution(String s) {	
		return settings.getJSONArray("Application Settings").getJSONObject(0).getJSONObject("Resolution").getInt(s);
	}
	
	// Returns the login password
	public static String getLogin() {
		return settings.getJSONArray("Application Settings").getJSONObject(0).getString("Login");
	}
	
	// Returns a given HTTP Request Setting
	public static String getHttpSetting(String s) {
		return settings.getJSONArray("HTTP Request Settings").getJSONObject(0).getString(s);
	}
	
	// Returns the name of the main sheet
	public static String getMainSheetName() {
		return settings.getJSONArray("Point Spreadsheet").getJSONObject(0).getJSONObject("Main Sheet").getString("Name");
	}
	
	// Returns a format of the main sheet (given a column specification)
	public static String getMainSheetFormat(String s) {
		return settings.getJSONArray("Point Spreadsheet").getJSONObject(0).getJSONObject("Main Sheet").getJSONObject("Format").getString(s);
	}
	
	// Returns the list of names of month sheets
	public static String[] getMonthSheetList() {
		JSONArray monthList = settings.getJSONArray("Point Spreadsheet").getJSONObject(0).getJSONObject("Month Sheets").getJSONArray("List");
		
		String[] output = new String[monthList.length()];
		for(int i=0; i<monthList.length(); i++) {
			output[i] = monthList.getString(i);
		}
		return output;
	}
	
	// Returns the format of the month sheets (given a column specification)
	public static String getMonthSheetFormat(String s) {
		return settings.getJSONArray("Point Spreadsheet").getJSONObject(0).getJSONObject("Month Sheets").getJSONObject("Format").getString(s);
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
			
			reader.close();
			
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
		} 
		catch(IOException error) {
			System.out.println("An error occured when saving settings");
			System.out.println(error);
		}

	}
}