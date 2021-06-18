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
    
    private JTabbedPane tabbedpane = new JTabbedPane();

    public ApplicationWindow(int x, int y){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Dulaney Key Club Points");
        this.setSize(new Dimension(x,y));
        this.setResizable(false);

        this.getContentPane().setBackground(new Color(211, 211, 211));

        tabbedpane.setTabPlacement(JTabbedPane.TOP);
    }

    public void setUp(ArrayList<Member> list){
        LoginTab lTab = new LoginTab(list, this);
        addTab("Login", lTab, 0);
        
        this.add(tabbedpane);
    }
    public void removeTab(int index){
        try{
            tabbedpane.remove(index);
        } catch(Exception err){

        } finally{
            update();
        }
    }
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
    private void update(){
        this.revalidate();
    }

}