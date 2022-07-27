package pers.xiaomuma.example.web.dto.wrapper;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("登录授权")
public class LoginPasswordReq {

	@ApiModelProperty(value = "用户名", required = true)
	@NotBlank
	private String username;

	@ApiModelProperty(value = "密码明文", required = true)
	@Length(min = 6, max = 12)
	private String password;

}
