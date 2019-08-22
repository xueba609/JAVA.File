package client.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import Utils.CommUtils;
import java.sql.*;
import java.util.Properties;
/*
 *封装基础dao操作，获取数据源、连接、关闭资源等
 */
public class BaseDao {
    private static DruidDataSource druidDataSource;
    static{
        //加载
        Properties properties=CommUtils.loadProperties
                ("dataSource.properties");
        try {
            druidDataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            System.out.println("数据源加载失败");
            e.printStackTrace();
        }
    }
    //连接
    protected DruidPooledConnection getConnection(){
        try {
            return (DruidPooledConnection) druidDataSource.getPooledConnection();
        } catch (SQLException e) {
            System.out.println("获取连接失败");
            e.printStackTrace();
        }
        return null;

    }
    //关闭
    protected  void close(Connection connection, PreparedStatement statement) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    protected  void close(Connection connection, PreparedStatement statement, ResultSet resultSet){
        close(connection,  statement);
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
