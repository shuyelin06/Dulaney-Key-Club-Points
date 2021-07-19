package main;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import data.*;
import settings.Information;
import settings.Settings;

import org.json.JSONArray;

public class Request {
	/*
	 * ---- Request the list of all members from the general sheet of the point spreadsheet ----
	 */
	private static ArrayList<String> memberList = new ArrayList<String>();
	
    // Request a list of all the members in the Point Spreadsheet (General Sheet, First Two Columns)
    public static ArrayList<String> memberList(){
    	// Pulling the Spreadsheet ID and API Key from Settings
    	final String Main_Sheet = Settings.getMainSheetName();
        
    	final String FirstCol = Settings.getMainSheetFormat("First Name Column");
    	final String LastCol = Settings.getMainSheetFormat("Last Name Column");
    	
    	final String Sheet_ID = Settings.getHttpSetting("Spreadsheet ID");
        final String API_Key = Settings.getHttpSetting("API Key");

        // Create an HTTP Client
        var client = HttpClient.newHttpClient();
        
        // Create the HTTP Request
        var httprequest = HttpRequest.newBuilder(
            URI.create("https://sheets.googleapis.com/v4/spreadsheets/" + Sheet_ID + "/values/" + Main_Sheet + "!" + FirstCol + ":" + LastCol + "/?key=" + API_Key))
            .build();

        // Use the client to send the HTTP Request
        client
            .sendAsync(httprequest, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(Request::instantiateMemberList)
            .join();
        
        return memberList;
    }
    // Variable for where 
    private static void instantiateMemberList(String s){
        JSONArray list = new JSONObject(s).getJSONArray("values");
        
        // Clearing the memberList arraylist
        memberList.clear();
        
        // Iterate through the JSONArray and add first/last name pairs
        for(int i=0; i<list.length(); i++) {
        	JSONArray pair = list.getJSONArray(i);
        	
        	memberList.add((String) pair.get(0) + ", " + (String) pair.get(1));
        }
    }
    
    /*
     * ---- Request a single member and their main information from the point spreadsheet ----
     */
    
    private static Member member = null;
    private static String[] headers;
    
    public static Member member(String firstName, String lastName) {    	
    	// Finding the row number of the member in the general sheet
    	int rowNumber = Request.memberList().indexOf(lastName + ", " + firstName) + 1;
    	
    	if(rowNumber == 0) return null;
    	
    	// Pull the header and the member row
    	final String Main_Sheet = Settings.getMainSheetName();
    	final int headerRow = 1;
    	
    	final String Sheet_ID = Settings.getHttpSetting("Spreadsheet ID");
        final String API_Key = Settings.getHttpSetting("API Key");

        // Create an HTTP Client
        var client = HttpClient.newHttpClient();
        
        // HTTP Request for headers
        var requestHeaders = HttpRequest.newBuilder(
                URI.create("https://sheets.googleapis.com/v4/spreadsheets/" + Sheet_ID + "/values/" + Main_Sheet + "!" + headerRow + ":" + headerRow + "/?key=" + API_Key))
                .build();
        // HTTP Request for member information
        var requestInformation = HttpRequest.newBuilder(
            URI.create("https://sheets.googleapis.com/v4/spreadsheets/" + Sheet_ID + "/values/" + Main_Sheet + "!" + rowNumber + ":" + rowNumber + "/?key=" + API_Key))
            .build();

        // Use the client to send the HTTP Requests
        client
            .sendAsync(requestHeaders, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(Request::createHeaders)
            .join();
        
        client
        	.sendAsync(requestInformation , BodyHandlers.ofString())
        	.thenApply(HttpResponse::body)
        	.thenAccept(Request::createMember)
        	.join();
        
    	return member;
    }
    private static void createHeaders(String s) {
    	JSONArray array = new JSONObject(s).getJSONArray("values").getJSONArray(0);
    	
    	headers = new String[array.length()];
    	
    	for(int i=0; i<array.length(); i++) {
    		headers[i] = array.getString(i);
    	}
    }
    private static void createMember(String s) {
    	// Extracting the member data and putting it into an array
    	JSONArray array = new JSONObject(s).getJSONArray("values").getJSONArray(0);
    	
    	String[] memberData = new String[array.length()];
    	
    	for(int i=0; i<array.length(); i++) {
    		memberData[i] = array.getString(i);
    	}
    	
    	/*
    	 * Creating the Member Object
    	 */
    	// Creating the Member Object (with First, Last, and Total Points)
    	String first = memberData[Character.getNumericValue(Settings.getMainSheetFormat("First Name Column").charAt(0)) - 10];
    	String last = memberData[Character.getNumericValue(Settings.getMainSheetFormat("Last Name Column").charAt(0)) - 10];
    	
    	String total = memberData[Character.getNumericValue(Settings.getMainSheetFormat("Total Points Column").charAt(0)) - 10];
    	
    	member = new Member(first, last, total);
    	
    	// Adding grade and dues (if available)
    	String grade = memberData[Character.getNumericValue(Settings.getMainSheetFormat("Grade Column").charAt(0)) - 10];
    	String dues = memberData[Character.getNumericValue(Settings.getMainSheetFormat("Dues Paid Column").charAt(0)) - 10];
    	
    	if(!grade.equals("")) {
    		member.grade(grade);
    	}
    	if(dues.toLowerCase().equals("x")) {
    		member.duesPaid("Paid");
    	}
    	
    	// Adding monthly points
    	int totalPointsIndex = Character.getNumericValue(Settings.getMainSheetFormat("Total Points Column").charAt(0)) - 10;
    	
    	for(int i = totalPointsIndex + 1; i < memberData.length; i++) {
    		member.addMonth(new String[] {headers[i], memberData[i]});
    	}
    }
    
    /*
     * ---- Request the Points for a Member Given a Month ----
     */
    private static ArrayList<String[]> events = new ArrayList<String[]>();
    
    private static String[] name = new String[2];
    private static int rowNumber = -1;
    
    public static void main(String[] args) {
    	Settings.retrieveSettings();
    	
    	Request.events("April", "Julie", "Lin");
    }
    
    public static ArrayList<String[]> events(String month, String first, String last){
    	final String Sheet_ID = Settings.getHttpSetting("Spreadsheet ID");
        final String API_Key = Settings.getHttpSetting("API Key");
        
        // Create an HTTP Client
        var client = HttpClient.newHttpClient();
        
    	// Find the row number of the member in this month sheet
        String firstCol = Settings.getMonthSheetFormat("First Name Column");
        String lastCol = Settings.getMonthSheetFormat("Last Name Column");
        
        rowNumber = -1;
        name[0] = first;
        name[1] = last;
        
        var list = HttpRequest.newBuilder(
                URI.create("https://sheets.googleapis.com/v4/spreadsheets/" + Sheet_ID + "/values/" + month + "!" + lastCol + ":" + firstCol + "/?key=" + API_Key))
                .build();
        
        // Use the client to send the HTTP Requests
        client
            .sendAsync(list, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(Request::findMemberRow)
            .join();
        
        if(rowNumber == -1) return null;
        
        
        var requestHeaders = HttpRequest.newBuilder(
                URI.create("https://sheets.googleapis.com/v4/spreadsheets/" + Sheet_ID + "/values/" + month + "!" + 1 + ":" + 1 + "/?key=" + API_Key))
                .build();
        var requestData = HttpRequest.newBuilder(
                URI.create("https://sheets.googleapis.com/v4/spreadsheets/" + Sheet_ID + "/values/" + month + "!" + rowNumber + ":" + rowNumber + "/?key=" + API_Key))
                .build();
        client
    		.sendAsync(requestHeaders, BodyHandlers.ofString())
    		.thenApply(HttpResponse::body)
    		.thenAccept(Request::extractHeaders)
    		.join();
        
        client
        	.sendAsync(requestData, BodyHandlers.ofString())
        	.thenApply(HttpResponse::body)
        	.thenAccept(Request::extractEvents)
        	.join();
        
    	return events;
    }
    private static void findMemberRow(String s) {
    	JSONArray memberList = new JSONObject(s).getJSONArray("values");
    	
    	/*
    	 * -- Searches through the list to find the associated index (to convert into a row)
    	 * Could be faster, but it works.
    	 */
    	for(int i=0; i<memberList.length(); i++) {
    		JSONArray array = memberList.getJSONArray(i);
    		
    		if(array.length() != 2) {
    			continue;
    		} else {
    			String first = array.getString(1);
        		String last = array.getString(0);
    			
        		if(name[0].equals(first) && name[1].equals(last)) {
        			rowNumber = i + 1;
        		}
    		}
    	}
    	
    }
    private static void extractHeaders(String s) {
    	JSONArray array = new JSONObject(s).getJSONArray("values").getJSONArray(0);
    	
    	headers = new String[array.length()];
    	
    	for(int i=0; i<array.length(); i++) {
    		headers[i] = array.getString(i);
    	}
    }
    private static void extractEvents(String s) {
    	events.clear();
    	
    	// Extracting the member data and putting it into an array
    	JSONArray array = new JSONObject(s).getJSONArray("values").getJSONArray(0);
    	
    	String[] memberData = new String[array.length()];
    	
    	for(int i=0; i<array.length(); i++) {
    		memberData[i] = array.getString(i);
    	}
    	
    	// Find the Associated Index of Where the Events Begin
    	int totalPointsIndex = Character.getNumericValue(Settings.getMonthSheetFormat("Total Points Column").charAt(0)) - 10;
    	
    	for(int i = totalPointsIndex + 1; i < memberData.length; i++) {
    		if(headers[i].equals("") || memberData[i].equals("")) continue;
    		
    		events.add(new String[] {headers[i], memberData[i]});
    	}
    }
    
    /*
     * ---- Request the Names of All of the Sheets ----
     * Fairly slow method (compared to the others), which is why it will only be used in initializing / resetting the settings
     */
    
    private static ArrayList<String> sheetList = new ArrayList<String>();
    
    public static ArrayList<String> sheets(){
    	final String Sheet_ID = Settings.getHttpSetting("Spreadsheet ID");
        final String API_Key = Settings.getHttpSetting("API Key");

        // Create an HTTP Client
        var client = HttpClient.newHttpClient();
        
        // Create the HTTP Request
        var httprequest = HttpRequest.newBuilder(
            URI.create("https://sheets.googleapis.com/v4/spreadsheets/" + Sheet_ID + "/?key=" + API_Key))
            .build();

        // Use the client to send the HTTP Request
        client
            .sendAsync(httprequest, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(Request::extractSheets)
            .join();
        
        return sheetList;
    }
    private static void extractSheets(String s) {
    	JSONArray sheets = new JSONObject(s).getJSONArray("sheets");
    	
    	sheetList.clear();
    	
    	for(int i=0; i<sheets.length(); i++) {
    		String name = sheets.getJSONObject(i).getJSONObject("properties").getString("title");
    		if(!name.equals(Settings.getMainSheetName())) {
        		sheetList.add(name);
    		}
    	}
    }

}
