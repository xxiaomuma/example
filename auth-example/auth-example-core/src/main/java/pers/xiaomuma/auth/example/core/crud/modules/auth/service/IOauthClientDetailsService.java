package pers.xiaomuma.auth.example.core.crud.modules.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xiaomuma.auth.example.core.crud.modules.auth.domain.OauthClientDetails;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-02-21
 */
public interface IOauthClientDetailsService extends IService<OauthClientDetails> {

    OauthClientDetails loadClientByClientId(String clientId);
}
