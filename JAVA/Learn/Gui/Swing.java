package Java.Learn.Gui;

import javax.swing.*;

public class Swing {
    public static void main(String[] args) {
        JFrame window=new JFrame("Test");
        window.setSize(400,300);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel=new JPanel();
        panel.setBounds(100,100,80,30);
        window.add(panel);


        JButton button=new JButton("Click me");
        panel.add(button);
        window.setVisible(true);
    }
}
