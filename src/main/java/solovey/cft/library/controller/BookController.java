package solovey.cft.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import solovey.cft.library.dto.BookDto;
import solovey.cft.library.dto.LoanDto;
import solovey.cft.library.model.Book;
import solovey.cft.library.service.BookService;

import java.util.List;

import static solovey.cft.library.log.message.ControllerMessages.*;


@Slf4j
@RestController
@RequestMapping("books")
@Api(value = "Book REST Endpoint")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ApiOperation(value = "Return new book")
    @PostMapping
    public BookDto addBook(@RequestBody BookDto bookDto) {
        log.info(LOG_ADD_NEW, BookDto.class.toString(), bookDto.toString());
        return bookService.add(bookDto);
    }

    @ApiOperation(value = "Return update book")
    @PutMapping
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        Long id = bookDto.getId();
        bookDto.setId(id);
        log.info(LOG_UPDATE, Book.class.toString(), bookDto.toString());
        return bookService.update(bookDto);
    }

    @ApiOperation(value = "Return all books")
    @GetMapping
    public List<BookDto> findBooks() {
        log.info(LOG_GET_ALL, BookDto.class.toString());
        return bookService.findAllBooks();
    }

    @ApiOperation(value = "Return book by book_id")
    @GetMapping("/{id}")
    public BookDto findBookById(@PathVariable Long id) {
        BookDto bookDto = bookService.findBookById(id);
        log.info(LOG_GET, BookDto.class.toString(), bookDto.toString());
        return bookDto;
    }

    @ApiOperation(value = "Return book title")
    @PostMapping("/title")
    public List<BookDto> findBookByTitled(@RequestBody BookDto bookDto) {
        log.info(LOG_GET_ALL, BookDto.class.toString());
        return bookService.findBooksByTitle(bookDto.getTitle());
    }

    @ApiOperation(value = "Return all loans by book_id")
    @GetMapping("/{id}/loans")
    public List<LoanDto> findLoansByBookId(@PathVariable Long id) {
        log.info(LOG_GET_ALL, LoanDto.class.toString());
        return bookService.findLoanByBookId(id);
    }

    @ApiOperation(value = "Delete book by book_id")
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        log.info(LOG_DELETE_BY_ID, Book.class.toString(), id);
    }

}
