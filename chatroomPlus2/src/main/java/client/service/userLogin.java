package client.service;

import VO.MessageVO;
import client.dao.AccountDao;
import client.entity.User;
import Utils.CommUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.Set;

public class userLogin {
    private JPanel userLogin;
    private JTextField usernameText;
    private JPasswordField passwordText;
    private JButton reg;
    private JButton logn;
    private JLabel username;
    private JLabel password;
    private JPanel QQ;
    private JFrame frame;

    AccountDao accountDao=new AccountDao();
    public userLogin(){
         frame = new JFrame("用户登录");
        frame.setContentPane(userLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        //注册按钮
        reg.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //弹出注册页面
                new userReg();
            }
        });
        //登录页面
        logn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //校验用户信息
                String username=usernameText.getText();
                String password= String.valueOf(passwordText.getPassword());
                User user=accountDao.userLogin(username,password);

                //成功加载用户列表
                    if(user!=null) {
                        JOptionPane.showMessageDialog(frame,"登录成功",
                                "提示信息",JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);
                        //与服务器建立链接，将当前用户

                        ConnectServer connection = new ConnectServer();
                        MessageVO msg2Server=new MessageVO();
                        msg2Server.setType("1");
                        msg2Server.setContent(username);
                        //发送的内容序列化为json字符串
                        String json2Server =CommUtils.object2Json(msg2Server);
                        try {
                            PrintStream out=new PrintStream(connection.getout(),true,
                                    "UTF-8");
                            out.println(json2Server);
                            //读取服务端发回的所有在线用户信息
                            Scanner in=new Scanner(connection.getIn());
                            if(in.hasNextLine()){
                                String msgFromServerStr=in.nextLine();
                                MessageVO mesFromServer=
                                        (MessageVO) CommUtils.Json2object
                                                (msgFromServerStr,MessageVO.class);
                                Set<String> users=
                                        (Set<String>) CommUtils.Json2object
                                                (mesFromServer.getContent(),Set.class);
                                System.out.println("所有在线用户为"+users);
                                //加载用户列表界面
                                //将当前用户名，所有在线好友，与服务器建立连接传递到好友列表中
                                new FriendsList(username,users,connection);

                            }
                        } catch (UnsupportedEncodingException e1) {
                            e1.printStackTrace();
                        }
                    }else{
                        //失败，停留在当前登录页面，提示用用户信息错误
                        JOptionPane.showMessageDialog(frame,"登录失败","提示信息",
                                JOptionPane.ERROR_MESSAGE);
                    }
            }
        });

    }
    public static void main(String[] args) {
      userLogin  userLogin=new userLogin();
    }
}
