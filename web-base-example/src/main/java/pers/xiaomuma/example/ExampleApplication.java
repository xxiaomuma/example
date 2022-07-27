package pers.xiaomuma.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import pers.xiaomuma.auth.framework.resource.server.annotation.EnableExternalResourceServer;
import pers.xiaomuma.framework.base.support.cache.EnableRedisCache;
import pers.xiaomuma.framework.rpc.ServiceClientConfiguration;
import pers.xiaomuma.framework.web.WebApplicationConfiguration;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class, RabbitAutoConfiguration.class
})
@EnableRedisCache
@Import({ServiceClientConfiguration.class, WebApplicationConfiguration.class})
@EnableExternalResourceServer
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}
