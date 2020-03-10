package solovey.cft.library.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AuthorDto {
    private Long id;

    private String firstName;

    private String lastName;

    private Set<BookDto> books;
}
