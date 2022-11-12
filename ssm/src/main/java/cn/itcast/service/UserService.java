package cn.itcast.service;

import cn.itcast.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @author fail
 * @create 2021-03-22 15:49
 */


public interface UserService {

    //保存用户
    public Integer saveUser(User user);

    //查询用户是否存在(通过用户名和密码)
    public User findUserByNameAndPassword(User user);

    //查询用户是否存在(通过用户名)
    public Boolean findByName(String Name);
}
