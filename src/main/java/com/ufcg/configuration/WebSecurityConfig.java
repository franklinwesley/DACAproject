package com.ufcg.configuration;

import com.ufcg.Utils.UserType;
import com.ufcg.repositories.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    final String ROLE = "ROLE_";
    final String ROLE_USER = ROLE + UserType.NORMAL.toString();
    final String ROLE_ADMIN = ROLE + UserType.ADMINISTRATOR.toString();

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().frameOptions().sameOrigin();

        http
                .httpBasic().and()
                .authorizeRequests()
                    // USER
                    .antMatchers(HttpMethod.GET, "/user").hasAnyAuthority(ROLE_ADMIN)
                    .antMatchers(HttpMethod.GET, "/user/*").hasAnyAuthority(ROLE_ADMIN)
                    .antMatchers(HttpMethod.DELETE, "/user/*").hasAnyAuthority(ROLE_ADMIN)
                    .antMatchers(HttpMethod.POST, "/user").hasAnyAuthority(ROLE_ADMIN, ROLE_USER)
                    .antMatchers(HttpMethod.PUT, "user/*").hasAnyAuthority(ROLE_ADMIN, ROLE_USER)
                    .antMatchers(HttpMethod.PATCH, "user/*").hasAnyAuthority(ROLE_ADMIN)
                    //PROBLEM
                    .antMatchers(HttpMethod.DELETE, "/problem/*").hasAnyAuthority(ROLE_ADMIN, ROLE_USER)
                    .antMatchers(HttpMethod.POST, "/problem").hasAnyAuthority(ROLE_ADMIN, ROLE_USER)
                    .antMatchers(HttpMethod.PUT, "/problem/*").hasAnyAuthority(ROLE_ADMIN, ROLE_USER)
                    //TEST
                    .antMatchers(HttpMethod.DELETE, "/test/*").hasAnyAuthority(ROLE_ADMIN, ROLE_USER)
                    .antMatchers(HttpMethod.POST, "/test").hasAnyAuthority(ROLE_ADMIN, ROLE_USER)
                    .antMatchers(HttpMethod.PUT, "/test/*").hasAnyAuthority(ROLE_ADMIN, ROLE_USER)
                .anyRequest().authenticated().and().csrf().disable();
        http
                .csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Autowired
            UserRepository userRepository;

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                com.ufcg.models.User user = userRepository.findUserByEmail(username);
                if (user != null) {
                    return new User(user.getEmail(), user.getPassword(), true, true, true, true,
                            AuthorityUtils.createAuthorityList("ROLE_" + user.getType().toString()));
                } else {
                    throw new UsernameNotFoundException("could not find the user '" + username + "'");
                }
            }

        };
    }

}
