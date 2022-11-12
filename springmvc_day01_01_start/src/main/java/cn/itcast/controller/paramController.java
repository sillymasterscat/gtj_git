package cn.itcast.controller;

import cn.itcast.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fail
 * @create 2021-03-18 16:33
 */
@Controller
@RequestMapping("/param")
public class paramController {

    @RequestMapping("/basic")
    public String testParam(String username, int age) {
        System.out.println(username);
        System.out.println(age);
        return "success";
    }

    @RequestMapping("/bean")
    public String bean(User u){
        System.out.println(u);
        return "success";
    }

    @RequestMapping("/bean1")
    public String bean1(User u){
        System.out.println(u);
        return "success";
    }

}
