package projetarchitecture.projetarchitecture.controller;

import projetarchitecture.projetarchitecture.dto.AccountRequestDTO;
import projetarchitecture.projetarchitecture.dto.AccountResponseDTO;
import projetarchitecture.projetarchitecture.mapper.AccountMapper;
import projetarchitecture.projetarchitecture.model.Account;
import projetarchitecture.projetarchitecture.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @GetMapping
    public List<AccountResponseDTO> getAllAccounts() {
        return accountService.getAllAccounts().stream()
                .map(accountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AccountResponseDTO getAccountById(@PathVariable String id) {
        return accountMapper.toDTO(accountService.getAccountById(id));
    }

    @PostMapping
    public AccountResponseDTO createAccount(@Valid @RequestBody AccountRequestDTO requestDTO) {
        Account account = accountMapper.toEntity(requestDTO);
        return accountMapper.toDTO(accountService.createAccount(account));
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
    }
}
