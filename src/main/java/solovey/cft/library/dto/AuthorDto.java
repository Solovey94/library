package solovey.cft.library.dto;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AuthorDto {
    private Long id;

    private String firstName;

    private String lastName;
}
