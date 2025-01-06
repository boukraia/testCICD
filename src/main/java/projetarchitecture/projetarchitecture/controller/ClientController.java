package projetarchitecture.projetarchitecture.controller;

import projetarchitecture.projetarchitecture.dto.ClientRequestDTO;
import projetarchitecture.projetarchitecture.dto.ClientResponseDTO;
import projetarchitecture.projetarchitecture.mapper.ClientMapper;
import projetarchitecture.projetarchitecture.model.Client;
import projetarchitecture.projetarchitecture.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;

    @GetMapping
    public List<ClientResponseDTO> getAllClients() {
        return clientService.getAllClients().stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ClientResponseDTO getClientById(@PathVariable String id) {
        return clientMapper.toDTO(clientService.getClientById(id));
    }

    @PostMapping
    public ClientResponseDTO createClient(@Valid @RequestBody ClientRequestDTO requestDTO) {
        Client client = clientMapper.toEntity(requestDTO);
        return clientMapper.toDTO(clientService.createClient(client));
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);
    }
}
