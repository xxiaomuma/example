package pers.xiaomuma.example.crud.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.xiaomuma.example.crud.domain.Account;
import pers.xiaomuma.example.crud.param.QueryAccountParam;

/**
 * <p>
 * 账户信息 服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-02-25
 */
public interface IAccountService extends IService<Account> {

    IPage<Account> findNormalAccountList(QueryAccountParam param);
}
