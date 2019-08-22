package client.service;

import Utils.CommUtils;
import VO.MessageVO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class FriendsList {
    private JPanel friendsPanel;
    private JButton createGroupBtn;
    private JScrollPane friendsList;
    private JScrollPane groupListPanel;
    private JFrame frame;

    private String userName;
    private Set<String> users;
    // 存储所有群名称以及群好友
    private Map<String,Set<String>> groupList = new ConcurrentHashMap<>();
    private ConnectServer connectServer;

    //缓存私聊界面
    private Map<String,privateChatGUI> privateChatGUIList =new ConcurrentHashMap<>();
    //缓存群聊界面
    private Map<String,groupChatGUI> groupChatGUIList=new ConcurrentHashMap<>();

    //好友列表后台任务，不断监听服务器发来的信息
    //好友上线信息,用户私聊，群聊(这个类用于处理）
    private class DaemonTask implements Runnable{
        //获取输入流
        private Scanner in = new Scanner(connectServer.getIn());
        @Override
        public void run() {
            while (true) {
                //收到服务器发来的信息
                if (in.hasNextLine()) {
                    String strFromServer = in.nextLine();
                    //判断过来的是否是json字符串{type:"1",content:""},json的开头有{
                    // 此时服务器发来的是一个json字符串
                    if (strFromServer.startsWith("{")) {
                        MessageVO messageVO= (MessageVO) CommUtils.Json2object(strFromServer,
                                MessageVO.class);
                        if(messageVO.getType().equals("2")){
                            //服务器发来的私聊信息
                            String friendName=messageVO.getContent().split("-")[0];
                            String msg=messageVO.getContent().split("-")[1];
                            //判断私聊是否是第一次创建
                            if(privateChatGUIList.containsKey(friendName)){
                                //直接激活，不需要新创建
                                privateChatGUI privateChatGUI1=privateChatGUIList.get(friendName);
                                privateChatGUI1.getFrame().setVisible(true);
                                privateChatGUI1.readFromServer(friendName+"说"+msg);
                            }else {
                                privateChatGUI privateChatGUIL=new privateChatGUI(friendName,
                                        userName,connectServer);
                                privateChatGUIList.put(friendName,privateChatGUIL);
                                privateChatGUIL.readFromServer(friendName+"说"+msg);
                            }
                        }else if(messageVO.getType().equals("4")){
                            //收到服务器发来的群聊消息
                            //content:发送者的话
                            //to:接受的人
                            String groupName = messageVO.getTo().split("-")[0];
                            String senderName = messageVO.getContent().split("-")[0];
                            String groupMsg = messageVO.getContent().split("-")[1];
                            // 若此群名称在群聊列表
                            if (groupList.containsKey(groupName)) {
                                if (groupChatGUIList.containsKey(groupName)) {
                                    // 群聊界面弹出
                                    groupChatGUI groupChatGUI = groupChatGUIList.get(groupName);
                                    groupChatGUI.getFrame().setVisible(true);
                                    groupChatGUI.readFromServer(senderName+"说:"+groupMsg);
                                }else {
                                    Set<String> names = groupList.get(groupName);
                                    groupChatGUI groupChatGUI = new groupChatGUI(groupName,
                                            names,userName,connectServer);
                                    groupChatGUIList.put(groupName,groupChatGUI);
                                    groupChatGUI.readFromServer(senderName+"说:"+groupMsg);
                                }
                            }else {
                                // 若群成员第一次收到群聊信息
                                // 1.将群名称以及群成员保存到当前客户端群聊列表
                                Set<String> friends = (Set<String>) CommUtils.Json2object(messageVO.getTo().split("-")[1],
                                        Set.class);
                                groupList.put(groupName, friends);
                                loadGroupList();
                                // 2.弹出群聊界面
                                groupChatGUI groupChatGUI = new groupChatGUI(groupName,
                                        friends,userName,connectServer);
                                groupChatGUIList.put(groupName,groupChatGUI);
                                groupChatGUI.readFromServer(senderName+"说:"+groupMsg);
                            }

                        }
                    }else {
                        //否则是新用户登录
                        // newLogin:userName
                        if (strFromServer.startsWith("newLogin:")) {
                            String newFriendName = strFromServer.split(":")[1];
                            users.add(newFriendName);
                            // 弹框提示用户上线
                            JOptionPane.showMessageDialog(frame,
                                    newFriendName+"上线了!",
                                    "上线提醒",JOptionPane.INFORMATION_MESSAGE);
                            // 刷新好友列表
                            loadUsers();
                        }
                    }


                            }
                        }
                    }
                }


    //当你私聊的时候，你需要拿鼠标点击标签，因此使用 MouseListener事件
    //私聊点击事件
    private class PrivateLableAction implements MouseListener{
        //保存一下你点的谁
        private String labelName;
        //鼠标点击执行事件
        public PrivateLableAction(String labelName){
            this.labelName=labelName;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            //判断好友列表私聊界面缓存是否已经有指定标签
            if(privateChatGUIList.containsKey(labelName)){
                //如果存在，获取得到这个对象
                privateChatGUI privateChatGUI= privateChatGUIList.get(labelName);
                //然后将窗口视为可见
                privateChatGUI.getFrame().setVisible(true);
            }else {
                //第一次点击，创建私聊界面
                privateChatGUI privateChatGUI = new privateChatGUI(
                        labelName,userName,connectServer
                );
                //然后加入map里面
                privateChatGUIList.put(labelName,privateChatGUI);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    //群聊点击事件
    private class groupLabelAction implements MouseListener {
        private String groupName;

        public groupLabelAction(String groupName) {
            this.groupName = groupName;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (groupChatGUIList.containsKey(groupName)) {
                groupChatGUI groupChatGUI = groupChatGUIList.get(groupName);
                groupChatGUI.getFrame().setVisible(true);
            }else {
                Set<String> names = groupList.get(groupName);
                groupChatGUI groupChatGUI = new groupChatGUI(
                        groupName,names,userName,connectServer
                );
                groupChatGUIList.put(groupName,groupChatGUI);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    public FriendsList(String userName, Set<String> users,
                       ConnectServer connect2Server) {
        this.userName = userName;
        this.users = users;
        this.connectServer = connect2Server;
        frame = new JFrame(userName);
        frame.setContentPane(friendsPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        loadUsers();
        //启动后台线程不断监听服务器发来的信息
        Thread daemonThread=new Thread(new DaemonTask());
        daemonThread.setDaemon(true);
        daemonThread.start();
        //创建群组
        createGroupBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new createGroupGUI(userName,users,connectServer,
                   FriendsList.this);
            }
        });
    }
    //将用户信息以标签的形势加载到列表中
    public void loadUsers(){
        //长度就是传来的用户长度
        JLabel[] userLabels = new JLabel [users.size()];
        //这个JPanel用来存放标签
        JPanel friends = new JPanel();
        //以盒子的形式，垂直排列
        friends.setLayout(new BoxLayout(friends,BoxLayout.Y_AXIS));
        //进行遍历
        Iterator<String> iterator = users.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            String userName = iterator.next();
            userLabels[i] = new JLabel(userName);
            //当用户列表加载后，添加一个列表点击事件(私聊的窗口点击事件）,
            //这样就给每个用户设置了标签事件
            userLabels[i].addMouseListener(new PrivateLableAction(userName));
            friends.add(userLabels[i]);
            i++;
        }
        //把这个列表添加到最外面的panel
        friendsList.setViewportView(friends);
        //设置滚动条垂直滚动
        friendsList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        friends.revalidate();
        friendsList.revalidate();
    }
    public void loadGroupList() {
        // 存储所有群名称标签Jpanel
        JPanel groupNamePanel = new JPanel();
        groupNamePanel.setLayout(new BoxLayout(groupNamePanel,
                BoxLayout.Y_AXIS));
        JLabel[] labels = new JLabel[groupList.size()];
        // Map遍历
        Set<Map.Entry<String,Set<String>>> entries = groupList.entrySet();
        Iterator<Map.Entry<String,Set<String>>> iterator =
                entries.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Map.Entry<String,Set<String>> entry = iterator.next();
            labels[i] = new JLabel(entry.getKey());
            labels[i].addMouseListener(new groupLabelAction(entry.getKey()));
            groupNamePanel.add(labels[i]);
            i++;
        }
        groupListPanel.setViewportView(groupNamePanel);
        groupListPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        groupListPanel.revalidate();
    }
    public void addGroup(String groupName,Set<String> friends) {
        groupList.put(groupName,friends);
    }

}
