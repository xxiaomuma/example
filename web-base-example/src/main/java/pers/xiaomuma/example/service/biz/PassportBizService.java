package pers.xiaomuma.example.service.biz;


import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pers.xiaomuma.auth.framework.auth.client.entity.AuthCredential;
import pers.xiaomuma.auth.framework.auth.client.param.PasswordAuthenticationParam;
import pers.xiaomuma.auth.framework.auth.client.param.SmsAuthenticationParam;
import pers.xiaomuma.auth.framework.auth.client.provider.ClientAuthenticationService;
import pers.xiaomuma.example.web.vo.OAuth2PassportVO;
import pers.xiaomuma.framework.exception.InternalBizException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PassportBizService {

    private final Logger logger = LoggerFactory.getLogger(PassportBizService.class);
    private final ClientAuthenticationService clientAuthenticationService;

    public OAuth2PassportVO passwordLogin(String username, String password) {
        logger.info("用户[{}]正尝试登陆....", username);
        PasswordAuthenticationParam param = PasswordAuthenticationParam.builder()
                .username(username)
                .password(password).build();
        AuthCredential authCredential = clientAuthenticationService.authentication(param);
        if (!authCredential.isAuthSuccess()) {
            throw new InternalBizException(authCredential.getErrorMsg());
        }
        logger.info("用户名密码登陆成功, username:[{}]", param.getUsername());
        return this.convertCredentials2Passport(authCredential.getCredential());
    }

    public OAuth2PassportVO smsLogin(String mobile, String code) {
        logger.info("用户[{}]正尝试登陆....", mobile);
        boolean validateCodeSuccess = true; //验证手机号
        if (!validateCodeSuccess) {
            throw new InternalBizException("验证码不匹配");
        }
        SmsAuthenticationParam param = SmsAuthenticationParam.builder()
                .mobile(mobile)
                .code(code)
                .build();
        AuthCredential authCredential = clientAuthenticationService.authentication(param);
        if (!authCredential.isAuthSuccess()) {
            throw new InternalBizException(authCredential.getErrorMsg());
        }
        logger.info("手机号验证码登陆成功, mobile:[{}]", param.getMobile());
        return this.convertCredentials2Passport(authCredential.getCredential());
    }

    private OAuth2PassportVO convertCredentials2Passport(Map<String, ?> credentials) {
        if (MapUtils.isEmpty(credentials)) {
            return null;
        }
        OAuth2PassportVO passportVO = new OAuth2PassportVO();
        passportVO.setAccessToken((String) credentials.get("access_token"));
        passportVO.setTokenType((String) credentials.get("token_type"));
        Integer expiresIn = (Integer) credentials.get("expires_in");
        if (expiresIn != null) {
            passportVO.setExpiresIn(expiresIn);
        }
        return passportVO;
    }

}
