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
    public ClientDto addClient(@RequestBody ClientDto clientDto) {
        return clientService.add(clientDto);
    }

    @PutMapping
    public ClientDto updateClient(@Validated @RequestBody ClientDto clientDto) {
        Long id = clientDto.getId();
        clientDto.setId(id);
        return clientService.update(clientDto);
    }

    @GetMapping
    public List<ClientDto> findAllClients() {
            return clientService.findAllClients();
    }

    @GetMapping("/name")
    public List<ClientDto> findClientByName(@RequestBody ClientDto clientDto) {
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

    @GetMapping("/id")
    public ClientDto findClientById(@RequestBody ClientDto clientDto) {
        Long id = clientDto.getId();
        return clientService.findClientById(id);
    }

    @GetMapping("/loans")
    public List<LoanDto> findLoansByClientId(@RequestBody ClientDto clientDto) {
        Long id = clientDto.getId();
        return clientService.findLoansByClientId(id);
    }

    @DeleteMapping
    public void deleteClient(@RequestBody ClientDto clientDto) {
        Long id = clientDto.getId();
        clientService.deleteClientById(id);
    }

}
