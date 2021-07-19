package display.tabs;

import java.awt.Button;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import display.ApplicationWindow;
import main.Request;
import settings.Settings;
import data.Member;

public class MemberTab extends JPanel{
    private static final long serialVersionUID = -4249464756785891838L;
    
    // The Application Window
    ApplicationWindow window;
    
    // The member that the tab was constructed around.
    Member member;
    
    // The dropdown box (to select members)
    JComboBox<String> dropdown;
    
    String first;
    String last;

    public MemberTab(ApplicationWindow window, String first, String last){
    	this.first = first;
    	this.last = last;
    	
    	this.member = Request.member(first, last);
    	
        this.window = window;

        this.setBackground(new Color(150, 150, 150));
        
        setUp();
    }

    private void setUp(){
    	// Name
    	this.add(new Label(member.getFirst() + " " + member.getLast()));
    	
    	// Grade, Dues, Total Points
    	this.add(new Label("Grade: " + member.getGrade()));
    	this.add(new Label("Dues: " + member.getDues()));
    	
    	this.add(new Label("Total Points: " + member.getTotalPoints()));
    	
    	// Monthly Points
    	for(String[] month: member.getMonthlyPoints()) {
    		this.add(new Label(month[0] + ": " + month[1]));
    	}
    	
    	// Additional Information (MonthTab)
    	String[] availableMonths = Settings.getMonthSheetList();
    	for(String month: availableMonths) {
    		Button monthButton = new Button(month);
    		
    		monthButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				MonthTab monthtab = new MonthTab(monthButton.getLabel(), first, last, window);
                    
    				window.removeTab(2);
                    window.addTab(monthButton.getLabel(), monthtab, 2);
    			}
    		});
    		
    		this.add(monthButton);
    	}
    }
}