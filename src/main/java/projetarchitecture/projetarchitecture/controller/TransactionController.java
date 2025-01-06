package projetarchitecture.projetarchitecture.controller;

import projetarchitecture.projetarchitecture.dto.TransactionRequestDTO;
import projetarchitecture.projetarchitecture.dto.TransactionResponseDTO;
import projetarchitecture.projetarchitecture.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public TransactionResponseDTO createTransaction(@RequestBody TransactionRequestDTO requestDTO) {
        return transactionService.createTransaction(requestDTO);
    }
}
