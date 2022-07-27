package pers.xiaomuma.auth.example.core;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import pers.xiaomuma.auth.framework.auth.server.annotation.EnableCustomizedAuthorizationServer;
import pers.xiaomuma.framework.base.support.cache.EnableRedisCache;
import pers.xiaomuma.framework.rpc.ServiceClientConfiguration;

@SpringBootApplication(exclude = {
        RabbitAutoConfiguration.class,
        DataSourceAutoConfiguration.class
})
@EnableRedisCache
@Import({ServiceClientConfiguration.class})
@EnableCustomizedAuthorizationServer
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }


}
