package pers.xiaomuma.auth.example.core.cache;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Joiner;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import pers.xiaomuma.auth.example.core.constant.ApplicationConstant;
import pers.xiaomuma.auth.example.core.crud.modules.auth.domain.OauthClientDetails;
import pers.xiaomuma.auth.example.core.service.biz.OauthClientDetailsBizService;
import pers.xiaomuma.framework.serialize.JsonUtils;
import java.util.concurrent.TimeUnit;


@Component
@RequiredArgsConstructor
public class ClientDetailCache {

    private final RedisTemplate<String, String> redisTemplate;
    private final OauthClientDetailsBizService oauthClientDetailsBizService;
    private final static String CLIENT_DETAILS_CACHE_KEY_PREFIX = ApplicationConstant.APPLICATION_NAME + ":clientDetails:clientId";

    //60s缓存
    public OauthClientDetails getClient(String clientId) {
        String cacheKey = this.buildCacheKey(clientId);
        String cacheObject = redisTemplate.opsForValue().get(cacheKey);
        if (StrUtil.isEmpty(cacheObject) || cacheObject.equals("null")) {
            OauthClientDetails clientDetails = oauthClientDetailsBizService.loadClientByClientId(clientId);
            redisTemplate.opsForValue().set(cacheKey, JsonUtils.object2Json(clientDetails), 60, TimeUnit.SECONDS);
            return clientDetails;
        }
        return JsonUtils.json2Object(cacheObject, OauthClientDetails.class);
    }

    private String buildCacheKey(String clientId) {
        return Joiner.on(":").join(CLIENT_DETAILS_CACHE_KEY_PREFIX, clientId);
    }
}
