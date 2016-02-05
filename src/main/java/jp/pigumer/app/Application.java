package jp.pigumer.app;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class Application {
    
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    @RequestMapping(value = "/")
    @ResponseBody
    public AjaxResponse ajax(@CurrentUser User user) {
        log.info(Objects.toString(user, "null"));
        return new AjaxResponse("test");
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
