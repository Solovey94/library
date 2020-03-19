package solovey.cft.library.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import solovey.cft.library.dto.LoanDto;
import solovey.cft.library.dto.SimpleBookLoanDto;
import solovey.cft.library.exception.NotFoundException;
import solovey.cft.library.service.LoanService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("loans")
@Api(value = "Loan REST Endpoint")
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

    @GetMapping("/id")
    public LoanDto findById(@RequestBody SimpleBookLoanDto loanDto) {
        Long id = loanDto.getId();
        return loanService.findLoanById(id);
    }

    @GetMapping("/noreturn")
    public List<LoanDto> findNoReturnedLoans() {
        return loanService.findNotReturnedLoans();
    }

    @GetMapping("/expired")
    public List<LoanDto> findExpiredLoan() {
        LocalDate date = LocalDate.now();
        List<LoanDto> loans = loanService.findExpiredLoansOrNull(date);
        if (loans != null) {
            return loans;
        }
        throw new NotFoundException("Not found expired rents");
    }

    @PutMapping
    public LoanDto updateLoan(@RequestBody SimpleBookLoanDto loanDto) {
        Long id = loanDto.getId();
        loanDto.setId(id);
        return loanService.updateLoan(loanDto);
    }

    @DeleteMapping
    public void deleteLoan(@RequestBody SimpleBookLoanDto loanDto) {
        Long id = loanDto.getId();
        loanService.deleteLoanById(id);
    }

}
