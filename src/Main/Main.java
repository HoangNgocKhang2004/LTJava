/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author Minh
 */
public class Main  {
   public static void main(String[] args) {
        JFrameDemo l = new JFrameDemo("hello");
        l.setVisible(true);
    }
    
}
class JFrameDemo extends JFrame
{
    public JFrameDemo(String title)
   {
       Container con = getContentPane();
       con.setLayout(new BorderLayout());
       JPanel p1 = new JPanel();
       p1.setBackground(Color.YELLOW);
       JButton b = new JButton("CLick");
       b.addActionListener(new ActionListener()
       {
           @Override
           public void actionPerformed(ActionEvent e)
           {
               
           }
       });
       p1.add(b);
       con.add(p1,BorderLayout.NORTH);
       
       
       this.setSize(300,300);
   }
}
