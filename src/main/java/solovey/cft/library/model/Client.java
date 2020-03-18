package solovey.cft.library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="passport")
    private Long passport;

    @Column(name="email")
    private String email;

    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private Set<Loan> loans;

    public Client() {
        this.loans = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!Objects.equals(id, client.id)) return false;
        if (!firstName.equals(client.firstName)) return false;
        if (!lastName.equals(client.lastName)) return false;
        if (!email.equals(client.email)) return false;
        return passport.equals(client.passport);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + passport.hashCode();
        return result;
    }

}
