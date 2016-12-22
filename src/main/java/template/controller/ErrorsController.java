package template.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorsController {
    @RequestMapping(value = "/errors", method = RequestMethod.GET)
    String getErrorPage(HttpServletRequest httpRequest, Model model) {
        Integer errorCode = getErrorCode(httpRequest);
        
        if (errorCode == null) {
            return "redirect:/";
        }
        
        model.addAttribute("errorCode", errorCode);
        
        return "/errors";
    }
    
    private Integer getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    }
}
