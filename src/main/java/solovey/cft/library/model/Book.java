package solovey.cft.library.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name = "isbn")
    private String isbn;

    @Column(name="publisher")
    private String publisher;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable (
            name = "author_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors;

    @OneToMany(
            mappedBy = "book",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private Set<Loan> loans;

    public Book() {
        this.authors = new HashSet<>();
        this.loans = new HashSet<>();
    }

    public boolean like(Book book) {
        if (!title.equals(book.title)) return false;
        if (authors.size() != book.authors.size()) return false;
        return authors.containsAll(book.authors);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        if (!Objects.equals(id, book.id)) return false;
        if (!Objects.equals(isbn, book.isbn)) return false;
        return like(book);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + title.hashCode();
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        return result;
    }
}
