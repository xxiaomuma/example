package pers.xiaomuma.auth.example.core.service.handle;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import pers.xiaomuma.auth.example.core.cache.ClientDetailCache;
import pers.xiaomuma.auth.example.core.crud.modules.auth.domain.OauthClientDetails;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CachedClientDetailsService implements ClientDetailsService {

    private final ClientDetailCache clientDetailCache;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        OauthClientDetails clientDetails = clientDetailCache.getClient(clientId);
        if (Objects.isNull(clientDetails)) {
            throw new NoSuchClientException("invalid client");
        }
        BaseClientDetails details = new BaseClientDetails(clientDetails.getClientId(),
                clientDetails.getResourceIds(),
                clientDetails.getScope(),
                clientDetails.getAuthorizedGrantTypes(),
                clientDetails.getAuthorities(),
                clientDetails.getWebServerRedirectUri());
        details.setClientSecret(clientDetails.getClientSecret());
        return details;
    }
}
