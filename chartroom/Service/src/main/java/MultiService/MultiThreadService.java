package MultiService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class MultiThreadService {
    public static void main(String[] args)  {

        //连接数据库
        JDBCMySQL jdbcMySQL=new JDBCMySQL("root","123456") {
            @Override
            void dealResult() throws SQLException {
                ResultSet resultSet= JDBCMySQL.getResultSet();
                if(resultSet == null){
                    System.out.println("数据库表数据为空");
                    return;
                }
                while(resultSet.next()){
                    String username=resultSet.getString("username");
                    String userpassword=resultSet.getString("userpassword");
            }
            }
        };
        jdbcMySQL.runnable("select * from test_userinfo;");//默认端口
                    int port = 6667;
                    if(args.length>0){
                         port=Integer.parseInt(args[0]);
                    }
        //线程池
        ExecutorService executorService = Executors.newFixedThreadPool(20);
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                while (true) {
                    Socket client = serverSocket.accept();
                    //接受客户端连接，建立任务
                    executorService.submit(new ExecuteClient(client));
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

