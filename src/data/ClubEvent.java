package data;

public class ClubEvent{
    private String name;
    private String points;

    public ClubEvent(String n, String p){
        this.name = n;
        this.points = p;
    }

    // Accessor methods
    public String getEvent(){
        return getName() + " -- " + getPoints() + " points";
    }
    private String getName(){
        return name;
    }
    private String getPoints(){
        return points;
    }
}