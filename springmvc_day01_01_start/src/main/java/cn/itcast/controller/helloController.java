package cn.itcast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author fail
 * @create 2021-03-18 15:21
 */
@Controller
@RequestMapping("/user")
public class helloController {

    @RequestMapping(value = "/method", method = {RequestMethod.POST})
    public String method() {
        System.out.println("hellow springMvc");
        return "success";
    }

    @RequestMapping(value = "/params", params = {"username"})
    public String params() {
        System.out.println("hellow springMvc");
        return "success";
    }

    @RequestMapping(value = "/headers", params = {"username"}, headers = {"accept"})
    public String headers() {
        System.out.println("hellow springMvc");
        return "success";
    }
}
