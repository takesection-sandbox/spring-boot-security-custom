package jp.pigumer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class Application {
    
    @RequestMapping(value = "/")
    @ResponseBody
    public AjaxResponse ajax() {
        return new AjaxResponse("test");
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
