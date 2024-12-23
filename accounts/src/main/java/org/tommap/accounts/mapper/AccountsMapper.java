package org.tommap.accounts.mapper;

import org.tommap.accounts.dto.AccountsDTO;
import org.tommap.accounts.entity.Account;

public class AccountsMapper {
    public static AccountsDTO toAccountsDTO(Account account, AccountsDTO accountsDTO) {
        accountsDTO.setAccountNumber(account.getId());
        accountsDTO.setAccountType(account.getAccountType());
        accountsDTO.setBranchAddress(account.getBranchAddress());

        return accountsDTO;
    }

    public static Account toAccount(AccountsDTO accountsDTO, Account account) {
        account.setId(accountsDTO.getAccountNumber());
        account.setAccountType(accountsDTO.getAccountType());
        account.setBranchAddress(accountsDTO.getBranchAddress());

        return account;
    }
}
