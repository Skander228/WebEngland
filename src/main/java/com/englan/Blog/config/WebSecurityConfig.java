package com.englan.Blog.config;

import com.englan.Blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //передаем на вход объект
        http
                    //Вкулючения авторизации
                    .authorizeRequests()
                    //Указываем что для данной страницы дан полный доступ
                    .antMatchers("/", "/registration").permitAll()
                    //Для всех остальных запросов мы требуем авторизацию
                    .anyRequest().authenticated()
                .and()
                    //включаем форму логин
                    .formLogin()
                    //Указываем что форма лагин указываеться на таком мепинге
                    .loginPage("/login")
                    .usernameParameter("usern")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/user/settings")
                //.loginProcessingUrl("/login")
                    .failureUrl("/login")
                    //Разрешаем этим пользоваться всем
                    .permitAll()
                .and()
                    //Включаем логаун и разрешаем пользоваться всем
                    .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}