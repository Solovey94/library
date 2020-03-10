package solovey.cft.library.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookDto {
    private Long id;

    private String title;

    private String isbn;

    private String publisher;

    List<AuthorDto> authors;
}
