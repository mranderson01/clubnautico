package es.rodrigo.eviden.security.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController {
    @RequestMapping("/error/access-denied")
    public String accessDeniedError() {
        return "error/access-denied";
    }
}
