package jp.pigumer.app;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class Application {
    
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    @RequestMapping("/secure/a")
    @ResponseBody
    public AjaxResponse ajax(@AuthenticationPrincipal UserDetails user) {
        log.info(Objects.toString(user, "NULL"));
        return new AjaxResponse("test");
    }
    
    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "index";
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
