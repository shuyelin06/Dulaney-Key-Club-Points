package display.tabs;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import data.Member;
import data.Month;
import display.ApplicationWindow;
import display.tabs.*;

public class MonthTab extends JPanel{
    private static final long serialVersionUID = 6976710718414322188L;
    
    ApplicationWindow window;
    ArrayList<Month> months;
    JComboBox<String> dropdown;
    Button submit = new Button("Submit");

    public MonthTab(Member m, ApplicationWindow window){
        this.months = m.getMonths();
        this.window = window;

        this.setBackground(new Color(150, 150, 150));
        
        setUp();
    }

    private void setUp(){
        // Setting up dropdown
        String[] array = new String[months.size()];

        ArrayList<String> convert = new ArrayList<String>();
        for(Month m:months){
            convert.add(m.getName());
        }
        array = convert.toArray(array);
        dropdown = new JComboBox<String>(array);
        this.add(dropdown);

        // Setting up button
        submit.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    int index = (int) dropdown.getSelectedIndex();
                    
                    Month selectedMonth = months.get(index);

                    EventTab eventTab = new EventTab(selectedMonth);
                    window.addTab("Events", eventTab, 2);
                }
            });
        this.add(submit);
    }
}