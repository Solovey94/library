package solovey.cft.library.service;

import solovey.cft.library.dto.ClientDto;
import solovey.cft.library.dto.LoanDto;
import solovey.cft.library.model.Client;

import java.util.List;

public interface ClientService {

    ClientDto addClient(ClientDto clientDto);

    List<ClientDto> findAllClients();

    ClientDto findClientById(Long id);

    ClientDto findClientByEmail(String email);

    ClientDto findClientByPassport(Long passport);

    List<ClientDto> findClientByName(String firstName, String lastName);

    List<LoanDto> findLoansByClientId(Long id);

    ClientDto updateClient(ClientDto clientDto);

    void deleteClientById(Long id);
}
