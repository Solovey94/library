package solovey.cft.library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import solovey.cft.library.dto.groups.Details;

import java.time.LocalDate;

@Getter
@Setter
public class LoanDto {
    @JsonView(Details.class)
    private Long id;

    @JsonView(Details.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate loanDate;

    @JsonView(Details.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate returnDate;

    @JsonView(Details.class)
    private String clientId;

    @JsonView(Details.class)
    private String title;
}
