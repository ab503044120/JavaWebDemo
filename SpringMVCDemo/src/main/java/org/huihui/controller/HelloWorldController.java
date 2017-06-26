package org.huihui.controller;

import org.huihui.model.User;
import org.huihui.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by huihui on 2017/6/25.
 */
@Controller
public class HelloWorldController {
    @Resource
    private UserService userService;

    @RequestMapping(value = {"/hello"}, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String hello() {
        User userById = userService.getUserById("1");
        return "helloWorld" + "  " + userById.name;

    }
    //自动匹配参数
    @RequestMapping(value = {"/login"}
            , produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String login(String id) {
        User userById = userService.getUserById(id);
        return "helloWorld" + " 你是 " + userById.name;

    }
}
