package solovey.cft.library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LoanDto {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate loanDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate returnDate;

    private ClientDto client;

    private BookDto book;
}
