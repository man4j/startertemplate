package template.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class MainController2 {
    @RequestMapping(method = RequestMethod.GET)
    String main(Model model) {
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName() + "_test");
        
        return "/main";
    }
}