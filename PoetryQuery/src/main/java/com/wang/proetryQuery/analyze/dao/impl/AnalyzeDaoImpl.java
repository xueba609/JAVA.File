package com.wang.proetryQuery.analyze.dao.impl;

import com.wang.proetryQuery.analyze.dao.AnalyzeDao;
import com.wang.proetryQuery.analyze.entity.PoetyInfo;
import com.wang.proetryQuery.analyze.model.AuthorCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnalyzeDaoImpl implements AnalyzeDao {
    private final Logger logger=LoggerFactory.getLogger(AnalyzeDaoImpl.class);
    public DataSource dataSource;
    public AnalyzeDaoImpl(DataSource dataSource){
        this.dataSource=dataSource;
    }
    @Override
    public List<AuthorCount> analyzeProductionAmount() {
        List<AuthorCount> data=new ArrayList<>();

        String sql="select count(*) as count,author from poety_info group by author ;";
        try(Connection connection=dataSource.getConnection();
            PreparedStatement statement= connection.prepareStatement(sql);
            ResultSet rs=statement.executeQuery()
            ) {
            while(rs.next()){
                AuthorCount p=new AuthorCount();
                p.setAuthor(rs.getString("author"));
                p.setCount(rs.getInt("count"));
                data.add(p);
            }
        } catch (SQLException e) {
            logger.error("Database query occur exception {}.",e.getMessage());
        }
        return data;
    }
     //从数据库把所有的用户信息取出来
    @Override
    public List<PoetyInfo> queryAllPoetyInfo() {
        List<PoetyInfo> datas = new ArrayList<>();
        String sql="select title,dynasty,author,content from poety_info;";
        try(Connection connection=dataSource.getConnection();
            PreparedStatement statement= connection.prepareStatement(sql);
            ResultSet rs =statement.executeQuery()
        ) {
            while(rs.next()){
                PoetyInfo poetyInfo=new PoetyInfo();
                poetyInfo.setTitle(rs.getString("title"));
                poetyInfo.setDynasty(rs.getString("dynasty"));
                poetyInfo.setAuthor(rs.getString("author"));
                poetyInfo.setContent(rs.getString("content"));
                datas.add(poetyInfo);
            }
        } catch (SQLException e) {
           logger.error("Database query occur exception{}.",e.getMessage());
        }
        return datas;
    }
}
