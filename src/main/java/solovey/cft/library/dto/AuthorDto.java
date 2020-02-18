package solovey.cft.library.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AuthorDto {
    @JsonView(Details.class)
    private Long id;

    @JsonView(Details.class)
    private String firstname;

    @JsonView(Details.class)
    private String lastname;

    @JsonView(Details.class)
    private Set<BookDto> books;
}
