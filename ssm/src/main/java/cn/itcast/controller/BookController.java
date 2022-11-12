package cn.itcast.controller;

import cn.itcast.dao.BookDao;
import cn.itcast.domain.Book;
import cn.itcast.domain.Page;
import cn.itcast.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author fail
 * @create 2021-03-23 14:04
 */

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    // 查询book数据显示给用户（无页面）
    @RequestMapping("/list")
    public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //查询所有book数据
        List<Book> books = bookService.queryAllBook();
        //保存到request中
        req.setAttribute("books", books);
        //请求转发到book_manager.jsp中
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    // 查询book数据显示给用户修改
    @RequestMapping("/getBook")
    public void getBook(@RequestParam("name") String name, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //查询book
        Book book = bookService.queryByName(name);
        // 保存book到request中
        req.setAttribute("book", book);
        // 请求转发到book_edit.jsp中
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    // 删除book数据
    @RequestMapping("/delete")
    public void delete(@RequestParam("name") String name, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //删除
        bookService.deleteByName(name);
        //得到pageno跳转到之前的页数
        int pageNoInt = req.getParameter("pageNo") != null ? Integer.parseInt(req.getParameter("pageNo")) : 1;
        // 重定向到book_manager.jsp
        resp.sendRedirect(req.getContextPath() + "/book/manager?pageNo=" + pageNoInt);
    }

    // 更新book数据
    @RequestMapping("/update")
    public void update(Book book, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //将更新保存
        bookService.update(book);
        //得到pageno跳转到之前的页数
        int pageNoInt = req.getParameter("pageNo") != null ? Integer.parseInt(req.getParameter("pageNo")) : 1;
        // 重定向到book_manager.jsp
        resp.sendRedirect(req.getContextPath() + "/book/manager?pageNo=" + pageNoInt);

    }

    // 添加book数据
    @RequestMapping("/add")
    public void add(Book book, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //保存到t_book中
        bookService.add(book);
        //得到pageno跳转到之前的页数
        int pageNoInt = req.getParameter("pageNo") != null ? Integer.parseInt(req.getParameter("pageNo")) : 1;
        // 重定向到book_manager.jsp
        resp.sendRedirect(req.getContextPath() + "/book/manager?pageNo=" + pageNoInt);
    }


    // 查询book数据显示给用户（页面）
    @RequestMapping("/page")
    public void page(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取请求的参数 pageNo 和 pageSize
        int pageNoInt = req.getParameter("pageNo") != null ? Integer.parseInt(req.getParameter("pageNo")) : 1;
        int pageSizeInt = req.getParameter("pageSize") != null ? Integer.parseInt(req.getParameter("pageSize")) : Page.PAGE_SIZE;
        // 保存到Request域中
        Page<Book> page = bookService.page(pageNoInt, pageSizeInt);
        page.setUrl("/ssm_war/book/page");
        req.setAttribute("page", page);
        // 请求转发给client中的index.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    // 进入管理员管理书籍数据界面
    @RequestMapping("/manager")
    public void manager(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取请求的参数 pageNo 和 pageSize
        int pageNo = req.getParameter("pageNo") != null ? Integer.parseInt(req.getParameter("pageNo")) : 1;
        int pageSize = req.getParameter("pageSize") != null ? Integer.parseInt(req.getParameter("pageSize")) : Page.PAGE_SIZE;
        // 保存到Request域中
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("/ssm_war/book/manager");
        req.setAttribute("page", page);
        // 请求转发给client中的book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);

    }

    // 根据传入的大小来返回页面的数据
    @RequestMapping("/pageByPrice")
    public void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取请求的参数 pageNo  pageSize max min
        int pageNo = req.getParameter("pageNo") != null ? Integer.parseInt(req.getParameter("pageNo")) : 1;
        int pageSize = req.getParameter("pageSize") != null ? Integer.parseInt(req.getParameter("pageSize")) : Page.PAGE_SIZE;
        int min = req.getParameter("min") != "" ? Integer.parseInt(req.getParameter("min")) : 0;
        int max = req.getParameter("max") != "" ? Integer.parseInt(req.getParameter("max")) : 99999;
        //  数据有效性
        if (min < 0) {
            min = 0;
        }
        if (max < 0) {
            max = 99999;
        }

        // 保存到Request域中
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);
        page.setUrl("/ssm_war/book/pageByPrice");
        req.setAttribute("page", page);
        // 请求转发给client中的index.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);


    }


}
