package display.tabs;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import display.ApplicationWindow;
import main.Request;
import data.Member;

public class MemberListTab extends JPanel{
	// Dunno what thsi does
    private static final long serialVersionUID = -4249464756785891838L;
    
    // The Application Window
    ApplicationWindow window;
    
    // The list of members (with first and last name)
    ArrayList<String> memberList;
    
    // The dropdown box (to select members)
    JComboBox<String> dropdown;
    
    // The submit box (to have the 
    Button submit = new Button("Submit");

    public MemberListTab(ApplicationWindow window){
    	this.memberList = Request.memberList();
    	
        this.window = window;

        this.setBackground(new Color(150, 150, 150));
        
        setUp();
    }

    private void setUp(){
    	// Converting the ArrayList 
    	String[] list = new String[memberList.size()];
    	
    	for(int i=0; i<memberList.size(); i++) {
    		list[i] = memberList.get(i);
    	}

        dropdown = new JComboBox<String>(list);
        
        this.add(dropdown);

        // Setting up submit button
        submit.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                	String[] name = dropdown.getItemAt(dropdown.getSelectedIndex()).split(", ");
                	
                    MemberTab membertab = new MemberTab(window, name[1], name[0]);
                    window.removeTab(1);
                    window.addTab(name[1] + " " + name[0], membertab, 1);
                }
            });
        this.add(submit);
    }
}