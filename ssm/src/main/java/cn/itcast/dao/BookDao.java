package cn.itcast.dao;

import cn.itcast.domain.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 多个参数的时候需要给每个参数前面加上@param
 *
 * @author fail
 * @create 2021-03-23 13:40
 */
@Repository
public interface BookDao {

    //更新
    @Update("update t_book set name=#{name}, author=#{author}, price=#{price}, sales=#{sales}, stock=#{stock}, img_path=#{imgPath} where name=#{name} and author=#{author}")
    public void update(Book book);

    //添加
    @Insert("insert into t_book (name, author, price, sales, stock, img_path) values (#{name}, #{author}, #{price}, #{sales}, #{stock}, #{imgPath})")
    public void add(Book book);

    //删除
    @Delete("delete from t_book where name=#{name}")
    public void deleteByName(String name);

    //查询所有book
    @Select("select * from t_book")
    @ResultMap(value = "t_book")
    public List<Book> queryAllBook();

    //查询book通过name
    @Select("select * from t_book where name=#{name}")
    @ResultMap(value = "t_book")
    public Book queryByName(String name);

    //查询book通过id
    @Select("select * from t_book where id=#{id}")
    @ResultMap(value = "t_book")
    public Book queryById(Integer id);

    //查询book总记录数
    @Select("select count(*) from t_book")
    public Integer totalCount();


    //查询价格的最大和最小得到总记录数
    @Select("select count(*) from t_book where price between #{minPrice} and #{maxPrice}")
    public Integer totalCountByPrice(@Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice);


    //查询当前页码得到要显示的book数据
    @Select("select * from t_book limit #{begin},#{pageSize}")
    @ResultMap(value = "t_book")
    public List<Book> pageItems(@Param("begin") int begin, @Param("pageSize") int pageSize);


    //查询价格的最大和最小得到的book数据
    @Select("select * from t_book where price between #{minPrice} and #{maxPrice} limit #{begin},#{pageSize}")
    @Results(id = "t_book", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "author", property = "author"),
            @Result(column = "price", property = "price"),
            @Result(column = "sales", property = "sales"),
            @Result(column = "stock", property = "stock"),
            @Result(column = "img_path", property = "imgPath"),
    })
    public List<Book> pageItemsByPrice(@Param("begin") int begin, @Param("pageSize") int pageSize, @Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice);


}
