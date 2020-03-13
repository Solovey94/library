package solovey.cft.library.service;

import solovey.cft.library.dto.AuthorDto;
import solovey.cft.library.dto.BookDto;
import solovey.cft.library.model.Author;

import java.util.List;

public interface AuthorService {

    Author addAuthor(AuthorDto authorDto);

    AuthorDto add(AuthorDto authorDto);

    Author updateAuthor(AuthorDto authorDto);

    AuthorDto update(AuthorDto authorDto);

    Author getAuthorById(Long id);

    AuthorDto findAuthorById(Long id);

    Author getAuthorOrNullByName(String firstName, String lastName);

    AuthorDto findAuthorByLastName(String lastName);

    List<AuthorDto> findAllAuthors();

    void deleteAuthorById(Long id);

    List<BookDto> findBooksByAuthorId(Long id);

    AuthorDto convertToDto(Author author);

    List<AuthorDto> convertToDto(Iterable<Author> authors);
}
