package solovey.cft.library.service;

import solovey.cft.library.dto.LoanDto;
import solovey.cft.library.dto.SimpleBookLoanDto;
import solovey.cft.library.model.Loan;

import java.time.LocalDate;
import java.util.List;

public interface LoanService {

    Loan addLoan(SimpleBookLoanDto loanDto);

    LoanDto add(SimpleBookLoanDto loanDto);

    Loan getLoanById(Long id);

    LoanDto findLoanById(Long id);

    List<LoanDto> findAllLoans();

    List<LoanDto> findNotReturnedLoans();

    List<LoanDto> findExpiredLoansOrNull(LocalDate currentDate);

    LoanDto updateLoan(SimpleBookLoanDto loanDto);

    void deleteLoanById(Long id);

    LoanDto convertToDto(Loan loan);

    List<LoanDto> convertToDto(Iterable<Loan> loans);
}
