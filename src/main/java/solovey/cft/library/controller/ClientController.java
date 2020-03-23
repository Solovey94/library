package solovey.cft.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import solovey.cft.library.dto.ClientDto;
import solovey.cft.library.dto.LoanDto;
import solovey.cft.library.model.Client;
import solovey.cft.library.service.ClientService;

import java.util.List;

import static solovey.cft.library.log.message.ControllerMessages.*;

@Slf4j
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
        log.info(LOG_ADD_NEW, ClientDto.class.toString(), clientDto.toString());
        return clientService.add(clientDto);
    }

    @ApiOperation(value = "Return update client")
    @PutMapping
    public ClientDto updateClient(@Validated @RequestBody ClientDto clientDto) {
        Long id = clientDto.getId();
        clientDto.setId(id);
        log.info(LOG_UPDATE, ClientDto.class.toString(), clientDto.toString());
        return clientService.update(clientDto);
    }

    @ApiOperation(value = "Return all clients")
    @GetMapping
    public List<ClientDto> findAllClients() {
        log.info(LOG_GET_ALL, ClientDto.class.toString());
        return clientService.findAllClients();
    }

    @ApiOperation(value = "Return all clients with the same name")
    @PostMapping("/name")
    public List<ClientDto> findClientByName(@RequestBody ClientDto clientDto) {
        String firstName = clientDto.getFirstName();
        String lastName = clientDto.getLastName();
        log.info(LOG_GET_ALL, ClientDto.class.toString());
        return clientService.findClientByName(firstName, lastName);
    }

    @ApiOperation(value = "Return client by client_email")
    @PostMapping("/email")
    public ClientDto findClientByEmail(@RequestBody ClientDto clientDto) {
        log.info(LOG_GET, ClientDto.class.toString(), clientDto.toString());
        return clientService.findClientByEmail(clientDto.getEmail());
    }

    @ApiOperation(value = "Return client by client_passport")
    @PostMapping("/passport")
    public ClientDto findClientByPassport(@RequestBody ClientDto clientDto) {
        log.info(LOG_GET, ClientDto.class.toString(), clientDto.toString());
        return clientService.findClientByPassport(clientDto.getPassport());
    }

    @ApiOperation(value = "Return client by client_id")
    @GetMapping("/{id}")
    public ClientDto findClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        log.info(LOG_GET, Client.class.toString(), client.toString());
        return clientService.findClientById(id);
    }

    @ApiOperation(value = "Return all loans by client_id")
    @GetMapping("/{id}/loans")
    public List<LoanDto> findLoansByClientId(@PathVariable Long id) {
        log.info(LOG_GET_ALL, LoanDto.class.toString());
        return clientService.findLoansByClientId(id);
    }

    @ApiOperation(value = "Delete client by client_id")
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
        log.info(LOG_DELETE_BY_ID, ClientDto.class.toString(), id);
    }

}
