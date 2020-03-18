package solovey.cft.library.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solovey.cft.library.dto.LoanDto;
import solovey.cft.library.dto.SimpleBookLoanDto;
import solovey.cft.library.exception.InvalidRequestException;
import solovey.cft.library.exception.NotFoundException;
import solovey.cft.library.model.Book;
import solovey.cft.library.model.Client;
import solovey.cft.library.model.Loan;
import solovey.cft.library.repository.LoanRepository;
import solovey.cft.library.service.BookService;
import solovey.cft.library.service.ClientService;
import solovey.cft.library.service.LoanService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final BookService bookService;
    private final ClientService clientService;


    @Value("${days.for.return}")
    private int daysForReturn;

    public LoanServiceImpl(LoanRepository loanRepository, @Lazy BookService bookService, ClientService clientService) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
        this.clientService = clientService;
    }

    @Transactional
    @Override
    public Loan addLoan(SimpleBookLoanDto loanDto) {
        Loan loan;
        if (loanDto.getId() != null) {
            loan = getLoanById(loanDto.getId());
        } else {
            loan = new Loan();
            loanRepository.save(loan);
        }
        Client client = clientService.getClientById(loanDto.getClient_id());
        Book book = bookService.getBookById(loanDto.getBook_id());
        BeanUtils.copyProperties(loanDto, loan, "id", "book", "client");
        loan.setClient(client);
        loan.setBook(book);
        List<Loan> loansByClient = loanRepository.findByClientId(client.getId());
//        loansByClient.stream().filter(loanBook -> loanBook.like(loan)).forEach(loanBook -> {
//            throw new InvalidRequestException("Loan already exists");
//        });
        return loan;
    }

    @Transactional
    @Override
    public LoanDto add(SimpleBookLoanDto loanDto) {
        return convertToDto(loanRepository.saveAndFlush(addLoan(loanDto)));
    }

    @Transactional
    @Override
    public Loan getLoanById(Long id) {
        Optional<Loan> loan = loanRepository.findById(id);
        if (loan.isPresent()) {
            return loan.get();
        }
        throw new NotFoundException("Not found element by id " + id.toString());
    }

    @Transactional
    @Override
    public LoanDto findLoanById(Long id) {
        return convertToDto(getLoanById(id));
    }

    @Transactional
    @Override
    public List<LoanDto> findAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        if (loans.size() > 0) {
            return convertToDto(loans);
        }
        throw new NotFoundException("Not found any elements");
    }

    @Transactional
    @Override
    public List<LoanDto> findNotReturnedLoans() {
        List<Loan> loan = loanRepository.findByReturnDateIsNull();
        if (loan.size() > 0) {
            return convertToDto(loan);
        }
        throw new NotFoundException("Not found not returned rents");
    }

    @Transactional
    @Override
    public List<LoanDto> findExpiredLoansOrNull(LocalDate currentDate) {
        LocalDate date = currentDate.minusDays(daysForReturn);
        List<Loan> loans = loanRepository.findByReturnDateIsNullAndLoanDateBefore(date);
        if (loans.size() > 0) {
            return convertToDto(loans);
        }
        return null;
    }

    @Transactional
    @Override
    public LoanDto updateLoan(SimpleBookLoanDto loanDto) {
        Loan loan;
        if (loanDto.getId() != null) {
            loan = getLoanById(loanDto.getId());
        } else {
            loan = new Loan();
            loanRepository.save(loan);
        }
        Client client = clientService.getClientById(loanDto.getClient_id());
        Book book = bookService.getBookById(loanDto.getBook_id());
        BeanUtils.copyProperties(loanDto, loan, "id", "book", "client");
        loan.setClient(client);
        loan.setBook(book);
        List<Loan> loansByClient = loanRepository.findByClientId(client.getId());
        loansByClient.stream().filter(loanBook -> loanBook.like(loan)).forEach(loanBook -> {
            throw new InvalidRequestException("Loan already exists");
        });
        return convertToDto(loan);
    }

    @Transactional
    @Override
    public void deleteLoanById(Long id) {
        Loan loan = getLoanById(id);
        if (loan.getReturnDate() == null) {
            throw new InvalidRequestException("Book rented now");
        }
        loanRepository.deleteById(id);
    }

    @Override
    public LoanDto convertToDto(Loan loan) {
        LoanDto loanDto = new LoanDto();
        BeanUtils.copyProperties(loan, loanDto, "client", "book");
        Client client = loan.getClient();
        loanDto.setClient(clientService.convertToDto(client));
        Book book = loan.getBook();
        loanDto.setBook(bookService.convertToDto(book));
        return loanDto;
    }

    @Override
    public List<LoanDto> convertToDto(Iterable<Loan> loans) {
        List<LoanDto> result = new ArrayList<>();
        for (Loan loan : loans) {
            result.add(convertToDto(loan));
        }
        return result;
    }
}
