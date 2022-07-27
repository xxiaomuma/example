package pers.xiaomuma.example.converter;


import org.mapstruct.Mapper;
import pers.xiaomuma.example.crud.domain.Account;
import pers.xiaomuma.example.web.vo.AccountVO;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountConverter {

    AccountVO domain2VO(Account account);

    List<AccountVO> domain2VO(List<Account> accounts);
}
