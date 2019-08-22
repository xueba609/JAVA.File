package client.dao;

import client.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;

public class AccountDao extends BaseDao{
    //用户注册  insert
    public boolean userReg(User user){
        Connection connection=null;
        PreparedStatement statement=null;
        //等于父类的链接
        try {
            connection = getConnection();
            String sql="insert into user(username,password,brief)"+
                    "values(?,?,?)";
            statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,user.getUsername());
            statement.setString(2,DigestUtils.md5Hex(user.getPassword()));
            statement.setString(3,user.getBrif());
            int rows=statement.executeUpdate();
            if(rows==1){
                return true;
            }
        }catch (SQLException e){
            System.out.println("用户注册失败");
            e.printStackTrace();
        }finally {
           close(connection,statement);
        }
        return false;
    }

    //登录
    public User userLogin(String userName,String password){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try{
            connection = getConnection();
            String sql="select * from user where username = ?" +
                    "and password= ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,userName);
            statement.setString(2,DigestUtils.md5Hex(password));
            resultSet=statement.executeQuery();
           while(resultSet.next()){
                User user=getUser(resultSet);
                return user;
            }
        }catch (Exception e){
            System.out.println("用户登录失败");
            e.printStackTrace();
        }finally {
            close(connection,statement,resultSet);
        }
        return null;
    }

    private User getUser(ResultSet resultSet) throws Exception {
        User user=new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setBrif(resultSet.getString("brief"));
        return user;
    }
}
