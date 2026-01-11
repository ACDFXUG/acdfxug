package Java.Learn.Network.ChatSystem;

import javax.swing.*;
import Java.Learn.Network.ChatSystem.UI.LoginUI;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 在事件调度线程中创建界面
        SwingUtilities.invokeLater(()->new LoginUI().setVisible(true));
    }
}
