package solovey.cft.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solovey.cft.library.model.Loan;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByReturnDateIsNull();

    List<Loan> findByReturnDateIsNullAndLoanDateBefore(LocalDate date);

    List<Loan> findByClientId(Long clientId);
}
