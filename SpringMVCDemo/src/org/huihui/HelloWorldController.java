package org.huihui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by huihui on 2017/6/25.
 */
@Controller
public class HelloWorldController {
    @RequestMapping(value = {"/hello"})
    @ResponseBody
    public String hello(Model model) {
        return "helloWorld";

    }
}
