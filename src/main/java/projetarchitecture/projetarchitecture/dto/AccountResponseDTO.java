package projetarchitecture.projetarchitecture.dto;

import lombok.Data;

@Data
public class AccountResponseDTO {
    private String id;
    private String accountNumber;
    private Double balance;
    private String type;
    private String clientId;
}
