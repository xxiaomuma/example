package pers.xiaomuma.auth.example.core.crud.modules.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.xiaomuma.auth.example.core.crud.modules.client.dao.AccountExternalDAO;
import pers.xiaomuma.auth.example.core.crud.modules.client.domain.AccountExternal;
import pers.xiaomuma.auth.example.core.crud.modules.client.service.IAccountExternalService;

/**
 * <p>
 * 账户信息 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-02-25
 */
@Service
public class AccountExternalServiceImpl extends ServiceImpl<AccountExternalDAO, AccountExternal> implements IAccountExternalService {

    @Override
    public AccountExternal findAccountByMobile(String mobile) {
        return this.baseMapper.queryAccountByMobile(mobile);
    }

    @Override
    public AccountExternal findAccountByUsername(String username) {
        return this.baseMapper.queryAccountByUsername(username);
    }
}
