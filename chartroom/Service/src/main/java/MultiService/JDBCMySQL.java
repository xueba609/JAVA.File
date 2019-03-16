package MultiService;

import java.sql.*;

public abstract class JDBCMySQL {
    private String user;
    private String password;
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet=null;
    private static int result=0;
    public static ResultSet getResultSet(){
        return resultSet;
    }
    public static int  getResult(){return result;}
    public  JDBCMySQL(String user,String password){
        this.user=user;
        this.password=password;
    }


        public void runnable(String sql){
            //1.加载驱动
            loadDriver();
            //2.建立数据库连接
            setConnection();
            //3.创建操作命令
            creatCommand(connection);
            //4.执行sql语句
            executeSql(sql);
            //5.处理结果集
            try {
                //当为查询的时候才遍历结果集
                if(sql.startsWith("select")){
                    dealResult();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
               //6提交
                commit();
                //7关闭结果集
                colseAll();
            }
    private static void loadDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static void setConnection() {
        try {
            Connection  connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatroom?user=root&password=123456&useSSL=false");
            connection = connection1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void creatCommand(Connection connection) {
        try {
            connection.setAutoCommit(false);
            Statement   statement1 = connection.createStatement();
            statement = statement1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void executeSql(String sql) {
        try {
            if(sql.startsWith("select")){
                resultSet = statement.executeQuery(sql);
            }else{
                result = statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //设置抽象方法，
    abstract void dealResult() throws SQLException;
    private void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void colseAll() {
        if (resultSet != null) {
            try {
                resultSet.close();
                //关闭命令
                statement.close();
                //关闭结果命令
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}