package display.tabs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;

import data.Member;
import display.ApplicationWindow;
import main.Request;
import settings.Information;
import settings.Settings;

public class LoginTab extends JPanel{
    private static final long serialVersionUID = -2292948729682249378L;

    // The Application Window this tab is in
    ApplicationWindow window;
    
    // The login password
    String loginID;
    
    // The text input for the tab
    JTextField input;

    public LoginTab(ApplicationWindow window){
    	this.window = window;
        
    	// Sets the background color
        this.setBackground(new Color(150, 150, 150));
        
        // Retrieves the password
        this.loginID = Settings.getLogin();
        
        // Sets the value of the JTextField
    	input = new JTextField("Enter The Login ID Here");
        
    	// Sets up the login process
        input.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	// Event: When the password is correct
                if(input.getText().equals(loginID)){
                    MemberListTab m = new MemberListTab(window);
                    
                    window.removeTab(0);
                    window.addTab("Members", m, 0);
                }
            }
        });

        this.add(input);
    }
}