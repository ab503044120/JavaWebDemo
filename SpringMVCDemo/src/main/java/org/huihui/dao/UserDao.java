package org.huihui.dao;

import org.huihui.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/6/26.
 */
@Repository
public interface UserDao {
    User selectUserById(String userId);
}
