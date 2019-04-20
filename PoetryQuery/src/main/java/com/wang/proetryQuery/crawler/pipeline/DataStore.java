package com.wang.proetryQuery.crawler.pipeline;

import com.wang.proetryQuery.crawler.common.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataStore implements Pipeline {
    private final Logger logger=LoggerFactory.getLogger(DataStore.class);
    private final DataSource dataSource;
    public DataStore(DataSource dataSource){
        this.dataSource=dataSource;
    }
    @Override
    public void pipeline(Page page) {
        String title=(String)page.getDataSet().getData("title");
        String dynasty=(String)page.getDataSet().getData("dynasty");
        String author=(String)page.getDataSet().getData("author");
        String content=(String)page.getDataSet().getData("content");
        String sql="insert into poety_info(title,dynasty,author,content) values(?,?,?,?)" ;
        try(Connection connection=dataSource.getConnection();
            PreparedStatement statement=connection.prepareStatement(sql)
        ){
            statement.setString(1,title);
            statement.setString(2,dynasty);
            statement.setString(3,author);
            statement.setString(4,content);
            statement.executeUpdate();
        } catch (SQLException e) {
           logger.error("Database insert occur exception{}.",e.getMessage());
        }
    }
}
