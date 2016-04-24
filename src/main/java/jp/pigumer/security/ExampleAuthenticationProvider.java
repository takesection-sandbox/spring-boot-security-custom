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
package jp.pigumer.security;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class ExampleAuthenticationProvider implements AuthenticationProvider, InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(ExampleAuthenticationProvider.class);

    private ExampleUserDetailsService userDetailsService;
    
    @Autowired
    public void setUserDetailsService(ExampleUserDetailsService userDetailsService) {
        LOG.debug("serUserDetailsService: " + Objects.toString(userDetailsService, ""));
        this.userDetailsService = userDetailsService;
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PreAuthenticatedAuthenticationToken auth = (PreAuthenticatedAuthenticationToken) authentication;
        String username = (String) auth.getPrincipal();
        
        LOG.debug("authenticate: " + Objects.toString(auth, ""));

        User user = userDetailsService.loadUser(username);

        ExampleAuthentication result = new ExampleAuthentication(user, user.getAuthorities());
        result.setDetails(auth.getDetails());

        LOG.debug("authenticate: " + Objects.toString(result, ""));
        
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.debug("afterPropertiesSet");
    }
    
}
