package display.tabs;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

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

    public static void main(String[] args) {
    	Settings.retrieveSettings();
    	
    	ApplicationWindow window = new ApplicationWindow(750, 750);
    	
    	window.addTab("Test", new MemberTab(window, "Julie", "Lin"), 1);
    	window.setVisible(true);
    }
    
    public MemberTab(ApplicationWindow window, String first, String last){
    	this.member = Request.member(first, last);
        this.window = window;

        this.setBackground(new Color(150, 150, 150));
        this.setLayout(new GridBagLayout());
        
        setUp();
    }

    private void setUp(){
    	/*
    	 * Name
    	 */
    	GridBagConstraints nameConstraints = new GridBagConstraints();
    	
    	nameConstraints.gridx = 0;
    	nameConstraints.gridy = 0;
    	nameConstraints.insets = new Insets(10, 10, 10, 10);
    	nameConstraints.fill = GridBagConstraints.BOTH;
    	
    	JPanel namePanel = new JPanel();
    	namePanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black), new EmptyBorder(10, 10, 10, 10)));
    	
    	namePanel.add(new JLabel(member.getFirst() + " " + member.getLast()) {
			private static final long serialVersionUID = 1L;
		{
    		setFont(new Font("Arial", Font.BOLD, 45));
    	}});
    	this.add(namePanel, nameConstraints);
    	
    	/*
    	 * Grade, Dues, Total Points
    	 */
    	GridBagConstraints infoConstraints = new GridBagConstraints();
    	
    	infoConstraints.gridx = 0;
    	infoConstraints.gridy = 1;
    	infoConstraints.insets = new Insets(10, 10, 10, 10);
    	infoConstraints.fill = GridBagConstraints.BOTH;
    	
    	JPanel infoPanel = new JPanel();

    	infoPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black), new EmptyBorder(10, 10, 10, 10)));
    	infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
    	
    	infoPanel.add(new Label("Grade: " + member.getGrade()) {
    		private static final long serialVersionUID = 1L;
    		{
    		setFont(new Font("Arial", Font.BOLD, 20));
    	}});
    	infoPanel.add(new Label("Dues: " + member.getDues()) {
    		private static final long serialVersionUID = 1L;
    		{
    		setFont(new Font("Arial", Font.BOLD, 20));
    	}});
    	infoPanel.add(new Label("Total Points: " + member.getTotalPoints()) {
    		private static final long serialVersionUID = 1L;
    		{
    			setFont(new Font("Arial", Font.BOLD, 20));
    	}});
    	
    	this.add(infoPanel, infoConstraints);
    	
    	/*
    	 * Monthly Points
    	 */
    	GridBagConstraints monthConstraints = new GridBagConstraints();
    	
    	monthConstraints.gridx = 1;
    	monthConstraints.gridy = 0;
    	monthConstraints.gridheight = 3;
    	monthConstraints.insets = new Insets(10, 10, 10, 10);
    	monthConstraints.fill = GridBagConstraints.BOTH;
    	
    	JPanel monthPanel = new JPanel();
    	
    	monthPanel.setLayout(new BoxLayout(monthPanel, BoxLayout.Y_AXIS));
    	monthPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black), new EmptyBorder(10, 10, 10, 10)));
    	
     	// Monthly Points
    	for(String[] month: member.getMonthlyPoints()) {
    		monthPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    		monthPanel.add(new JLabel(month[0] + ": " + month[1]) {
				private static final long serialVersionUID = 1L;
				{
					setFont(new Font("Arial", Font.BOLD, 20));
    		}});
    		monthPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    	}
    	
    	this.add(monthPanel, monthConstraints);
    	
    	
    	/*
    	 * Additional Month Information
    	 */
    	GridBagConstraints addConstraints = new GridBagConstraints();
    	
    	addConstraints.gridx = 0;
    	addConstraints.gridy = 2;
    	addConstraints.insets = new Insets(10, 10, 10, 10);
    	addConstraints.fill = GridBagConstraints.BOTH;
    	
    	JPanel containerPanel = new JPanel();
    	
    	containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
    	containerPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black), new EmptyBorder(10, 10, 10, 10)));
    	
    	containerPanel.add(new JLabel("Additional Information (Click!)") {
			private static final long serialVersionUID = 1L;
			{
				setFont(new Font("Arial", Font.BOLD, 15));
    	}});
    	
     	String[] availableMonths = Settings.getMonthSheetList();
     	
    	JPanel addPanel = new JPanel();
    	addPanel.setLayout(new GridLayout((int) Math.ceil((double) availableMonths.length / 3) , 3, 5, 5));
    	addPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black), new EmptyBorder(3, 3, 3, 3)));
 
    	// Additional Information (MonthTab)
    	for(String month: availableMonths) {
    		Button monthButton = new Button(month);
    		
    		monthButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				MonthTab monthtab = new MonthTab(monthButton.getLabel(), member.getFirst(), member.getLast(), window);
                    
    				window.removeTab(2);
                    window.addTab(monthButton.getLabel(), monthtab, 2);
    			}
    		});
    		
    		addPanel.add(monthButton);
    	}
    	
    	containerPanel.add(addPanel);
    	this.add(containerPanel, addConstraints);
    }
}