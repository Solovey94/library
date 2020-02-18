package solovey.cft.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solovey.cft.library.model.Loan;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findLoansByClientId(Long clientId);
}
