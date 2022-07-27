package pers.xiaomuma.example.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import pers.xiaomuma.auth.framework.auth.client.provider.DefaultClientAuthenticationService;
import pers.xiaomuma.auth.framework.auth.client.provider.DefaultOAuth2AuthenticationClient;
import pers.xiaomuma.auth.framework.auth.client.provider.OAuth2AuthenticationClient;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class OAuth2ClientConfiguration {

    @Value("${security.oauth2.client.tokenEndpointUrl}")
    private String tokenEndpointUri;
    private final RestTemplate restTemplate;
    private final OAuth2ClientProperties oAuth2ClientProperties;

    @Bean
    public DefaultClientAuthenticationService defaultAuthenticationClientService() {
        return new DefaultClientAuthenticationService(oAuth2AuthenticationClient());
    }

    private OAuth2AuthenticationClient oAuth2AuthenticationClient() {
        DefaultOAuth2AuthenticationClient client = new DefaultOAuth2AuthenticationClient();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400) {
                    super.handleError(response);
                }
            }
        });
        client.setRestTemplate(restTemplate);
        client.setTokenEndpointUrl(tokenEndpointUri);
        client.setClientId(oAuth2ClientProperties.getClientId());
        client.setClientSecret(oAuth2ClientProperties.getClientSecret());
        return client;
    }
}
