package jp.pigumer.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        PreAuthenticatedAuthenticationToken auth = (PreAuthenticatedAuthenticationToken) authentication;
        String principal = (String) auth.getPrincipal();
        
        log.info("authenticate: " + Objects.toString(auth, ""));

        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        authorities.add(authority);

        ExampleAuthentication result = new ExampleAuthentication(principal, authorities);
        result.setDetails(userDetailsService.loadUserByUsername(principal));

        log.info("authenticate: " + Objects.toString(result, ""));
        
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
    
}
