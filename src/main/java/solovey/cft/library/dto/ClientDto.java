package solovey.cft.library.dto;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ClientDto {
    private Long id;

    private String firstName;

    private String lastName;

    private Long passport;

    private String email;
}
