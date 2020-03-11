package solovey.cft.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import solovey.cft.library.dto.ClientDto;
import solovey.cft.library.dto.LoanDto;
import solovey.cft.library.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ClientDto addAuthor(@RequestBody ClientDto clientDto) {
        return clientService.add(clientDto);
    }

    @PutMapping("/{id}")
    public ClientDto updateClient(
            @PathVariable Long id,
            @Validated @RequestBody ClientDto clientDto
    ) {
        clientDto.setId(id);
        return clientService.update(clientDto);
    }

    @GetMapping
    public List<ClientDto> findClients(@RequestBody(required = false) ClientDto clientDto) {
        if (clientDto == null) {
            return clientService.findAllClients();
        }
        String firstName = clientDto.getFirstName();
        String lastName = clientDto.getLastName();
        return clientService.findClientByName(firstName, lastName);

    }

    @GetMapping("/email")
    public ClientDto findClientByEmail(@RequestBody ClientDto clientDto) {
        return clientService.findClientByEmail(clientDto.getEmail());
    }

    @GetMapping("/passport")
    public ClientDto findClientByPassport(@RequestBody ClientDto clientDto) {
        return clientService.findClientByPassport(clientDto.getPassport());
    }

    @GetMapping("/{id}")
    public ClientDto findClientById(@PathVariable Long id) {
        return clientService.findClientById(id);
    }

    @GetMapping("/{id}/loans")
    public List<LoanDto> findLoansByClientId(@PathVariable Long id) {
        return clientService.findLoansByClientId(id);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
    }
}
