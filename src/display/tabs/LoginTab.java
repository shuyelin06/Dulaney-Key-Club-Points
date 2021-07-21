package display.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import display.ApplicationWindow;
import main.Time;
import settings.Settings;

public class LoginTab extends JPanel{
    private static final long serialVersionUID = -2292948729682249378L;

    // The Application Window this tab is in
    ApplicationWindow window;
    
    // The password required to log into the application (case sensitive)
    String loginID;
    
    // The text field where the user will be entering the login password 
    JTextField input;
    
    // Submit button
    JButton submit;

    // https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html 
    
    public LoginTab(ApplicationWindow window){
    	Time.startTimer("Instantiating Login Tab");
    	
    	this.window = window;
        
    	// Sets the background color
        this.setBackground(new Color(150, 150, 150));
        
        // Sets the layout to the vertical box layout (elements are aligned top to bottom)
        this.setLayout(new GridBagLayout());
        
        // Retrieving the login password
        this.loginID = Settings.getLogin();
        
        /*
         * Instantiating Key Club Logo
         */
        JLabel logo = new JLabel(new ImageIcon("./src/resources/KeyClubLogo.png"));
        logo.setPreferredSize(new Dimension(300, 300));
        
        GridBagConstraints logoConstraints = new GridBagConstraints();
        
        logoConstraints.gridx = 1;
        logoConstraints.gridy = 0;
        
        this.add(logo, logoConstraints);
        
        /*
         * Adding Between Components
         */
        GridBagConstraints filler1 = new GridBagConstraints();
        
        filler1.gridx = 1;
        filler1.gridy = 2;
        
        this.add(Box.createRigidArea(new Dimension(175, 15)), filler1);
        
        /*
         *  Instantiating the Text Box
         */
        input = new JTextField("Enter The Login ID Here");
        input.setHorizontalAlignment(JTextField.CENTER);
        input.setPreferredSize(new Dimension(140, 40));
        
        GridBagConstraints textConstraints = new GridBagConstraints();
        
        textConstraints.gridx = 1;
        textConstraints.gridy = 3;
        
        this.add(input, textConstraints);
        
        /*
         * Adding Space Between Components
         */
        GridBagConstraints filler2 = new GridBagConstraints();
        
        filler2.gridx = 1;
        filler2.gridy = 4;
        
        
        this.add(Box.createRigidArea(new Dimension(0, 15)), filler2);
        
        /*
         * Instantiating the submit button
         */
        submit = new JButton("Log In");
        submit.setPreferredSize(new Dimension(75, 40));
        
        submit.addActionListener(new ActionListener()
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
        
        GridBagConstraints submitConstraints = new GridBagConstraints();
        
    	submitConstraints.gridx = 1;
    	submitConstraints.gridy = 5;
    	
    	this.add(submit, submitConstraints);
    	
    	Time.endTimer();
    }
}