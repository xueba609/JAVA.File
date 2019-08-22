package client.service;

import client.dao.AccountDao;
import client.entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class userReg {
    private JPanel userRegPanel;
    private JLabel username;
    private JTextField userNameText;
    private JPasswordField passwordText;
    private JTextField brefText;
    private JLabel password;
    private JButton reg;
    private JLabel bref;
    private AccountDao accountDao=new AccountDao();
    public userReg(){
        JFrame frame = new JFrame("用户注册");
        frame.setContentPane(userRegPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        //点击注册按钮，如果成功，把数据写入到数据库中，弹出成功提示框
        reg.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户信息注册信息
                String username=userNameText.getText();
                String password=String.copyValueOf(passwordText.getPassword());
                String bref=brefText.getText();
                //将输入的信息包装成为User类，保存在数据库中
                User user=new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setBrif(bref);
                //然后把user给dao层的注册方法
                //这个方法会返回true/false
                if(accountDao.userReg(user)){
                    //返回成功界面
                    JOptionPane.showMessageDialog(frame,"注册成功!",
                            "提示信息",JOptionPane.INFORMATION_MESSAGE);
                   frame.setVisible(false);
                }else {
                    //返回失败页面
                    JOptionPane.showMessageDialog(frame,"注册失败","提示信息",
                            JOptionPane.ERROR_MESSAGE);

                }
            }
        });
    }
}
