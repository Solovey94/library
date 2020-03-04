package solovey.cft.library.service;

import solovey.cft.library.dto.LoanDto;

import java.time.LocalDate;
import java.util.List;

public interface LoanService {

    LoanDto addLoan(LoanDto loanDto);

    List<LoanDto> findAllLoans();

    LoanDto findLoanById(Long id);

    List<LoanDto> findNotReturnedLoans();

    List<LoanDto> findExpiredLoansOrNull(LocalDate currentDate);

    LoanDto updateLoan(LoanDto bookLoanDto);

    void deleteLoanById(Long id);
}
