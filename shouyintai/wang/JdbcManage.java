package com.wang;
import java.sql.*;
public abstract class JdbcManage {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private int result = -1;

    public ResultSet getResultSet() {
        return resultSet;
    }

    public int getResult() {
        return result;
    }

    //commit
    public final void myCommit(){
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //rollback(比较简单，没有创建保存点，所以回滚也就只能直接回滚到头)
    public final void myrollback(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //1.加载驱动程序
    public final void loadDrive(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //2.连接数据库
    public final Connection connect(String user,String password){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkstand",user,password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connection = connection;
        return connection;
    }
    //3.创建命令
    public final Statement createSql() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.statement = statement;
        return statement;
    }
    //4.执行用户命令
    public final void executeOrder(String sql){
        try{
            if(sql.substring(0,1).equalsIgnoreCase("s")){
                resultSet = statement.executeQuery(sql);
            }else{
                this.result = statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //5.处理结果集
    public abstract ResultSet handle();
    //6.关闭所有流
    public final void colseAll(){
        try{
            if(resultSet != null){
                resultSet.close();
            }
            statement.close();
            connection.close();
            result = -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

