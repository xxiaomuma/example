package pers.xiaomuma.auth.example.core.crud.modules.client.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.xiaomuma.auth.example.core.crud.modules.client.domain.AccountExternal;

/**
 * <p>
 * 账户信息 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-02-25
 */
public interface AccountExternalDAO extends BaseMapper<AccountExternal> {

    AccountExternal queryAccountByUsername(String username);

    AccountExternal queryAccountByMobile(String mobile);
}
