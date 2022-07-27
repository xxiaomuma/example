package pers.xiaomuma.example.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;
import pers.xiaomuma.auth.framework.resource.server.authorize.AuthorizeConfigProvider;

@Component
@Order(Integer.MAX_VALUE-1)
public class CustomAuthorizeConfigProvider implements AuthorizeConfigProvider {

	private static final String INTERNAL_SERVER_SCOPE_SPEL = "#oauth2.hasScope('external')";

	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config.antMatchers("/passport/**", "/test/**","/v2/api-docs", "/swagger-ui.html",
				"/swagger-resources/configuration/ui",
				"/swagger-resources",
				"/webjars/**",
				"/swagger-resources/configuration/security").permitAll();
		config.anyRequest().access(INTERNAL_SERVER_SCOPE_SPEL);
		return false;
	}
}
