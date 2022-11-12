package cn.itcast.service;

import cn.itcast.domain.Book;
import cn.itcast.domain.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author fail
 * @create 2021-03-23 14:02
 */

public interface BookService {

    //更新
    public void update(Book book);

    //添加
    public void add(Book book);

    //删除
    public void deleteByName(String name);

    //查询所有book
    public List<Book> queryAllBook();

    //查询book通过name
    public Book queryByName(String name);

    //查询book通过name
    public Book queryById(int id);

    //得到页数据
    Page<Book> page(int pageNo, int pageSize);

    //根据min，max得到页数据
    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
