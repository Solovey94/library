package solovey.cft.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import solovey.cft.library.dto.LoanDto;
import solovey.cft.library.dto.SimpleBookLoanDto;
import solovey.cft.library.exception.NotFoundException;
import solovey.cft.library.service.LoanService;

import java.time.LocalDate;
import java.util.List;

import static solovey.cft.library.log.message.ControllerMessages.*;

@Slf4j
@RestController
@RequestMapping("loans")
@Api(value = "Loan REST Endpoint")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @ApiOperation(value = "Return new loan")
    @PostMapping
    public LoanDto addLoan(@RequestBody SimpleBookLoanDto loanDto) {
        log.info(LOG_ADD_NEW, SimpleBookLoanDto.class.toString(), loanDto.toString());
        return loanService.add(loanDto);
    }

    @ApiOperation(value = "Return update loan")
    @PutMapping
    public LoanDto updateLoan(@RequestBody SimpleBookLoanDto loanDto) {
        Long id = loanDto.getId();
        loanDto.setId(id);
        log.info(LOG_UPDATE, SimpleBookLoanDto.class.toString(), loanDto.toString());
        return loanService.updateLoan(loanDto);
    }

    @ApiOperation(value = "Return all loans")
    @GetMapping
    public List<LoanDto> findAllLoans() {
        log.info(LOG_GET_ALL, LoanDto.class.toString());
        return loanService.findAllLoans();
    }

    @ApiOperation(value = "Return loan by loan_id")
    @GetMapping("/{id}")
    public LoanDto findById(@PathVariable Long id) {
        LoanDto loanDto = loanService.findLoanById(id);
        log.info(LOG_GET, SimpleBookLoanDto.class.toString(), loanDto.toString());
        return loanService.findLoanById(id);
    }

    @ApiOperation(value = "Return all loans not returned")
    @GetMapping("/noreturn")
    public List<LoanDto> findNoReturnedLoans() {
        log.info(LOG_GET_ALL, LoanDto.class.toString());
        return loanService.findNotReturnedLoans();
    }

    @ApiOperation(value = "Return all loans are expired")
    @GetMapping("/expired")
    public List<LoanDto> findExpiredLoan() {
        LocalDate date = LocalDate.now();
        List<LoanDto> loans = loanService.findExpiredLoansOrNull(date);
        if (loans != null) {
            log.info(LOG_GET_ALL, LoanDto.class.toString());
            return loans;
        }
        throw new NotFoundException("Not found expired rents");
    }

    @ApiOperation(value = "Delete loan by loan_id")
    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Long id) {
        loanService.deleteLoanById(id);
        log.info(LOG_DELETE_BY_ID, SimpleBookLoanDto.class.toString(), id);
    }

}
