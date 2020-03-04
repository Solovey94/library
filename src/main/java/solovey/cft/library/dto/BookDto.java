package solovey.cft.library.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import solovey.cft.library.dto.groups.Details;
import solovey.cft.library.model.Author;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class BookDto {
    @JsonView(Details.class)
    private Long id;

    @JsonView(Details.class)
    private String title;

    @JsonView(Details.class)
    private String isbn;

    @JsonView(Details.class)
    private String publisher;

    @JsonView(Details.class)
    List<AuthorDto> authors;
}
