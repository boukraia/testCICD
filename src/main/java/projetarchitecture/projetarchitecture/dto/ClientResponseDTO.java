package projetarchitecture.projetarchitecture.dto;

import lombok.Data;

@Data
public class ClientResponseDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
