package solovey.cft.library.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solovey.cft.library.dto.AuthorDto;
import solovey.cft.library.dto.BookDto;
import solovey.cft.library.dto.LoanDto;
import solovey.cft.library.exception.InvalidRequestException;
import solovey.cft.library.exception.NotFoundException;
import solovey.cft.library.model.Author;
import solovey.cft.library.model.Book;
import solovey.cft.library.model.Loan;
import solovey.cft.library.repository.BookRepository;
import solovey.cft.library.service.AuthorService;
import solovey.cft.library.service.BookService;
import solovey.cft.library.service.LoanService;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final LoanService loanService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, @Lazy LoanService loanService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.loanService = loanService;
    }

    @Transactional
    @Override
    public Book addBook(BookDto bookDto) {
        Book book;
        if (bookDto.getId() != null) {
            book = getBookById(bookDto.getId());
        } else {
            book = new Book();
            bookRepository.save(book);
        }
        BeanUtils.copyProperties(bookDto, book, "id", "authors");
        setDependencies(book, bookDto);
        return book;
    }

    private void setDependencies(Book book, BookDto bookDto) {
        List<AuthorDto> authorsDto = bookDto.getAuthors();
        Set<Author> authors = book.getAuthors();
        updateAuthors(authors, authorsDto);
        book.setAuthors(authors);
    }

    private void updateAuthors(Set<Author> authors, List<AuthorDto> authorsDto) {
        Set<Author> newAuthors = new HashSet<>();
        for (AuthorDto authorDto : authorsDto) {
            String firstName = authorDto.getFirstName();
            String lastName = authorDto.getLastName();
            Author author = authorService.getAuthorOrNullByName(firstName, lastName);
            if (author == null) {
                authorDto.setId(null);
                author = authorService.updateAuthor(authorDto);
            }
            newAuthors.add(author);
            authors.add(author);
        }
        authors.removeIf(author -> !newAuthors.contains(author));
    }


    @Transactional
    @Override
    public BookDto add(BookDto bookDto) {
        return convertToDto(addBook(bookDto));
    }

    @Transactional
    @Override
    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        }
        throw new NotFoundException("Not found element by id " + id.toString());
    }

    @Transactional
    @Override
    public BookDto findBookById(Long id) {
        return convertToDto(getBookById(id));
    }

    @Transactional
    @Override
    public List<BookDto> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.size() > 0) {
            return convertToDto(books);
        }
        throw new NotFoundException("Not found any elements");
    }

    @Transactional
    @Override
    public Book updateBook(BookDto bookDto) {
        return null;
    }

    @Override
    public BookDto update(BookDto bookDto) {
        return null;
    }

    @Transactional
    @Override
    public List<BookDto> findBooksByTitle(String title) {
        if (title == null) {
            throw new InvalidRequestException("Invalid field " + "title");
        }
        List<Book> books = bookRepository.findByTitle(title);
        if (books.size() > 0) {
            return convertToDto(books);
        }
        throw new NotFoundException("Not found books by title " + title);
    }


    @Transactional
    @Override
    public List<LoanDto> findRentByBookId(Long id) {
        Book book = getBookById(id);
        Set<Loan> rents = book.getLoans();
        if (rents.size() > 0) {
            return loanService.convertToDto(rents);
        }
        throw new NotFoundException("Not found books by " + id.toString());
    }

    @Transactional
    @Override
    public void deleteBookById(Long id) {
        Book book = getBookById(id);
        Set<Author> authors = book.getAuthors();
/*        Set<Loan> loans = book.getLoans();
        loans.stream().filter(loan -> loan.getReturnDate() == null).forEach(loan -> {
            throw new InvalidRequestException("Book rented now");
        });
        loans.stream().map(Loan::getId).forEach(loanService::deleteLoanById);*/
        authors.removeAll(authors);
        bookRepository.save(book);
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto convertToDto(Book book) {
        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(book, bookDto,   "authors");
        Set<Author> authors = book.getAuthors();
        bookDto.setAuthors(authorService.convertToDto(authors));
        return bookDto;
    }

    @Override
    public List<BookDto> convertToDto(Iterable<Book> books) {
        List<BookDto> result = new ArrayList<>();
        for (Book book : books) {
            result.add(convertToDto(book));
        }
        return result;
    }
}
