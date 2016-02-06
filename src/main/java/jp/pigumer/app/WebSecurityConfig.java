package jp.pigumer.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private ExampleUserDetailsServiceImpl userDetailsService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.regexMatcher("^/secure.*").addFilterAfter(exampleFilter(), BasicAuthenticationFilter.class);
        http.csrf().disable();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth
            .authenticationProvider(exampleAuthenticationProvider());
    }   
    
    @Bean
    public ExampleAuthenticationProvider exampleAuthenticationProvider() {
        ExampleAuthenticationProvider provider = new ExampleAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
    
    @Bean
    public ExampleFilter exampleFilter() throws Exception {
        ExampleFilter exampleFilter = new ExampleFilter();
        exampleFilter.setAuthenticationManager(authenticationManagerBean());
        return exampleFilter;
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
