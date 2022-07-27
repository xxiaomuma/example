package pers.xiaomuma.auth.example.core.service.handle;


import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.stereotype.Service;
import pers.xiaomuma.auth.example.core.model.PasswordUserConnection;
import pers.xiaomuma.auth.example.core.service.biz.AccountExternalBizService;
import pers.xiaomuma.auth.framework.auth.server.authentication.UserConnection;
import pers.xiaomuma.auth.framework.auth.server.authentication.UserConnectionRepository;
import pers.xiaomuma.auth.framework.auth.server.token.TokenRequestContextHolder;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class PasswordUserConnectionRepository implements UserConnectionRepository {

    private final Logger logger = LoggerFactory.getLogger(PasswordUserConnectionRepository.class);
    private final AccountExternalBizService accountExternalBizService;

    @Override
    public UserConnection getConnection(String username) {
        logger.info("用户账号密码登录:[{}]", username);
        TokenRequest tokenRequest = TokenRequestContextHolder.getContext().getTokenRequest();
        if (Objects.isNull(tokenRequest)) {
            throw new InvalidRequestException("认证异常");
        }
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        PasswordUserConnection userConnection = accountExternalBizService.loadUserByUsername(username);
        //根据scope划分业务认证
        //Set<String> scopes = tokenRequest.getScope();
        stopWatch.stop();
        logger.info("加载用户信息成功, scope:external, username: {}, 是否存在: {},总耗时:{}ms", username, Objects.nonNull(userConnection), stopWatch.getTime());
        return userConnection;
    }
}
