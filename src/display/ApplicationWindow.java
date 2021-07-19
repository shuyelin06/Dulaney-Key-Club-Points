package display;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import data.Member;
import display.tabs.LoginTab;
import settings.Information;

import java.awt.event.*;

public class ApplicationWindow extends JFrame{
    private static final long serialVersionUID = 6856031828783739192L;
    
    // Where all of the tabs will be located
    private JTabbedPane tabbedpane = new JTabbedPane();

    // Constructor which takes in a width and a height
    public ApplicationWindow(int width, int height){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Dulaney Key Club Points");
        this.setSize(new Dimension(width, height));
        this.setResizable(false);

        this.getContentPane().setBackground(new Color(211, 211, 211));

        tabbedpane.setTabPlacement(JTabbedPane.TOP);
    }

    // Sets up the Application Window
    public void setUp(){
        LoginTab loginTab = new LoginTab(this);
        addTab("Login", loginTab, 0);
        
        this.add(tabbedpane);
        
        this.setVisible(true);
    }
    
    // Adds a tab to the tabbed pane
    public void addTab(String title, JPanel p, int index){
        try{
            tabbedpane.setComponentAt(index, p);
        } catch (Exception err){
            try{
                tabbedpane.addTab(title, p);
            } catch (Exception er){}
        }
        finally{
            update();
        }
        
    }
    
    // Removes a tab from the tabbed pane
    public void removeTab(int index){
        try{
            tabbedpane.remove(index);
        } catch(Exception err){

        } finally{
            update();
        }
    }

    private void update(){
        this.revalidate();
    }

}