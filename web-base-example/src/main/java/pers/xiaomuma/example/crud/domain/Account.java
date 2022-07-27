package pers.xiaomuma.example.crud.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 账户信息
 * </p>
 *
 * @author ${author}
 * @since 2022-02-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Account implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实名称
     */
    private String realName;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 性别：0->未知；1->男；2->女
     */
    private Integer gender;

    /**
     * 简介
     */
    private String briefIntroduction;

    /**
     * 帐号启用状态: -1->删除, 0->禁用, 1->开启
     */
    private Integer status;

    /**
     * 是否已设置密码:0->未设置；1->已设置
     */
    private Integer hasSetPassword;

    /**
     * 是否已设置手机号:0->未设置；1->已设置
     */
    private Integer hasSetMobile;

    /**
     * 注册时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
