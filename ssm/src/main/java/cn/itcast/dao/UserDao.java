package cn.itcast.dao;

import cn.itcast.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

/**
 * @author fail
 * @create 2021-03-22 15:37
 */
@Repository
public interface UserDao {

    //保存用户
    @Insert("insert into t_user (username, password, email) values (#{username}, #{password}, #{email})")
    @SelectKey(statement = "select last_insert_id()", keyColumn = "id", keyProperty = "id", before = false, resultType = Integer.class)
    public Integer saveUser(User user);

    //查询用户是否存在通过姓名和密码
    @Select("select * from t_user where username=#{username} and password=#{password}")
    public User findUserByNameAndPassword(User user);

    //查询用户是否存在
    @Select("select * from t_user where username=#{Name}")
    public User findByName(String Name);
}
