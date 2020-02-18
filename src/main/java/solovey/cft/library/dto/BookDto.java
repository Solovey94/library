package solovey.cft.library.dto;

import com.fasterxml.jackson.annotation.JsonView;
import solovey.cft.library.model.Author;

import java.util.Set;

public class BookDto {
    @JsonView(Details.class)
    private Long id;

    @JsonView(Details.class)
    private String title;

    @JsonView(Details.class)
    private String publisher;

    @JsonView(Details.class)
    private Integer totalQuantity;

    @JsonView(Details.class)
    private Integer currentQuantity;

    @JsonView(Details.class)
    private Set<Author> authors;
}
