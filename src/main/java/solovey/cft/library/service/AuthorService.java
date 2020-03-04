package solovey.cft.library.service;

import solovey.cft.library.dto.AuthorDto;
import solovey.cft.library.dto.BookDto;
import solovey.cft.library.model.Author;

import java.util.List;

public interface AuthorService {

    AuthorDto addAuthor(AuthorDto authorDto);

    Author findAuthorById(Long id);

    Author findAuthorByLastName(String lastName);

    List<Author> findAllAuthors();

    AuthorDto updateAuthor(AuthorDto authorDto);

    void deleteAuthorById(Long id);

    List<BookDto> findBooksByAuthorId(Long id);
}
