package test.dev.web.common.config.security;

import lombok.extern.log4j.Log4j2;
import test.dev.web.common.handler.CustomAuthenticationFailureHandler;
import test.dev.web.common.handler.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author skkim
 * @since 2021-04-23
 */

@Configuration
@Log4j2
public class CustomSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    // 정적 자원에 대해서는 Security 설정을 적용하지 않음
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/login").permitAll() //권한 없이 접근 가능
//                .antMatchers("/").hasRole("USER") // USER 권한을 가진 사용자만 접근 가능
                .anyRequest().authenticated() //그 외의 요청은 인증된 사용자만 접근
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/") //로그인 성공하면
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .and()
                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    // BCryptPasswordEncoder는 Spring Security에서 제공하는 비밀번호 암호화 객체입니다.
    // Service에서 비밀번호를 암호화할 수 있도록 Bean으로 등록합니다.
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //CustomAuthenticationFilter를 빈으로 등록하는 과정에서 loginId 파라미터와 password 파라미터를 설정할 수 있다.
    // 이러한 과정을 거치면 UsernamePasswordToken이 발급되게 된다.
    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/login-process"); //로그인 처리 로직
        customAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandleru()); //로그인 성공 처리로직
        customAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler()); //로그인 실패 처리로직
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }

    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandleru() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider());
    }
}
