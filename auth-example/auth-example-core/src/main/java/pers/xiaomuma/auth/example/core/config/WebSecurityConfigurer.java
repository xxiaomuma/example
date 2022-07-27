package pers.xiaomuma.auth.example.core.config;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import pers.xiaomuma.auth.example.core.service.handle.PasswordUserConnectionRepository;
import pers.xiaomuma.auth.example.core.service.handle.SmsUserConnectionRepository;
import pers.xiaomuma.auth.framework.auth.server.authentication.sms.SmsAuthenticationProvider;
import pers.xiaomuma.auth.framework.auth.server.authentication.username.PasswordAuthenticationProvider;
import pers.xiaomuma.auth.framework.auth.server.password.IPbkdf2PasswordEncoder;
import java.util.HashMap;
import java.util.Map;


@Primary
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final SmsUserConnectionRepository smsUserConnectionRepository;
    private final PasswordUserConnectionRepository passwordUserConnectionRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭默认的csrf认证
        http.csrf().disable();
        http.httpBasic().disable();
        //设置登录,注销，表单登录不用拦截，其他请求要拦截
        http.authorizeRequests()
                .antMatchers(
                        "/",
                        "/removeAccessToken/**",
                        "/authority/**",
                        "/auth/**",
                        "/oauth/**",
                        "/oauth2/**",
                        "/token/**", //
                        "/actuator/**" //actuator（监控） 开头不拦截
                )  //social（第三方登录） 开头不拦截
                .permitAll()
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider( smsAuthenticationProvider() )
                .authenticationProvider( passwordAuthenticationProvider() );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        String encodingId = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(encodingId, new BCryptPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("i-pbkdf2", new IPbkdf2PasswordEncoder());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        return new DelegatingPasswordEncoder(encodingId, encoders);
    }

    @Bean
    public SmsAuthenticationProvider smsAuthenticationProvider() {
        return new SmsAuthenticationProvider(smsUserConnectionRepository);
    }

    @Bean
    public PasswordAuthenticationProvider passwordAuthenticationProvider() {
        return new PasswordAuthenticationProvider(passwordEncoder(), passwordUserConnectionRepository);
    }

    @Bean
    public ProviderManager providerManager() {
        return new ProviderManager(Lists.newArrayList(smsAuthenticationProvider(), passwordAuthenticationProvider()) );
    }
}
