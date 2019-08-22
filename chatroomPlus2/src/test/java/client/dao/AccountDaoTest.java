package client.dao;

import client.entity.User;
import org.junit.Test;

public class AccountDaoTest {
    AccountDao accountDao=new AccountDao();
    @Test
    public void userReg() {
        User user=new User();
        user.setUsername("王大姐");
        user.setPassword("1234");
        user.setBrif("你真帅");
       boolean flag=accountDao.userReg(user);

    }

    @Test
    public void userLogin() {
        String userName="王大姐";
        String password="1234";
        User user1=accountDao.userLogin(userName,password);
        System.out.println(user1.getBrif());
    }
}