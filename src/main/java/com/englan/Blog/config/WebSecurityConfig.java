package com.englan.Blog.config;

import com.englan.Blog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //передаем на вход объект
        http
                //Вкулючения авторизации
                .authorizeRequests()
                //Указываем что для данной страницы дан полный доступ
                .antMatchers("/main", "/registration").permitAll()
                //Для всех остальных запросов мы требуем авторизацию
                .anyRequest().authenticated()
                .and()
                //включаем форму логин
                .formLogin()
                //Указываем что форма лагин указываеться на таком мепинге
                .loginPage("/login")
                //Разрешаем этим пользоваться всем
                .permitAll()
                .and()
                //Включаем логаун и разрешаем пользоваться всем
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select firstName, lastName, email, password, city, active from usr where email=?")
                .authoritiesByUsernameQuery("select e.email, r.roles from usr e inner join user_role r on e.id = r.user_id where e.email=?");
    }
}