package Java.Learn.Network.ChatSystem.Client;

import javax.swing.*;
import java.awt.*;
import java.io.DataOutputStream;
import java.net.Socket;

public class ChatUI extends JFrame {
    private JTextArea chatArea;           // 聊天显示区域
    private JTextArea onlineUsersArea;    // 在线用户列表
    private JTextField inputField;        // 输入框
    private JButton sendButton;           // 发送按钮
    private JScrollPane chatScrollPane;   // 聊天区域滚动条
    private JScrollPane usersScrollPane;  // 用户列表滚动条
    private Socket client;
    
    private ChatUI(Socket client) {
        initializeComponents();
        setupLayout();
        addEventListeners();
        this.client=client;
        setVisible(true);
    }
    
    public ChatUI(String nickname,Socket client) {
        this(client);
        setTitle("局域网聊天室 - " + nickname);
        Thread reader=new ClientReaderThread(this,client);
        reader.start();
    }
    
    private void initializeComponents() {
        // 设置窗口标题和基本属性
        setTitle("局域网聊天室");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 不直接关闭，稍后处理
        setMinimumSize(new Dimension(800, 600)); // 设置最小尺寸
        setExtendedState(JFrame.MAXIMIZED_BOTH); // 默认最大化
        
        // 初始化聊天显示区域
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatScrollPane = new JScrollPane(chatArea);
        chatScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        // 初始化在线用户列表
        onlineUsersArea = new JTextArea("在线用户:\n");
        onlineUsersArea.setEditable(false);
        usersScrollPane = new JScrollPane(onlineUsersArea);
        usersScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        // 初始化输入组件
        inputField = new JTextField(30);
        sendButton = new JButton("发送");
        
        // 设置发送按钮为默认按钮
        getRootPane().setDefaultButton(sendButton);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // 创建中央聊天区域和右侧用户列表的分割面板
        JSplitPane centerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        centerSplitPane.setDividerLocation(600); // 设置初始分割位置
        
        // 设置聊天区域
        centerSplitPane.setLeftComponent(chatScrollPane);
        centerSplitPane.setRightComponent(usersScrollPane);
        
        // 创建底部输入区域
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // 输入框放在中间
        bottomPanel.add(inputField, BorderLayout.CENTER);
        
        // 按钮放在右侧
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(sendButton);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);
        
        // 将组件添加到主窗口
        add(centerSplitPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        // 设置分割线的大小
        centerSplitPane.setDividerSize(5);
    }
    
    private void addEventListeners() {
        // 发送按钮事件
        sendButton.addActionListener(e->sendMessage());
        
        // 输入框回车事件
        inputField.addActionListener(e->sendMessage());
        
        // 窗口关闭事件
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int result = JOptionPane.showConfirmDialog(
                    ChatUI.this,
                    "确定要退出聊天室吗？",
                    "确认退出",
                    JOptionPane.YES_NO_OPTION
                );
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }
    
    // 发送消息的方法
    private void sendMessage() {
        String message = inputField.getText();
        if (!message.isEmpty()) {
            try {
                DataOutputStream dos=new DataOutputStream(client.getOutputStream());
                dos.writeInt(Constant.GROUP);
                dos.writeUTF(message);
                dos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
            inputField.setText(""); // 清空输入框
        }else{
            System.out.println("请输入内容");
        }
    }
    
    // 添加消息到聊天区域
    public void appendMessage(String message) {
        if (SwingUtilities.isEventDispatchThread()) {
            chatArea.append(message + "\n");
            chatArea.setCaretPosition(chatArea.getDocument().getLength()); // 自动滚动到底部
        } else {
            SwingUtilities.invokeLater(() -> {
                chatArea.append(message + "\n");
                chatArea.setCaretPosition(chatArea.getDocument().getLength());
            });
        }
    }
    
    // 更新在线用户列表
    public void updateOnlineUsers(String[] users) {
        StringBuilder userListText = new StringBuilder("在线用户 (" + users.length + "):\n");
        for (String user : users) {
            userListText.append("• ").append(user).append("\n");
        }
        
        if (SwingUtilities.isEventDispatchThread()) {
            onlineUsersArea.setText(userListText.toString());
        } else {
            SwingUtilities.invokeLater(() -> onlineUsersArea.setText(userListText.toString()));
        }
    }
}