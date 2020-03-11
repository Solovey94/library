package solovey.cft.library.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="loan_date")
    private LocalDate loanDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    public boolean like(Loan loan) {
        if (!client.equals(loan.client)) return false;
        if (!book.equals(loan.book)) return false;
        if (!loanDate.equals(loan.loanDate)) return false;
        return Objects.equals(returnDate, loan.returnDate);
    }
}
