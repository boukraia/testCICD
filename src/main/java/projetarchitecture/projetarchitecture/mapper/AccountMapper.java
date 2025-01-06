package projetarchitecture.projetarchitecture.mapper;

import projetarchitecture.projetarchitecture.dto.AccountRequestDTO;
import projetarchitecture.projetarchitecture.dto.AccountResponseDTO;
import projetarchitecture.projetarchitecture.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public Account toEntity(AccountRequestDTO dto) {
        Account account = new Account();
        account.setAccountNumber(dto.getAccountNumber());
        account.setBalance(dto.getBalance());
        account.setType(dto.getType());
        account.setClientId(dto.getClientId());
        return account;
    }

    public AccountResponseDTO toDTO(Account account) {
        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setId(account.getId());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setBalance(account.getBalance());
        dto.setType(account.getType());
        dto.setClientId(account.getClientId());
        return dto;
    }
}
