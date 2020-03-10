package solovey.cft.library.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
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

}
