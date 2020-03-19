package solovey.cft.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import solovey.cft.library.dto.ClientDto;
import solovey.cft.library.dto.LoanDto;
import solovey.cft.library.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("clients")
@Api(value = "Client REST Endpoint")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ApiOperation(value = "Return new client")
    @PostMapping
    public ClientDto addClient(@RequestBody ClientDto clientDto) {
        return clientService.add(clientDto);
    }

    @ApiOperation(value = "Return update client")
    @PutMapping
    public ClientDto updateClient(@Validated @RequestBody ClientDto clientDto) {
        Long id = clientDto.getId();
        clientDto.setId(id);
        return clientService.update(clientDto);
    }

    @ApiOperation(value = "Return all clients")
    @GetMapping
    public List<ClientDto> findAllClients() {
            return clientService.findAllClients();
    }

    @ApiOperation(value = "Return all clients with the same name")
    @GetMapping("/name")
    public List<ClientDto> findClientByName(@RequestBody ClientDto clientDto) {
        String firstName = clientDto.getFirstName();
        String lastName = clientDto.getLastName();
        return clientService.findClientByName(firstName, lastName);
    }

    @ApiOperation(value = "Return client by client_email")
    @GetMapping("/email")
    public ClientDto findClientByEmail(@RequestBody ClientDto clientDto) {
        return clientService.findClientByEmail(clientDto.getEmail());
    }

    @ApiOperation(value = "Return client by client_passport")
    @GetMapping("/passport")
    public ClientDto findClientByPassport(@RequestBody ClientDto clientDto) {
        return clientService.findClientByPassport(clientDto.getPassport());
    }

    @ApiOperation(value = "Return client by client_id")
    @GetMapping("/id")
    public ClientDto findClientById(@RequestBody ClientDto clientDto) {
        Long id = clientDto.getId();
        return clientService.findClientById(id);
    }

    @ApiOperation(value = "Return all loans by client_id")
    @GetMapping("/loans")
    public List<LoanDto> findLoansByClientId(@RequestBody ClientDto clientDto) {
        Long id = clientDto.getId();
        return clientService.findLoansByClientId(id);
    }

    @ApiOperation(value = "Delete client by client_id")
    @DeleteMapping
    public void deleteClient(@RequestBody ClientDto clientDto) {
        Long id = clientDto.getId();
        clientService.deleteClientById(id);
    }

}
