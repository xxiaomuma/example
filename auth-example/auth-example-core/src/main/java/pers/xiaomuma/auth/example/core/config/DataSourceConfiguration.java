package pers.xiaomuma.auth.example.core.config;


import com.google.common.collect.Lists;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.xiaomuma.auth.example.core.constant.DataSourceNames;
import pers.xiaomuma.framework.base.support.database.MultipleDataSource;


@Configuration
@MapperScan(basePackages = "pers.xiaomuma.auth.example.core.crud.modules.*.dao")
public class DataSourceConfiguration {

    @Bean("multipleDataSource")
    public MultipleDataSource multipleDataSource() {
        return new MultipleDataSource(Lists.newArrayList(
                DataSourceNames.EXAMPLE_AUTH_READ,
                DataSourceNames.EXAMPLE_CLIENT_READ
        ));
    }

}
