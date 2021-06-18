package display.tabs;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import display.ApplicationWindow;
import data.Member;

public class MemberTab extends JPanel{
    private static final long serialVersionUID = -4249464756785891838L;
    
    ApplicationWindow window;
    ArrayList<Member> list;
    JComboBox<String> dropdown;
    Button submit = new Button("Submit");

    public MemberTab(ArrayList<Member> members, ApplicationWindow window){
        this.list = members;
        this.window = window;

        this.setBackground(new Color(150, 150, 150));
        
        setUp();
    }

    private void setUp(){
        // Setting up dropdown values
        String[] members = new String[list.size()];

        ArrayList<String> memberNames = new ArrayList<String>();
        for(Member m: list){
            memberNames.add(m.name());
        }
        
        members = memberNames.toArray(members);
        dropdown = new JComboBox<String>(members);
        
        this.add(dropdown);

        // Setting up submit button
        submit.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    int index = (int) dropdown.getSelectedIndex();
                    
                    Member selectedMember = list.get(index);

                    MonthTab monthtab = new MonthTab(selectedMember, window);
                    window.addTab("Months", monthtab, 1);
                }
            });
        this.add(submit);
    }
}