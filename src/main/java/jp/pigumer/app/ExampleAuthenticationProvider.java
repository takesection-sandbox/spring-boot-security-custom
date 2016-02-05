package jp.pigumer.app;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class ExampleAuthenticationProvider implements AuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(ExampleAuthenticationProvider.class);

    private UserDetailsService userDetailsService;
    
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PreAuthenticatedAuthenticationToken result = (PreAuthenticatedAuthenticationToken) authentication;

        String principal = (String) result.getPrincipal();
        log.info(Objects.toString(principal, ""));

        result.setDetails(userDetailsService.loadUserByUsername(principal));
        
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
    
}
