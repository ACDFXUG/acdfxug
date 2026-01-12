package Java.Learn.Network.ChatSystem.Client;

import javax.swing.*;
import java.awt.*;
import java.io.DataOutputStream;
import java.net.*;

public class LoginUI extends JFrame {
    private JTextField nicknameField;
    private JButton confirmButton;
    private JButton cancelButton;
    private JLabel label;  // 将label声明为成员变量
    private Socket client;
    
    public LoginUI() {
        initializeComponents();
        setupLayout();
        addEventListeners();
    }
    
    private void initializeComponents() {
        // 设置窗口标题
        setTitle("局域网聊天室");
        
        // 创建组件
        label = new JLabel("请输入昵称:");  // 初始化成员变量
        nicknameField = new JTextField(30);  // 增加输入框宽度
        confirmButton = new JButton("确认");
        cancelButton = new JButton("取消");
        
        // 设置窗口基本属性
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // 设置组件间距 - 增大间距
        gbc.insets = new Insets(20, 20, 20, 20);
        
        // 添加标签
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(label, gbc);
        
        // 添加昵称输入框
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(nicknameField, gbc);
        
        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // 跨两列
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);
        
        // 设置窗口大小并居中显示
        pack();
        setSize(500, 200);  // 设置固定的窗口大小
        setLocationRelativeTo(null);
    }
    
    private void addEventListeners() {
        // 确认按钮事件
        confirmButton.addActionListener(e->{
            String nickname = nicknameField.getText().trim();
            if (nickname.isEmpty()) {
                JOptionPane.showMessageDialog(LoginUI.this, 
                    "请输入有效的昵称！", 
                    "提示", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // 这里可以添加登录逻辑
            try{
                onConfirm(nickname);
                System.out.println("登录成功");
                // 创建聊天界面
                new ChatUI(nickname,client);
                dispose();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        
        // 取消按钮事件
        cancelButton.addActionListener(e->onCancel());
        
        // 支持回车键确认
        getRootPane().setDefaultButton(confirmButton);
    }
    
    // 确认按钮点击后的处理方法
    protected void onConfirm(String nickname) throws Exception {
        // 子类可重写此方法实现具体登录逻辑
        // System.out.println("用户输入的昵称: " + nickname);
        // JOptionPane.showMessageDialog(this, "欢迎, " + nickname + "!");
        // dispose(); // 关闭登录窗口
        client=new Socket(Constant.SERVER_IP,Constant.SERVER_PORT);
        DataOutputStream dos=new DataOutputStream(client.getOutputStream());
        dos.writeInt(Constant.LOGIN);
        dos.writeUTF(nickname);
        dos.flush();
    }
    
    // 取消按钮点击后的处理方法
    protected void onCancel() {
        System.exit(0); // 关闭程序
    }
    
    // 获取昵称的方法
    public String getNickname() {
        return nicknameField.getText().trim();
    }
    
    public static void main(String[] args) {
        // 设置界面外观为系统默认样式
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 在事件调度线程中创建界面
        SwingUtilities.invokeLater(()->new LoginUI().setVisible(true));
    }
}