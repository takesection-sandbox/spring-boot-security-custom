package jp.pigumer.app;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class ExampleAuthentication extends AbstractAuthenticationToken {

    String principal;
    
    public ExampleAuthentication(String name) {
        super(null);
        principal = name;
        
    }

    public ExampleAuthentication(String name, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        principal = name;
        setAuthenticated(true);
    }
    
    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
    
}
