package pers.xiaomuma.auth.example.core.crud.modules.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import pers.xiaomuma.auth.example.core.crud.modules.auth.dao.OauthClientDetailsDAO;
import pers.xiaomuma.auth.example.core.crud.modules.auth.domain.OauthClientDetails;
import pers.xiaomuma.auth.example.core.crud.modules.auth.service.IOauthClientDetailsService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-02-21
 */
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsDAO, OauthClientDetails> implements IOauthClientDetailsService {

    @Override
    public OauthClientDetails loadClientByClientId(String clientId) {
        return this.baseMapper.queryClientByClientId(clientId);
    }
}
