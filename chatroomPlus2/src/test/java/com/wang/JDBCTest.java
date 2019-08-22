package com.wang;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import Utils.CommUtils;
import org.junit.Test;
import java.sql.*;
import java.util.Properties;

public class JDBCTest {
    private static DruidDataSource dataSource;

    static {
        Properties properties = CommUtils.loadProperties("datasource.properties");
        try {
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQuery() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String sql = "select * from user";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String username = resultSet.getNString("username");
                String passward = resultSet.getNString("passward");
                String brief = resultSet.getNString("brief");
                System.out.println("id: " + id + ",username: " + username
                        + ",passward: " + passward + ",prief: " + brief);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Test
    public void testInsert() {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            String sql = "insert into user(username, password, brief) values (?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "test1");
            statement.setString(2, "123");
            statement.setString(3, "test user!");
            int rows = statement.executeUpdate();
            System.out.println("Rows impacted:" + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, statement);
        }
    }

    @Test
    public void testDelete() {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            String sql = "delete from user where username = 'test1'";
            statement = connection.prepareStatement(sql);
            int rows = statement.executeUpdate();
            System.out.println("Rows impacted:" + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, statement);
        }

    }

    private void close(Connection connection, PreparedStatement statement) {
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

    private void close(Connection connection,PreparedStatement statement,ResultSet resultSet){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(connection,statement);
    }

}
