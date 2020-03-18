package solovey.cft.library.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookDto implements Serializable {
    private Long id;

    private String title;

    private String isbn;

    private String publisher;

    List<AuthorDto> authors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookDto book = (BookDto) o;

        if (!Objects.equals(id, book.id)) return false;
        if (!title.equals(book.title)) return false;
        if (!Objects.equals(isbn, book.isbn)) return false;
        if (authors.size() != book.authors.size()) return false;
        return authors.containsAll(book.authors);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + title.hashCode();
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        return result;
    }
}
