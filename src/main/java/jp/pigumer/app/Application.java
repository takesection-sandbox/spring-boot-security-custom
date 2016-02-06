/*
 * Copyright 2016 Pigumer Group Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.pigumer.app;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class Application {
    
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    
    @RequestMapping("/secure/a")
    @ResponseBody
    public AjaxResponse ajax(@AuthenticationPrincipal User user) {
        LOG.debug("user: " + Objects.toString(user, "NULL"));
        return new AjaxResponse(user.getUsername());
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
