package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fail
 * @create 2021-03-22 15:49
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public Integer saveUser(User user) {
        System.out.println("业务层保存用户");
        Integer integer = userDao.saveUser(user);
        return integer;
    }


    @Override
    public User findUserByNameAndPassword(User user) {
        System.out.println("业务层查询用户通过用户名和密码");
        User u = userDao.findUserByNameAndPassword(user);
        return u;
    }

    /**
     * 存在返回true，不存在返回false
     * @param Name
     * @return
     */
    @Override
    public Boolean findByName(String Name) {
        System.out.println("业务层查询用户通过用户名");
        User u = userDao.findByName(Name);
        if (u != null) {
            return true;
        }
        return false;
    }
}
