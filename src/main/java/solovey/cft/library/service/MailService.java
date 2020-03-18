package solovey.cft.library.service;

import solovey.cft.library.dto.BookDto;

public interface MailService {
    void send(String clientMail, BookDto book);
}
