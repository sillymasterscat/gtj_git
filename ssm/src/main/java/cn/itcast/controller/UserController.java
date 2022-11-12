package cn.itcast.controller;

import cn.itcast.domain.Cart;
import cn.itcast.domain.User;
import cn.itcast.service.CartService;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author fail
 * @create 2021-03-22 16:51
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private CartService cartService;

    //封装user
    @ModelAttribute
    public void useModelAttribute(String username, String password, String email, Map<String, User> map) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        map.put("user", user);

    }

    /**
     * 注销账户
     * @param request
     * @param response
     */
    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 销毁 Session 中用户登录的信息（或者销毁 Session ）
        request.getSession().invalidate();
        // 重定向到首页（或登录页面）。
        response.sendRedirect(request.getContextPath());
    }

    /**
     * 登录
     *
     * @param user
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/login")
    public void login(@ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        User queryUser = userService.findUserByNameAndPassword(user);
        if (queryUser != null) {
            //登录成功 跳转到 login_success.jsp
            request.getSession().setAttribute("user", queryUser);
            System.out.println(queryUser);
            request.getSession().setAttribute("username", user.getUsername());
            // 如果status是1跳转到管理员界面
            if (queryUser.getStatus() == 1) {
                request.getRequestDispatcher("/pages/manager/manager.jsp").forward(request, response);
            }
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
        } else {
            // 失败 返回用户名 并提示信息
            request.getSession().setAttribute("msg", "用户名或密码错误");
            request.getSession().setAttribute("username", user.getUsername());
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);

        }
    }


    /**
     * 注册
     *
     * @param user
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/regist")
    public void regist(@ModelAttribute("user") User user, @RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Boolean isExist = userService.findByName(user.getUsername());
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().invalidate();
        //验证码正确
        if (token != null && token.equalsIgnoreCase(code)) {
            // 用户存在
            if (isExist) {
                // 把回显信息，保存到Request域中
                request.setAttribute("msg", "用户名已存在！！");
                request.setAttribute("username", user.getUsername());
                request.setAttribute("email", user.getEmail());
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);

            } else {
                // 用户名不存在 允许注册 创建购物车

                userService.saveUser(user);
                User queryUser = userService.findUserByNameAndPassword(user);
                cartService.addCart(queryUser.getId());

                request.getSession().setAttribute("user", queryUser);
                request.getSession().setAttribute("username", user.getUsername());
                //跳到注册成功页面 regist_success.jsp
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
            }
        }else {
            // 把回显信息，保存到Request域中
            request.setAttribute("msg", "验证码错误！！");
            request.setAttribute("username", user.getUsername());
            request.setAttribute("email", user.getEmail());

            System.out.println("验证码[" + code + "]错误");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }

    }

}
