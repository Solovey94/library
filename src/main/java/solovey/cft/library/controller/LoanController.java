package solovey.cft.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import solovey.cft.library.dto.LoanDto;
import solovey.cft.library.dto.SimpleBookLoanDto;
import solovey.cft.library.exception.NotFoundException;
import solovey.cft.library.service.LoanService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }


    @PostMapping
    public LoanDto addLoan(@RequestBody SimpleBookLoanDto loanDto) {
        return loanService.add(loanDto);
    }

    @GetMapping
    public List<LoanDto> findAll() {
        return loanService.findAllLoans();
    }

    @GetMapping("/{id}")
    public LoanDto findById(@PathVariable Long id) {
        return loanService.findLoanById(id);
    }

    @GetMapping("/noreturn")
    public List<LoanDto> findNoReturnedRents() {
        return loanService.findNotReturnedLoans();
    }

    @GetMapping("/expired")
    public List<LoanDto> findExpiredRent() {
        LocalDate date = LocalDate.now();
        List<LoanDto> loans = loanService.findExpiredLoansOrNull(date);
        if (loans != null) {
            return loans;
        }
        throw new NotFoundException("Not found expired rents");
    }

    @PutMapping("/{id}")
    public LoanDto updateLoan(
            @PathVariable Long id,
            @Validated @RequestBody SimpleBookLoanDto loanDto
    ) {
        loanDto.setId(id);
        return loanService.updateLoan(loanDto);
    }

    @DeleteMapping("/{id}")
    public void deleteRent(@PathVariable Long id) {
        loanService.deleteLoanById(id);
    }

}
