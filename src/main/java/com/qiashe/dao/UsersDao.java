package com.qiashe.dao;

import com.qiashe.domain.User;

public interface UsersDao {
    User selectByName(String userName);
}
