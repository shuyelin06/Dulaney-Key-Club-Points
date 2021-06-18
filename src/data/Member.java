package data;

import java.util.ArrayList;

public class Member{
    private String firstName;
    private String lastName;
    private ArrayList<Month> months = new ArrayList<Month>();

    // Accessor Methods
    public String name(){
        return lastName + ", " + firstName;
    }
    public ArrayList<Month> getMonths(){
        return months;
    }

    // Mutator Methods
    public void setFirst(String first){
        this.firstName = first;
    }
    public void setLast(String last){
        this.lastName = last;
    }
    public void addMonth(Month m){
        this.months.add(0, m);
    }
}