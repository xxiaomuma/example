package pers.xiaomuma.example.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@ApiModel("用户信息")
public class AccountVO {

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("真实名称")
    private String realName;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("生日")
    private LocalDate birthday;

    @ApiModelProperty("性别：0->未知；1->男；2->女")
    private Integer gender;

    @ApiModelProperty("简介")
    private String briefIntroduction;

    @ApiModelProperty("是否已设置密码:0->未设置；1->已设置")
    private Integer hasSetPassword;

    @ApiModelProperty("否已设置手机号:0->未设置；1->已设置")
    private Integer hasSetMobile;

    @ApiModelProperty("注册时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
