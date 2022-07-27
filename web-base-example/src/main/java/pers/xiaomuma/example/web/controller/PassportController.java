package pers.xiaomuma.example.web.controller;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xiaomuma.example.service.biz.PassportBizService;
import pers.xiaomuma.example.web.dto.wrapper.LoginPasswordReq;
import pers.xiaomuma.example.web.dto.wrapper.LoginSmsReq;
import pers.xiaomuma.example.web.vo.OAuth2PassportVO;
import pers.xiaomuma.framework.response.ViewResponse;
import pers.xiaomuma.framework.standard.service.ServiceHelper;
import pers.xiaomuma.framework.standard.service.ServiceResult;


@Api(tags = "授权")
@RestController
@RequestMapping("passport")
@RequiredArgsConstructor
public class PassportController {

    private final PassportBizService passportBizService;

    @ApiOperation("用户名登录")
    @PostMapping("/password")
    public ViewResponse<OAuth2PassportVO> passwordLogin(@RequestBody @Validated LoginPasswordReq req) {
        ServiceResult<OAuth2PassportVO> serviceResult = ServiceHelper.processing(() ->
            passportBizService.passwordLogin(req.getUsername(), req.getPassword())
        );
        return ViewResponse.response(serviceResult);
    }

    @ApiOperation("短信登录")
    @PostMapping("/sms")
    public ViewResponse<OAuth2PassportVO> smsLogin(@RequestBody @Validated LoginSmsReq req) {
        ServiceResult<OAuth2PassportVO> serviceResult = ServiceHelper.processing(() ->
            passportBizService.smsLogin(req.getMobile(), req.getCode())
        );
        return ViewResponse.response(serviceResult);
    }

}
