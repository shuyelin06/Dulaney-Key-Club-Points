package display.tabs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;

import data.Member;
import display.ApplicationWindow;
import settings.Information;

public class LoginTab extends JPanel{
    private static final long serialVersionUID = -2292948729682249378L;

    ApplicationWindow window;
    String loginID = Information.login;
    ArrayList<Member> list;
    JTextField input = new JTextField("Enter The Login ID Here");

    public LoginTab(ArrayList<Member> members, ApplicationWindow window){
        list = members;
        this.window = window;

        this.setBackground(new Color(150, 150, 150));
        
        setUp();
    }

    private void setUp(){
        input.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    if(input.getText().equals(loginID)){
                        MemberTab m = new MemberTab(list, window);
                        
                        window.removeTab(0);
                        window.addTab("Members", m, 0);
                    }
                }
            });
        this.add(input);
    }
}