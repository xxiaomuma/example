package pers.xiaomuma.example.crud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.xiaomuma.example.crud.dao.AccountDAO;
import pers.xiaomuma.example.crud.domain.Account;
import pers.xiaomuma.example.crud.param.QueryAccountParam;
import pers.xiaomuma.example.crud.service.IAccountService;

/**
 * <p>
 * 账户信息 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-02-25
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountDAO, Account> implements IAccountService {


    @Override
    public IPage<Account> findNormalAccountList(QueryAccountParam param) {
        IPage<Account> page = new Page<>(param.getPageNum(), param.getPageSize());
        LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Account::getStatus, param.getStatus());
        queryWrapper.orderByDesc(Account::getCreateTime);
        return this.baseMapper.selectPage(page, queryWrapper);
    }
}
