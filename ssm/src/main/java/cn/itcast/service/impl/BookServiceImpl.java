package cn.itcast.service.impl;

import cn.itcast.dao.BookDao;
import cn.itcast.domain.Book;
import cn.itcast.domain.Page;
import cn.itcast.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fail
 * @create 2021-03-23 14:03
 */

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public void update(Book book) {
        bookDao.update(book);
        System.out.println("更新" + book.getName() + "成功");
    }

    @Override
    public void add(Book book) {
        bookDao.add(book);
        System.out.println("添加" + book.getName() + "成功");

    }

    @Override
    public void deleteByName(String name) {
        bookDao.deleteByName(name);
        System.out.println("删除" + name + "成功");

    }

    @Override
    public List<Book> queryAllBook() {
        List<Book> books = bookDao.queryAllBook();
        System.out.println("查询所有book");
        return books;
    }

    @Override
    public Book queryByName(String name) {
        Book book = bookDao.queryByName(name);
        System.out.println("通过书名查询");
        return book;
    }

    @Override
    public Book queryById(int id) {
        return bookDao.queryById(id);
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();
        System.out.println("page");

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = bookDao.totalCount();
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Book> items = bookDao.pageItems(begin, pageSize);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<Book>();
        System.out.println("page min max");
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        int pageTotalCount = bookDao.totalCountByPrice(min, max);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Book> items = bookDao.pageItemsByPrice(begin, pageSize, min, max);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }


}
