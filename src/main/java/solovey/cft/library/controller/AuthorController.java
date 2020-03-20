package solovey.cft.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import solovey.cft.library.dto.AuthorDto;
import solovey.cft.library.dto.BookDto;
import solovey.cft.library.model.Author;
import solovey.cft.library.service.AuthorService;

import java.util.List;

import static solovey.cft.library.log.message.ControllerMessages.*;


@Slf4j
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
        log.info(LOG_ADD_NEW, Author.class.toString(), authorDto.toString());
        return authorService.add(authorDto);
    }

    @ApiOperation(value = "Returns all authors")
    @GetMapping
    public List<AuthorDto> findAllAuthors() {
        log.info(LOG_GET_ALL, Author.class.toString());
        return authorService.findAllAuthors();
    }

    @ApiOperation(value = "Returns author by lastName")
    @GetMapping("/name/{lastName}")
    public AuthorDto findAuthorByLastName(@PathVariable String lastName) {
        AuthorDto authorDto = authorService.findAuthorByLastName(lastName);
        log.info(LOG_GET, Author.class.toString(), authorDto.toString());
        return authorService.findAuthorByLastName(lastName);
    }

    @ApiOperation(value = "Returns author by author_id")
    @GetMapping("/{id}")
    public AuthorDto findAuthorById(@PathVariable Long id) {
        AuthorDto authorDto = authorService.findAuthorById(id);
        log.info(LOG_GET, AuthorDto.class.toString(), authorDto.toString());
        return authorService.findAuthorById(id);
    }

    @ApiOperation(value = "Returns author books")
    @GetMapping("/{id}/books")
    public List<BookDto> findBooksByAuthorId(@PathVariable Long id) {
        log.info(LOG_GET_ALL, BookDto.class.toString());
        return authorService.findBooksByAuthorId(id);
    }

    @ApiOperation(value = "Return update author")
    @PutMapping()
    public AuthorDto updateAuthor(@RequestBody AuthorDto authorDto) {
        Long id = authorDto.getId();
        authorDto.setId(id);
        log.info(LOG_UPDATE, Author.class.toString(), authorDto.toString());
        return authorService.update(authorDto);
    }

    @ApiOperation(value = "Delete author by author_id")
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthorById(id);
        log.info(LOG_DELETE_BY_ID, Author.class.toString(), id);
    }

}
