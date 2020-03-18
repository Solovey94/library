package solovey.cft.library.service.impl;


import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solovey.cft.library.dto.ClientDto;
import solovey.cft.library.dto.LoanDto;
import solovey.cft.library.exception.InvalidRequestException;
import solovey.cft.library.exception.NotFoundException;
import solovey.cft.library.model.Client;
import solovey.cft.library.model.Loan;
import solovey.cft.library.repository.ClientRepository;
import solovey.cft.library.service.ClientService;
import solovey.cft.library.service.LoanService;

import java.util.*;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final LoanService loanService;


    public ClientServiceImpl(ClientRepository clientRepository, @Lazy LoanService loanService) {
        this.clientRepository = clientRepository;
        this.loanService = loanService;
    }

    @Transactional
    @Override
    public Client addClient(ClientDto clientDto) {
        Client client;
        if (clientDto.getId() != null) {
            client = getClientById(clientDto.getId());
        } else {
            client = new Client();
            BeanUtils.copyProperties(clientDto, client, "id");
            clientRepository.save(client);
        }
        return client;
    }

    @Override
    public ClientDto add(ClientDto clientDto) {
        return convertToDto(addClient(clientDto));
    }

    @Override
    public Client getClientById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return client.get();
        } else {
            throw new NotFoundException("Not found element by id " + id.toString());
        }
    }

    @Transactional
    @Override
    public ClientDto findClientById(Long id) {
        return convertToDto(getClientById(id));
    }

    @Transactional
    @Override
    public List<ClientDto> findAllClients() {
        List<Client> clients = clientRepository.findAll();
        if (clients.size() > 0) {
            return convertToDto(clients);
        } else {
            throw new NotFoundException("Not found any elements");
        }
    }


    @Transactional
    @Override
    public ClientDto findClientByEmail(String email) {
        if (email == null) {
            throw new NotFoundException("Not found by email " + email.toString());
        }
        Optional<Client> client = clientRepository.findByEmail(email);
        if (client.isPresent()) {
            return convertToDto(client.get());
        } else {
            throw new NotFoundException("Not found by email " + email.toString());
        }
    }

    @Transactional
    @Override
    public ClientDto findClientByPassport(Long passport) {
        if (passport == null) {
            throw new NotFoundException("Not found by passport " + passport.toString());
        }
        Client client = clientRepository.findByPassport(passport);
        return convertToDto(client);
    }

    @Transactional
    @Override
    public List<ClientDto> findClientByName(String firstName, String lastName) {
        Set<Client> result = new HashSet<>();
        if (firstName != null) {
            result.addAll(clientRepository.findByFirstName(firstName));
        }
        if (lastName != null) {
            result.addAll(clientRepository.findByLastName(lastName));
        } if (result.size() > 0) {
            return convertToDto(result);
        }
        throw new NotFoundException("Not found element by name");
    }

    @Transactional
    @Override
    public Client updateClient(ClientDto clientDto) {
        Client client = getClientById(clientDto.getId());
        BeanUtils.copyProperties(clientDto, client, "id");
        return client;
    }

    @Transactional
    @Override
    public ClientDto update(ClientDto clientDto) {
        return convertToDto(clientRepository.saveAndFlush(updateClient(clientDto)));
    }

    @Transactional
    @Override
    public void deleteClientById(Long id) {
        Client client = getClientById(id);
        Set<Loan> loans = client.getLoans();
        for (Loan loan : loans) {
            if (loan.getReturnDate() == null) {
                throw new InvalidRequestException("Client has not returned books");
            }
        }
        for (Loan loan : loans) {
            loanService.deleteLoanById(loan.getId());
        }
        clientRepository.save(client);
        clientRepository.delete(client);
    }

    @Transactional
    @Override
    public List<LoanDto> findLoansByClientId(Long id) {
        Client client = getClientById(id);
        Set<Loan> loans = client.getLoans();
        if (loans.size() > 0) {
            return loanService.convertToDto(loans);
        }
        throw new NotFoundException("Not found loans by element id");
    }

    @Override
    public ClientDto convertToDto(Client client) {
        ClientDto clientDto = new ClientDto();
        BeanUtils.copyProperties(client, clientDto);
        return clientDto;
    }

    @Override
    public List<ClientDto> convertToDto(Iterable<Client> clients) {
        List<ClientDto> result = new ArrayList<>();
        for (Client client : clients) {
            result.add(convertToDto(client));
        }
        return result;
    }
}
