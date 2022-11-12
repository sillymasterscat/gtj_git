package cn.itcast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fail
 * @create 2021-03-20 19:42
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/string")
    public String testString() {
        System.out.println("string");
        return "success";
    }
}
