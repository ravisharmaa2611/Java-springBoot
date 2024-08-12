package com.xyalgo.bookmyshow.config;

import com.xyalgo.bookmyshow.admin.AdminDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AdminDetail adminDetail;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests((requests) -> requests.antMatchers("/css/**", "/js/**", "/scss/**"
                        , "/images/**", "/movie_photos/**","/user_photos/**", "/fonts/**", "/img/**", "/vendor/**", "/META-INF/**", "/user/usersignup", "/user/login", "/",

                        "/movie-select-show/movie", "/movie-payment", "/movie", "/booking", "/bookings", "/user/**"
                        , "/pic-a-movie", "/movie-payment", "/movie-booking", "/profile","/uploadPhoto","/rateMovie")
                .permitAll()
        );
        http.authorizeRequests((requests) -> requests
                        .antMatchers("/admin")
                        .hasAuthority("ADMIN").antMatchers("/login*").permitAll()
                        .anyRequest()
                        .authenticated()
                        .and()
                )
                .formLogin().loginPage("/login");

    }


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminDetail).passwordEncoder(passwordEncoder());
//        auth.inMemoryAuthentication().withUser("ravi").password(this.passwordEncoder().encode("ravisharmaa")).roles("ADMIN");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
