package solovey.cft.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import solovey.cft.library.dto.BookDto;
import solovey.cft.library.dto.LoanDto;
import solovey.cft.library.service.BookService;

import java.util.List;

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
        return bookService.add(bookDto);
    }

    @ApiOperation(value = "Return update book")
    @PutMapping
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        Long id = bookDto.getId();
        bookDto.setId(id);
        return bookService.update(bookDto);
    }

    @ApiOperation(value = "Return all books")
    @GetMapping
    public List<BookDto> findBooks() {
        return bookService.findAllBooks();
    }

    @ApiOperation(value = "Return book by book_id")
    @GetMapping("/id")
    public BookDto findBookById(@RequestBody BookDto bookDto) {
        return bookService.findBookById(bookDto.getId());
    }

    @ApiOperation(value = "Return book title")
    @GetMapping("/title")
    public List<BookDto> findBookByTitled(@RequestBody BookDto bookDto) {
        return bookService.findBooksByTitle(bookDto.getTitle());
    }

    @ApiOperation(value = "Return all loans by book_id")
    @GetMapping("/loans")
    public List<LoanDto> findLoansByBookId(@RequestBody BookDto bookDto) {
        return bookService.findLoanByBookId(bookDto.getId());
    }

    @ApiOperation(value = "Delete book by book_id")
    @DeleteMapping
    public void deleteBook(@RequestBody BookDto bookDto) {
        bookService.deleteBookById(bookDto.getId());
    }

}
