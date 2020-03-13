package solovey.cft.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import solovey.cft.library.model.Author;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAll();

    Author findByLastName(String lastName);

    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM author_books where book_id=:book_id);"
    )
    Set<Author> findAuthorsOfBook(@Param("book_id") Long bookId);


}
