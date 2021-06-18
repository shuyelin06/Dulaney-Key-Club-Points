package settings;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONWriter;

public class Settings {
	private static String path = "./src/settings/";
	private static String fileName = "settings.txt";
	
	
	public enum Setting {
		
	}
	public static void main(String[] args) {
		// retrieveSettings();
		saveSettings();
	}
	
	public static void retrieveSettings() {
		try {
			FileReader reader = new FileReader(path + fileName);
			
			System.out.println(reader.getEncoding());
			
			boolean nextChar = true;
			while(nextChar) {
				int read = reader.read();
				
				if(read == -1) {
					nextChar = false;
				} else {
					System.out.println(Character.toString(read));
				}
				
			}
		} catch(IOException error) {
			System.out.println("There was an error");
		}

	}
	public static void saveSettings() {
		try {			
			FileWriter file = new FileWriter(path + fileName);
			file.write("Tesing testgint 2 241");
			file.flush();
			file.close();
			
			System.out.println("Success!");
		} catch(IOException error) {
			System.out.println("There was an error");
			System.out.println(error);
		}

	}
}