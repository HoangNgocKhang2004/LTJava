/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Minh
 */
class JFDashboard extends JFrame
{
    public JFDashboard()
    {
        setTitle("SIeu thi");
        setSize(500,600);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        JPanel pn = new JPanel();
        pn.setBackground(Color.black);
        pn.setSize(300, 600);
        c.add(pn,BorderLayout.WEST);
    }
}
public class Dashboard  {
   public static void main(String[] args) {
        JFDashboard l = new JFDashboard();
        l.setVisible(true);
    }
    
}