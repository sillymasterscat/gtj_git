package cn.itcast.conrtoller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fail
 * @create 2021-03-20 20:17
 */

@RequestMapping("/user")
@Controller
public class UserController {

    @RequestMapping("/string")
    public String testString() {
        System.out.println("testString");
        return "success";
    }

    @RequestMapping("/httpServletRequest")
    public void testHttpServletRequest(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        System.out.println("HttpServletRequest");
        response.sendRedirect(request.getContextPath() + "/t.jsp");
    }
}
