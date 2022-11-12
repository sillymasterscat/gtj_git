package cn.itcast.controller;

import cn.itcast.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;
import java.util.Map;

/**
 * @author fail
 * @create 2021-03-18 18:52
 */

@Controller
@RequestMapping("/anno")
@SessionAttributes(value= {"username","password","age"},types=
        {String.class,Integer.class}) // 把数据存入到session域对象中
public class AnnoController {

    @RequestMapping("/useRequestParam")
    public String useRequestParam(@RequestParam("name") String username) {
        System.out.println(username);
        return "success";
    }

    @RequestMapping("/useRequestBody")
    public String useRequestBody(@RequestBody String body) {
        System.out.println(body);
        return "success";
    }

    @RequestMapping("/usePathVariable/{id}")
    public String usePathVariable(@PathVariable(name = "id") String sid) {
        System.out.println(sid);
        return "success";
    }


    @RequestMapping("/RequestHeader")
    public String useRequestHeader(@RequestHeader(value = "Accept") String header) {
        System.out.println(header);
        return "success";
    }


    @RequestMapping("/CookieValue")
    public String useCookieValue(@CookieValue(value = "JSESSIONID") String header) {
        System.out.println(header);
        return "success";
    }

    /**
     * ModelAttribute
     * @return
     */
//    @ModelAttribute
//    public User useModelAttribute(String username, int age) {
//
//        User user = new User();
//        user.setUsername(username);
//        user.setAge(age);
//        user.setDate(new Date());
//
//
//        return user;
//    }

    @ModelAttribute
    public void useModelAttribute(String username, int age, Map<String, User> map) {

        User user = new User();
        user.setUsername(username);
        user.setAge(age);
        user.setDate(new Date());
        map.put("a", user);
    }



    @RequestMapping("/ModelAttribute")
    public String testModelAttribute(@ModelAttribute("a") User user) {

        System.out.println(user);

        return "success";
    }

    /**
     * 向session中存入值
     * @return
     */
    @RequestMapping(path="/save")
    public String save(Model model) {
        System.out.println("向session域中保存数据");
        model.addAttribute("username", "root");
        model.addAttribute("password", "123");
        model.addAttribute("age", 20);
        return "success";
    }
    /**
     * 从session中获取值
     * @return
     */
    @RequestMapping(path="/find")
    public String find(ModelMap modelMap) {
        String username = (String) modelMap.get("username");
        String password = (String) modelMap.get("password");
        Integer age = (Integer) modelMap.get("age");
        System.out.println(username + " : "+password +" : "+age);
        return "success";
    }
    /**
     * 清除值
     * @return
     */
    @RequestMapping(path="/delete")
    public String delete(SessionStatus status) {
        status.setComplete();
        return "success";
    }


}
