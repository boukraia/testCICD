package projetarchitecture.projetarchitecture.mapper;

import projetarchitecture.projetarchitecture.dto.ClientRequestDTO;
import projetarchitecture.projetarchitecture.dto.ClientResponseDTO;
import projetarchitecture.projetarchitecture.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public Client toEntity(ClientRequestDTO dto) {
        Client client = new Client();
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setEmail(dto.getEmail());
        client.setPhoneNumber(dto.getPhoneNumber());
        return client;
    }

    public ClientResponseDTO toDTO(Client client) {
        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setId(client.getId());
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setEmail(client.getEmail());
        dto.setPhoneNumber(client.getPhoneNumber());
        return dto;
    }
}
