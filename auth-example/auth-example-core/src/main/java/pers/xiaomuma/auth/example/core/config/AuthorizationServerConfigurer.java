package pers.xiaomuma.auth.example.core.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import pers.xiaomuma.auth.example.core.constant.ApplicationConstant;
import pers.xiaomuma.auth.example.core.service.handle.CachedClientDetailsService;
import pers.xiaomuma.auth.framework.auth.server.exception.CustomizedWebResponseExceptionTranslator;
import pers.xiaomuma.auth.framework.auth.server.grant.TokenGranterFactory;
import pers.xiaomuma.auth.framework.auth.server.store.CustomizedRedisTokenStore;
import pers.xiaomuma.auth.framework.auth.server.token.CustomizedTokenEnhancer;
import pers.xiaomuma.auth.framework.constant.TokenGrantType;
import java.util.ArrayList;
import java.util.List;


@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    private final RedisConnectionFactory connectionFactory;
    private final AuthenticationManager authenticationManagerBean;
    private final CachedClientDetailsService customClientDetailsService;
    private CustomizedWebResponseExceptionTranslator translator = new CustomizedWebResponseExceptionTranslator();

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(customClientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancer())
                .reuseRefreshTokens(false)
                .exceptionTranslator(translator);

        endpoints.authenticationManager(authenticationManagerBean);
        this.customTokenGranter(endpoints);
    }

    @Bean
    public TokenStore tokenStore() {
        CustomizedRedisTokenStore tokenStore = new CustomizedRedisTokenStore(connectionFactory);
        tokenStore.setPrefix(ApplicationConstant.APPLICATION_TOKEN_STORE);
        return tokenStore;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomizedTokenEnhancer(customClientDetailsService);
    }

    private void customTokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        AuthorizationServerTokenServices tokenService = endpoints.getTokenServices();
        TokenGranterFactory tokenGranterFactory = new TokenGranterFactory(authenticationManagerBean, tokenService, customClientDetailsService);
        List<TokenGranter> list = new ArrayList<>();
        list.add(endpoints.getTokenGranter());
        list.add(tokenGranterFactory.buildTokenGranter(TokenGrantType.PASSWORD));
        list.add(tokenGranterFactory.buildTokenGranter(TokenGrantType.SMS));
        list.add(tokenGranterFactory.buildTokenGranter(TokenGrantType.WX));
        endpoints.tokenGranter(new CompositeTokenGranter(list));
    }
}
