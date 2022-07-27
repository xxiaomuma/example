package pers.xiaomuma.auth.example.core.crud.modules.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xiaomuma.auth.example.core.crud.modules.client.domain.AccountExternal;

/**
 * <p>
 * 账户信息 服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-02-25
 */
public interface IAccountExternalService extends IService<AccountExternal> {

    AccountExternal findAccountByMobile(String mobile);

    AccountExternal findAccountByUsername(String username);
}
