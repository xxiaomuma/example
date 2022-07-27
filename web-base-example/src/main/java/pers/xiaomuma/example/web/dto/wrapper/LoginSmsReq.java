package pers.xiaomuma.example.web.dto.wrapper;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("登录授权")
public class LoginSmsReq {

    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank
    private String mobile;

    @ApiModelProperty(value = "验证码", required = true)
    @Length(min = 6, max = 6)
    private String code;
}
