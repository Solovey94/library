package solovey.cft.library.service;

import solovey.cft.library.dto.BookDto;
import solovey.cft.library.dto.LoanDto;

import java.util.List;

public interface BookService {

    BookDto addBook(BookDto bookDto);

    List<BookDto> findAllBooks();

    BookDto findBookById(Long id);

    List<BookDto> findBooksByTitle(String title);

    BookDto updateBook(BookDto bookDto);

    List<LoanDto> findRentByBookId(Long id);

    void deleteBookById(Long id);
}
