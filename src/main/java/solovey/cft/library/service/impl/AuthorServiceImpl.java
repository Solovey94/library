package solovey.cft.library.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solovey.cft.library.dto.AuthorDto;
import solovey.cft.library.dto.BookDto;
import solovey.cft.library.exception.NotFoundException;
import solovey.cft.library.model.Author;
import solovey.cft.library.model.Book;
import solovey.cft.library.repository.AuthorRepository;
import solovey.cft.library.service.AuthorService;
import solovey.cft.library.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookService bookService;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, @Lazy BookService bookService) {
        this.authorRepository = authorRepository;
        this.bookService = bookService;
    }

    @Transactional
    @Override
    public Author addAuthor(AuthorDto authorDto) {
        Author author;
        if (authorDto.getId() != null) {
            author = getAuthorById(authorDto.getId());
        } else {
            author = new Author();
            authorRepository.save(author);
        }
        BeanUtils.copyProperties(authorDto, author, "id");
        return author;
    }

    @Transactional
    @Override
    public AuthorDto add(AuthorDto authorDto) {
        return convertToDto(addAuthor(authorDto));
    }

    @Override
    public Author getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return author.get();
        }
        throw new NotFoundException("Not found element by id " + id.toString());
    }

    @Transactional(readOnly = true)
    @Override
    public AuthorDto findAuthorById(Long id) {
        return convertToDto(getAuthorById(id));
    }


    public Author getAuthorOrNullByName(String firstName, String lastName) {
        Optional<Author> author = authorRepository.findByFirstNameAndLastName(firstName, lastName);
        return author.orElse(null);
    }

    @Transactional
    @Override
    public AuthorDto findAuthorByLastName(String lastName) {
        Author author = authorRepository.findByLastName(lastName);
        return convertToDto(author);
    }

    @Transactional
    @Override
    public List<AuthorDto> findAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (authors.size() > 0) {
            return convertToDto(authors);
        }
        throw new NotFoundException("Not found any elements");
    }

    @Transactional
    @Override
    public Author updateAuthor(AuthorDto authorDto) {
        Author author = getAuthorById(authorDto.getId());
        BeanUtils.copyProperties(authorDto, author, "id");
        return author;
    }

    @Transactional
    @Override
    public AuthorDto update(AuthorDto authorDto) {
        return convertToDto(authorRepository.saveAndFlush(updateAuthor(authorDto)));
    }


    @Transactional
    @Override
    public void deleteAuthorById(Long id) {
        Author author = getAuthorById(id);
        Set<Book> books = author.getBooks();
        books.removeAll(books);
        authorRepository.save(author);
        authorRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<BookDto> findBooksByAuthorId(Long id) {
        Author author = getAuthorById(id);
        Set<Book> books = author.getBooks();
        return bookService.convertToDto(books);
    }

    @Override
    public AuthorDto convertToDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        BeanUtils.copyProperties(author, authorDto);
        return authorDto;
    }

    @Override
    public List<AuthorDto> convertToDto(Iterable<Author> authors) {
        List<AuthorDto> result = new ArrayList<>();
        for (Author author : authors) {
            result.add(convertToDto(author));
        }
        return result;
    }

}
