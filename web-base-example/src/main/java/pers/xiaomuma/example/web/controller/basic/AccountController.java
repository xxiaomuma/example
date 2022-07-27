package pers.xiaomuma.example.web.controller.basic;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xiaomuma.example.service.AccountFacadeService;
import pers.xiaomuma.example.web.dto.wrapper.QueryAccountReq;
import pers.xiaomuma.example.web.vo.AccountVO;
import pers.xiaomuma.framework.page.PageResult;
import pers.xiaomuma.framework.response.ViewResponse;
import pers.xiaomuma.framework.standard.service.ServiceHelper;
import pers.xiaomuma.framework.standard.service.ServiceResult;

@Api(tags = "用户")
@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountFacadeService accountFacadeService;

    @ApiOperation("用户列表")
    @GetMapping("list")
    public ViewResponse<PageResult<AccountVO>> list(@RequestBody @Validated QueryAccountReq req) {
        ServiceResult<PageResult<AccountVO>> serviceResult = ServiceHelper.processing(() ->
                accountFacadeService.list(req)
        );
        return ViewResponse.response(serviceResult);
    }
}
