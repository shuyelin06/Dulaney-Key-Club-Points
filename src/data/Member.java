package data;

import java.util.ArrayList;

public class Member{
    private String firstName;
    private String lastName;
    
    private String grade;
    private String dues;
    
    private String totalPoints;
    
    private ArrayList<String[]> monthlyPoints;
    
    public Member(String first, String last, String points) {
    	this.lastName = last;
    	this.firstName = first;
    	
    	this.totalPoints = points;
    	
    	monthlyPoints = new ArrayList<String[]>();
    	
    	grade = "NA";
    	dues = "Not Paid";
    }

    public String getInformation() {
    	String output = 
    			firstName + " " + lastName + "/n" +
    			"Grade: " + grade + "/n" +
    			"Dues: " + dues + "/n" + 
    			"Total Points: " + totalPoints;
    	
    	for(String[] month: monthlyPoints) {
    		output += "/n" + month[0] + ": " + month[1];
    	}
    	
    	return output;
    }
    public void print() {
    	System.out.println(firstName + " " + lastName);
    	
    	System.out.println("Total Points: " + totalPoints);
    	System.out.println("Grade: " + grade);
    	System.out.println("Dues: " + dues);
    	
    	for(String[] month: monthlyPoints) {
    		System.out.println(month[0] + ": " + month[1]);
    	}
    }
    /*
     * --- Accessor Methods ---
     */
    public String getFirst() {
    	return firstName;
    }
    public String getLast() {
    	return lastName;
    }
    public String getGrade() {
    	return grade;
    }
    public String getDues() {
    	return dues;
    }
    
    public String getTotalPoints() {
    	return totalPoints;
    }
    public ArrayList<String[]> getMonthlyPoints(){
    	return monthlyPoints;
    }

    /*
     * --- Mutator Methods ---
     */
    public void duesPaid(String s) {
    	dues = s;
    }
    public void grade(String s) {
    	grade = s;
    }
    public void addMonth(String[] s) {
    	monthlyPoints.add(s);
    }
}