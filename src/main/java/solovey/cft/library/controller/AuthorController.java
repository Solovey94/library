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

    @GetMapping("/{id}")
    public AuthorDto findAuthorById(@PathVariable Long id) {
        return authorService.findAuthorById(id);
    }

    @GetMapping("/{id}/books")
    public List<BookDto> findBooksByAuthorId(@PathVariable Long id) {
        return authorService.findBooksByAuthorId(id);
    }

    @PutMapping("/{id}")
    public AuthorDto updateAuthor(
            @PathVariable Long id,
            @Validated @RequestBody AuthorDto authorDto
    ) {
        authorDto.setId(id);
        return authorService.update(authorDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthorById(id);
    }

}
