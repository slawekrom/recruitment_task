package org.example.mapper;

import java.math.BigDecimal;
import java.util.Set;
import org.example.dto.AccountDto;
import org.example.dto.CreateAccountDto;
import org.example.enums.Currency;
import org.example.model.Account;
import org.example.model.SubAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

  AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

  @Mapping(source = "balance", target = "subAccounts", qualifiedByName = "initSubAccounts")
  Account toAccount(CreateAccountDto createAccountDto);

  AccountDto toAccountDto(Account account);

  @Named("initSubAccounts")
  default Set<SubAccount> initSubAccounts(BigDecimal balance) {
    var plnSubAccount = SubAccount.builder()
        .balance(balance)
        .currency(Currency.PLN)
        .build();
    var usdSubAccount = SubAccount.builder()
        .balance(BigDecimal.ZERO)
        .currency(Currency.USD)
        .build();

    return Set.of(plnSubAccount, usdSubAccount);
  }
}
