package solovey.cft.library.service;

import solovey.cft.library.dto.ClientDto;
import solovey.cft.library.dto.LoanDto;
import solovey.cft.library.model.Client;

import java.util.List;

public interface ClientService {

    Client addClient(ClientDto clientDto);

    ClientDto add(ClientDto clientDto);

    Client updateClient(ClientDto clientDto);

    ClientDto update(ClientDto clientDto);

    Client getClientById(Long id);

    ClientDto findClientById(Long id);

    List<ClientDto> findAllClients();

    ClientDto findClientByEmail(String email);

    ClientDto findClientByPassport(Long passport);

    List<ClientDto> findClientByName(String firstName, String lastName);

    List<LoanDto> findLoansByClientId(Long id);

    void deleteClientById(Long id);

    ClientDto convertToDto(Client client);

    List<ClientDto> convertToDto(Iterable<Client> clients);
}
