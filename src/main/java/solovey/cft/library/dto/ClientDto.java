package solovey.cft.library.dto;

import com.fasterxml.jackson.annotation.JsonView;
import solovey.cft.library.dto.groups.Details;

public class ClientDto {
    @JsonView(Details.class)
    private Long id;

    @JsonView(Details.class)
    private String firstName;

    @JsonView(Details.class)
    private String lastName;

    @JsonView(Details.class)
    private Long passport;

    @JsonView(Details.class)
    private String email;
}
