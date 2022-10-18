package com.lionlike.dao;

import com.lionlike.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {

    private ConnectionMaker connectionMaker;

    public UserDao() {
        this.connectionMaker = new AWSConnectionMaker(); // 기본 아마존
    }

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user){
        Map<String, String> evn = System.getenv();
        try {
            // DB연동
            Connection c = connectionMaker.makeConnection();
            // Query 작성
            PreparedStatement ps = c.prepareStatement("" +
                    "INSERT INTO users(id,name,password) values(?,?,?);");
            ps.setString(1,user.getId());
            ps.setString(2,user.getName());
            ps.setString(3,user.getPassword());


            // Query 실행
            ps.executeUpdate();

            ps.close();
            c.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public User findById(String id){
        Map<String, String> evn = System.getenv();
        try {

            // DB연동
            Connection c = connectionMaker.makeConnection();

            // Query 작성
            PreparedStatement ps = c.prepareStatement("select * FROM users WHERE id = ?");
            ps.setString(1,id);


            ResultSet rs = ps.executeQuery(); //Resultset?
            rs.next();
            User user = new User(rs.getString("id"),rs.getString("name"),rs.getString("password"));

            rs.close();
            ps.close();
            c.close();

            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
//        userDao.add();
        User user = userDao.findById("6");
        System.out.println(user.getName());
    }
}
