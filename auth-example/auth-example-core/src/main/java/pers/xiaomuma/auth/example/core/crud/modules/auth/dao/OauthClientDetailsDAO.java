package pers.xiaomuma.auth.example.core.crud.modules.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pers.xiaomuma.auth.example.core.crud.modules.auth.domain.OauthClientDetails;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-02-21
 */
public interface OauthClientDetailsDAO extends BaseMapper<OauthClientDetails> {

    OauthClientDetails queryClientByClientId(@Param("clientId") String clientId);
}
