package display.tabs;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import display.ApplicationWindow;
import main.Request;

public class MonthTab extends JPanel{
    private static final long serialVersionUID = 6976710718414322188L;
    
    // The Application Window
    ApplicationWindow window;
    
    // The month this tab is about
    String month;
    
    // The name of the member
    String first;
    String last;
    
    // The list of events this member participated in for this month
    ArrayList<String[]> events;

    public MonthTab(String month, String first, String last, ApplicationWindow window){
        this.window = window;
        
        this.month = month;
        
        this.first = first;
        this.last = last;
        
        events = Request.events(month, first, last);
        
        this.setBackground(new Color(150, 150, 150));
        this.setLayout(new GridBagLayout());
        
        setUp();
    }

    private void setUp(){
    	/*
    	 * Header Panel
    	 */
    	JPanel headerPanel = new JPanel();
    	
    	GridBagConstraints headerConstraints = new GridBagConstraints();
    	
    	headerConstraints.gridx = 0;
    	headerConstraints.gridy = 0;
    	headerConstraints.insets = new Insets(10, 10, 10, 10);
    	headerConstraints.fill = GridBagConstraints.BOTH;
    	
    	headerPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black), new EmptyBorder(10, 10, 10, 10)));
    	
    	headerPanel.add(new JLabel(first + " " + last + "'s " + month + " Points") {
			private static final long serialVersionUID = 1L;
			{
				setFont(new Font("Arial", Font.BOLD, 40));
    	}});
    	
    	this.add(headerPanel, headerConstraints);
    	
    	/*
    	 * Information Panel (Contains Event Name and Points)
    	 */
    	JPanel informationPanel = new JPanel();
    	
    	GridBagConstraints infoConstraints = new GridBagConstraints();
    	
    	infoConstraints.gridx = 0;
    	infoConstraints.gridy = 1;
    	infoConstraints.insets = new Insets(10, 10, 10, 10);
    	infoConstraints.fill = GridBagConstraints.BOTH;
    	
    	informationPanel.setLayout(new GridLayout(events.size(), 2, 5, 10));
    	informationPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black), new EmptyBorder(10, 10, 10, 10)));
    	
    	for(String[] event: events) {
    		informationPanel.add(new JLabel(event[0]) {
				private static final long serialVersionUID = 1L;
			{
				setFont(new Font("Arial", Font.BOLD, 25));
    		}});
    		
    		informationPanel.add(new JLabel(event[1]) {
				private static final long serialVersionUID = 1L;
			{
				setFont(new Font("Arial", Font.BOLD, 25));
    		}});
    	}
    	
    	this.add(informationPanel, infoConstraints);
    }
}