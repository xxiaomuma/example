package pers.xiaomuma.auth.example.core.service.biz;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pers.xiaomuma.auth.example.core.constant.DataSourceNames;
import pers.xiaomuma.auth.example.core.crud.modules.auth.domain.OauthClientDetails;
import pers.xiaomuma.auth.example.core.crud.modules.auth.service.IOauthClientDetailsService;
import pers.xiaomuma.framework.base.support.database.annotation.DataSource;

@Service
@RequiredArgsConstructor
public class OauthClientDetailsBizService {

    private final IOauthClientDetailsService oauthClientDetailsService;

    @DataSource(DataSourceNames.EXAMPLE_AUTH_READ)
    public OauthClientDetails loadClientByClientId(String clientId) {
        return oauthClientDetailsService.loadClientByClientId(clientId);
    }
}
