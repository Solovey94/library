package solovey.cft.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import solovey.cft.library.dto.AuthorDto;
import solovey.cft.library.dto.BookDto;
import solovey.cft.library.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("authors")
@Api(value = "Author REST Endpoint")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ApiOperation(value = "Return new author")
    @PostMapping
    public AuthorDto addAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.add(authorDto);
    }

    @ApiOperation(value = "Returns all authors")
    @GetMapping
    public List<AuthorDto> findAllAuthors() {
        return authorService.findAllAuthors();
    }

    @ApiOperation(value = "Returns author by lastName")
    @GetMapping("/name")
    public AuthorDto findAuthorByLastName(@RequestBody AuthorDto authorDto) {
        String lastName = authorDto.getLastName();
        return authorService.findAuthorByLastName(lastName);
    }

    @ApiOperation(value = "Returns author by author_id")
    @GetMapping("/id")
    public AuthorDto findAuthorById(@RequestBody AuthorDto authorDto) {
        Long id = authorDto.getId();
        return authorService.findAuthorById(id);
    }

    @ApiOperation(value = "Returns author books")
    @GetMapping("/books")
    public List<BookDto> findBooksByAuthorId(@RequestBody AuthorDto authorDto) {
        Long id = authorDto.getId();
        return authorService.findBooksByAuthorId(id);
    }

    @ApiOperation(value = "Return update author")
    @PutMapping()
    public AuthorDto updateAuthor(@RequestBody AuthorDto authorDto) {
        Long id = authorDto.getId();
        authorDto.setId(id);
        return authorService.update(authorDto);
    }

    @ApiOperation(value = "Delete author by author_id")
    @DeleteMapping()
    public void deleteAuthor(@RequestBody AuthorDto authorDto) {
        Long id = authorDto.getId();
        authorService.deleteAuthorById(id);
    }

}
