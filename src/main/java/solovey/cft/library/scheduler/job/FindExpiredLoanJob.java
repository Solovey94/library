package solovey.cft.library.scheduler.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import solovey.cft.library.dto.BookDto;
import solovey.cft.library.dto.LoanDto;
import solovey.cft.library.service.LoanService;
import solovey.cft.library.service.MailService;

import java.time.LocalDate;
import java.util.List;

@Component
public class FindExpiredLoanJob{

    private final LoanService loanService;
    private final MailService mailService;

    @Autowired
    public FindExpiredLoanJob(LoanService loanService, MailService mailService) {
        this.loanService = loanService;
        this.mailService = mailService;
    }

    @Scheduled(cron = "${quartz.config.string}")
    public void execute() throws JobExecutionException {
        LocalDate currentDate = LocalDate.now();
        try {
            List<LoanDto> loans = loanService.findExpiredLoansOrNull(currentDate);
            if (loans != null) {
                for (LoanDto loan : loans) {
                    BookDto book = loan.getBook();
                    String email = loan.getClient().getEmail();
                    mailService.send(email, book);
                }
            }
        } catch (Exception exception) {
            throw new JobExecutionException(exception);
        }
    }
}
