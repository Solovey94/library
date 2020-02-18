package solovey.cft.library.dto;

import com.fasterxml.jackson.annotation.JsonView;

public class ClientDto {
    @JsonView(Details.class)
    private Long id;

    @JsonView(Details.class)
    private String firstname;

    @JsonView(Details.class)
    private String lastname;

    @JsonView(Details.class)
    private Long passport;

    @JsonView(Details.class)
    private String email;
}
