package projetarchitecture.projetarchitecture.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AccountRequestDTO {
    @NotEmpty(message = "Account number is required")
    private String accountNumber;

    @NotNull(message = "Balance cannot be null")
    @Min(value = 0, message = "Balance cannot be negative")
    private Double balance;

    @NotEmpty(message = "Account type is required")
    private String type;

    @NotEmpty(message = "Client ID is required")
    private String clientId;
}
