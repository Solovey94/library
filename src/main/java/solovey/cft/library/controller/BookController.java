package solovey.cft.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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

    @PutMapping("/{id}")
    public BookDto updateClient(
            @PathVariable Long id,
            @Validated @RequestBody BookDto bookDto
    ) {
        bookDto.setId(id);
        return bookService.update(bookDto);
    }

    @GetMapping
    public List<BookDto> findBooks(@RequestBody(required = false) BookDto bookDto) {
        if (bookDto == null) {
            return bookService.findAllBooks();
        }
        return bookService.findBooksByTitle(bookDto.getTitle());
    }

    @GetMapping("/{id}")
    public BookDto findBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @GetMapping("/{id}/loans")
    public List<LoanDto> findRentsByBookId(@PathVariable Long id) {
        return bookService.findRentByBookId(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }

}
