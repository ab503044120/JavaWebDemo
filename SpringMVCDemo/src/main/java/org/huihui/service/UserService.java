package org.huihui.service;

import org.huihui.dao.UserDao;
import org.huihui.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/6/26.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
    @Resource
    private UserDao mUserDao;

    public User getUserById(String userId) {
        return mUserDao.selectUserById(userId);
    }
}
