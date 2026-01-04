package Java.Learn.Gui;

import java.awt.event.*;
import javax.swing.*;

public class EventHandler {
    public static void main(String[] args) {
        JFrame login=new JFrame("Login");
        JPanel panel=new JPanel();
        login.add(panel);

        login.setSize(400,300);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button=new JButton("登录");
        panel.add(button);

        // button.addActionListener(e->{
        //     JOptionPane.showMessageDialog(login,"登录成功");
        // });
        
        login.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent kl){
                int keyCode=kl.getKeyCode();
                if(keyCode==KeyEvent.VK_ENTER){
                    JOptionPane.showMessageDialog(login,"登录成功");
                }else{
                    JOptionPane.showMessageDialog(login,"登录失败");
                }
            }
        });
        login.setVisible(true);
        login.requestFocus();
    }
}
