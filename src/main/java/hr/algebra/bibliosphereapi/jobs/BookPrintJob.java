package hr.algebra.bibliosphereapi.jobs;

import hr.algebra.bibliosphereapi.models.Book;
import hr.algebra.bibliosphereapi.repository.BookRepository;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.HashSet;
import java.util.Set;

public class BookPrintJob extends QuartzJobBean {

    private final Logger log = LoggerFactory.getLogger(BookPrintJob.class);

    private final BookRepository bookRepository;

    public BookPrintJob(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        final Set<Book> bookSet = new HashSet<>(bookRepository.getAllBooks());

        if(bookSet.stream().anyMatch(this::isBookAvailable)){
            log.info("The currently documented Books");
            log.info("------------------------------");
            bookSet.stream()
                    .filter(this::isBookAvailable)
                    .forEach(
                            Book -> log.info(Book.toString())
                    );
            log.info("------------------------------");
        } else {
            log.info("There are currently no documented Books");
        }

    }

    private boolean isBookAvailable(Book book) {
        return !book.getTitle().isEmpty();
    }
}
