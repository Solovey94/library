package solovey.cft.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import solovey.cft.library.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByName(String name);

    List<Book> findByAuthors_FirstnameAndAuthors_Lastname(String firstname, String lastname);

    @Query("select sum(a.totalQuantity) from Book a")
    Long getSumTotalQuantity();

    @Query("select sum(a.currentQuantity) from Book a")
    Long getSumCurrentQuantity();
}
