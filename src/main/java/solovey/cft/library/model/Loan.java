package solovey.cft.library.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Loan loan = (Loan) o;

        if (!Objects.equals(id, loan.id)) return false;
        return like(loan);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + client.hashCode();
        result = 31 * result + book.hashCode();
        result = 31 * result + loanDate.hashCode();
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        return result;
    }
}
