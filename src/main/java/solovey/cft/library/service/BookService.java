package solovey.cft.library.service;

import solovey.cft.library.dto.BookDto;
import solovey.cft.library.dto.LoanDto;
import solovey.cft.library.model.Book;

import java.util.List;

public interface BookService {

    Book addBook(BookDto bookDto);

    BookDto add(BookDto bookDto);

    Book updateBook(BookDto bookDto);

    BookDto update(BookDto bookDto);

    Book getBookById(Long id);

    BookDto findBookById(Long id);

    List<BookDto> findAllBooks();

    List<BookDto> findBooksByTitle(String title);

    List<LoanDto> findLoanByBookId(Long id);

    void deleteBookById(Long id);

    BookDto convertToDto(Book book);

    List<BookDto> convertToDto(Iterable<Book> books);
}
