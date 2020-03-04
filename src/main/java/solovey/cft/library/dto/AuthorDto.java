package solovey.cft.library.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import solovey.cft.library.dto.groups.Details;

import java.util.Set;

@Getter
@Setter
public class AuthorDto {
    @JsonView(Details.class)
    private Long id;

    @JsonView(Details.class)
    private String firstName;

    @JsonView(Details.class)
    private String lastName;



    @JsonView(Details.class)
    private Set<BookDto> books;
}
