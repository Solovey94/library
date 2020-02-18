package solovey.cft.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solovey.cft.library.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}
