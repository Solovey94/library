package solovey.cft.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import solovey.cft.library.dto.AuthorDto;
import solovey.cft.library.dto.BookDto;
import solovey.cft.library.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public AuthorDto addAuthor(@Validated @RequestBody AuthorDto authorDto) {
        return authorService.add(authorDto);
    }

    @GetMapping
    public List<AuthorDto> findAllAuthors() {
        return authorService.findAllAuthors();
    }

    @GetMapping("/name")
    public AuthorDto findAuthorByLastName(@RequestBody AuthorDto authorDto) {
        String lastName = authorDto.getLastName();
        return authorService.findAuthorByLastName(lastName);
    }

    @GetMapping("/id")
    public AuthorDto findAuthorById(@RequestBody AuthorDto authorDto) {
        Long id = authorDto.getId();
        return authorService.findAuthorById(id);
    }

    @GetMapping("/books")
    public List<BookDto> findBooksByAuthorId(@RequestBody AuthorDto authorDto) {
        Long id = authorDto.getId();
        return authorService.findBooksByAuthorId(id);
    }

    @PutMapping()
    public AuthorDto updateAuthor(@RequestBody AuthorDto authorDto) {
        Long id = authorDto.getId();
        authorDto.setId(id);
        return authorService.update(authorDto);
    }

    @DeleteMapping()
    public void deleteAuthor(@RequestBody AuthorDto authorDto) {
        Long id = authorDto.getId();
        authorService.deleteAuthorById(id);
    }

}
