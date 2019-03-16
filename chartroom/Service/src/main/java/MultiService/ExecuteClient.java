package MultiService;


import org.omg.CORBA.PRIVATE_MEMBER;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class ExecuteClient implements Runnable {
    //不可以被修改
    private static final ConcurrentHashMap<String, Socket> ONLINE_USER_MAP =
            new ConcurrentHashMap<String, Socket>();
    private static final ConcurrentHashMap<String,String >  RIGISTER_INFO=
            new ConcurrentHashMap<String, String>() ;
    private final Socket client;
    final JDBCMySQL jdbcMySQL = new JDBCMySQL("root", "123456") {
        @Override
        void dealResult() throws SQLException {
            ResultSet resultSet = this.getResultSet();
            while (resultSet.next()) {
                String usename = resultSet.getString("username");
                String userpassword = resultSet.getString("userpassword");
                RIGISTER_INFO.put(usename, userpassword);
            }
        }
    };

    public ExecuteClient(Socket client) {
        this.client = client;
    }

    public void run() {
        try {
            Scanner in = new Scanner(client.getInputStream());
            while (true) {
                String strAcceoptClient = in.nextLine();
                if (strAcceoptClient.startsWith("B")) {
                    backClientMessage(client, "请输入注册信息如：　userName:王五：11111");
                    String str = in.nextLine();
                    boolean s=true;
                    if (str.startsWith("userName")) {
                        String userName = str.split("\\:")[1];
                        String password = str.split("\\:")[2];
                        jdbcMySQL.runnable("select * from test_userinfo");
                        for (Map.Entry<String,String> entry : RIGISTER_INFO.entrySet()) {
                            if (userName.equals(entry.getKey()) && password.equals(entry.getValue())) {
                                backClientMessage(client, "用户已经存在，请重新注册");
                                s=false;
                            }
                        }

                        try {
                            if(s) {
                                register(userName, password, client);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    continue;
                }
                //登录
                if (strAcceoptClient.startsWith("A")) {
                    String userName = null;
                    String password = null;
                    backClientMessage(client, "请输入登录信息如：U: 王五: 11111");
                    String src = in.nextLine();
                    if (src.startsWith("U")) {
                        userName = src.split("\\:")[1];
                        password = src.split("\\:")[2];
                    }
                    jdbcMySQL.runnable("select * from test_userinfo");
                      for (Map.Entry<String,String> entry : RIGISTER_INFO.entrySet()) {
                          if (userName.equals(entry.getKey()) && password.equals(entry.getValue())) {
                              backClientMessage(client, "登录成功");
                          }
                      }
                    backClientMessage(client, "用户名或密码出错");
                    continue;
                }

                //私聊
                if (strAcceoptClient.startsWith("P")) {
                    String tagetuserName = strAcceoptClient.split("\\:")[1];
                    String message = strAcceoptClient.split("\\:")[2];
                    privateChat(tagetuserName, message);
                    continue;
                }
                //群聊
                if (strAcceoptClient.startsWith("G")) {
                    String message = strAcceoptClient.split("\\:")[1];
                    groupChat(message);
                    continue;
                }
                //停止
                if (strAcceoptClient.startsWith("byebye")) {
                    quit();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void quit() {
        String userName = null;
        for (String keyName : ONLINE_USER_MAP.keySet()) {
            if (ONLINE_USER_MAP.get(keyName).equals(client)) {
                userName = keyName;
            }
        }
        System.out.println(userName + "用户下线了");
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ONLINE_USER_MAP.remove(userName);
    }

    private void groupChat(String message) {
        for (Map.Entry<String, Socket> entry : ONLINE_USER_MAP.entrySet()) {
            privateChat(entry.getKey(),"群聊消息为："+message);
            System.out.println("群聊消息为：" + message);
        }
    }

    private void privateChat(String userName, String message) {
        Socket privateClient = ONLINE_USER_MAP.get(userName);
        if (privateClient != null) {
            backClientMessage(privateClient, "：" + message);
        }
    }

    private void register(String userName, String password,Socket client) throws SQLException {

        System.out.println(userName + "：注册成功");
        ONLINE_USER_MAP.put(userName,client);
        JDBCMySQL jdbcMySQL = new JDBCMySQL("root", "123456") {

            @Override
            void dealResult() throws SQLException {
            }
        };
            printOnlineNumber();
            backClientMessage(client,"成功");
            String sql=String.format("insert into test_userinfo values('%s','%s')",userName,password);
            jdbcMySQL.runnable(sql );

    }
        private void backClientMessage (Socket client, String message ){
            try {
                OutputStreamWriter serviceOut = new OutputStreamWriter(client.getOutputStream());
                serviceOut.write(message + "\n");
                serviceOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        private void printOnlineNumber () {
            System.out.println("当前人数为" + ONLINE_USER_MAP.size());
            System.out.println("用户名：");
            for (Map.Entry<String, Socket> entry : ONLINE_USER_MAP.entrySet()) {
                System.out.println(entry.getKey());
            }
        }
    }
