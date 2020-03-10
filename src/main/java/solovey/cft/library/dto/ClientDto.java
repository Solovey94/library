package solovey.cft.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {
    private Long id;

    private String firstName;

    private String lastName;

    private Long passport;

    private String email;
}
