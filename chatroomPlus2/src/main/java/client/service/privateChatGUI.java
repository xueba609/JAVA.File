package client.service;

import Utils.CommUtils;
import VO.MessageVO;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class privateChatGUI {
    private JPanel privateChatPanel;
    private JTextArea readFromServer;
    private JTextField sendToServer;
    private   JFrame frame;
    //私聊的对象的名字
    private String friendName;
    //自己的名字也要
    private String myName;
    //要连接
    private ConnectServer connectServer;
    //
    private PrintStream out;
    //通过web传入私聊的对象
    public privateChatGUI(String friendName,String myName,ConnectServer connectServer){
        this.friendName=friendName;
        this.myName=myName;
        this.connectServer=connectServer;
            try {
                this.out=new PrintStream(connectServer.getout(),
                        true,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        frame = new JFrame("与"+friendName+"私聊中...");
        frame.setContentPane(privateChatPanel);
        //设置窗口关闭的操作，将其设置为隐藏
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        //如果私聊对象再发过来，就唤醒
        frame.setVisible(true);
        //捕捉输入框键盘输入
        sendToServer.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                StringBuilder sb = new StringBuilder();
                sb.append(sendToServer.getText());
                // 1.当捕捉到按下Enter
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // 2.将当前信息发送到服务端
                    String msg = sb.toString();
                    MessageVO messageVO = new MessageVO();
                    messageVO.setType("2");
                    messageVO.setContent(myName+"-"+msg);
                    messageVO.setTo(friendName);
                    privateChatGUI.this.out.println(CommUtils.object2Json(messageVO));
                    // 3.将自己发送的信息展示到当前私聊界面
                    readFromServer(myName+"说:"+msg);
                    sendToServer.setText("");
                }
            }
        });
    }
     public void readFromServer(String str){
        readFromServer.append(str+"\n");
     }
     public JFrame getFrame(){
        return frame;
     }


}
