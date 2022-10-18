package com.lionlike.dao;

import com.lionlike.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class UserDaoTest {
    @Test
    void addAndSelect() {
        UserDao userDao = new UserDao();
        String id = "2";
        User user = new User(id,"plz", "110055");
        userDao.add(user);

        User selectedUser = userDao.findById(id);
        Assertions.assertEquals("plz",selectedUser.getName());
        Assertions.assertEquals("110055",selectedUser.getPassword());
    }
}