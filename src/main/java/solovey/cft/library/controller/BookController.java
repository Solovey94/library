package solovey.cft.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import solovey.cft.library.dto.BookDto;
import solovey.cft.library.dto.LoanDto;
import solovey.cft.library.service.BookService;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookDto addBook(@RequestBody BookDto bookDto) {
        return bookService.add(bookDto);
    }

    @PutMapping
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        Long id = bookDto.getId();
        bookDto.setId(id);
        return bookService.update(bookDto);
    }

    @GetMapping
    public List<BookDto> findBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/id")
    public BookDto findBookById(@RequestBody BookDto bookDto) {
        return bookService.findBookById(bookDto.getId());
    }

    @GetMapping("/title")
    public List<BookDto> findBookByTitled(@RequestBody BookDto bookDto) {
        return bookService.findBooksByTitle(bookDto.getTitle());
    }

    @GetMapping("/loans")
    public List<LoanDto> findLoansByBookId(@RequestBody BookDto bookDto) {
        return bookService.findLoanByBookId(bookDto.getId());
    }

    @DeleteMapping
    public void deleteBook(@RequestBody BookDto bookDto) {
        bookService.deleteBookById(bookDto.getId());
    }

}
