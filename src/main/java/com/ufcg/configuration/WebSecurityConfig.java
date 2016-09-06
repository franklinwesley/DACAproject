package com.ufcg.configuration;

import com.ufcg.Utils.UserType;
import com.ufcg.repositories.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
		 .httpBasic().and()
		 .authorizeRequests().anyRequest().authenticated().and().csrf().disable();
		 //.antMatchers(HttpMethod.GET, "/securitysave").hasRole("ADMIN").and()
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
                Logger log = Logger.getLogger(WebSecurityConfig.class);
                com.ufcg.models.User account = userRepository.findUserByEmail(username);
                log.info("Find user:" + account.getId());
                if (account != null) {
                    if (account.getType() == UserType.ADMINISTRATOR){
                        log.info("Find user21:" + account.getId());
                        return new User(account.getEmail(), account.getPassword(), true, true, true, true,
                                AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
                    } else {
                        log.info("Find user31234:" + account.getId());
                        return new User(account.getEmail(), account.getPassword(), true, true, true, true,
                                AuthorityUtils.createAuthorityList("ROLE_USER"));
                    }
                } else {
                    throw new UsernameNotFoundException("could not find the user '" + username + "'");
                }
            }

        };
    }

}
