package pers.xiaomuma.example.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pers.xiaomuma.example.converter.AccountConverter;
import pers.xiaomuma.example.crud.domain.Account;
import pers.xiaomuma.example.crud.param.QueryAccountParam;
import pers.xiaomuma.example.crud.service.IAccountService;
import pers.xiaomuma.example.web.dto.wrapper.QueryAccountReq;
import pers.xiaomuma.example.web.vo.AccountVO;
import pers.xiaomuma.framework.base.support.database.utils.PageUtils;
import pers.xiaomuma.framework.page.PageResult;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountFacadeService {

    private final IAccountService accountService;
    private final AccountConverter accountConverter;

    public PageResult<AccountVO> list(QueryAccountReq req) {
        QueryAccountParam param = QueryAccountParam.builder()
                .status(req.getStatus())
                .pageNum(req.getPageNum())
                .pageSize(req.getPageSize())
                .build();
        IPage<Account> page = accountService.findNormalAccountList(param);
        if (PageUtils.isEmpty(page)) {
            return PageResult.emptyPage();
        }
        List<AccountVO> accountVOS = accountConverter.domain2VO(page.getRecords());
        return new PageResult<>(page.getPages(), page.getSize(), page.getTotal(), accountVOS);
    }
}
