package hr.algebra.bibliosphereapi.controller.rest;

import hr.algebra.bibliosphereapi.models.Book;
import hr.algebra.bibliosphereapi.payload.request.BookUpdateCommand;
import hr.algebra.bibliosphereapi.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/book")
//@CrossOrigin(origins = "http://localhost:8082")
public class BookApiController {

    private final BookService bookService;

    public BookApiController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getBookDetails(@PathVariable Long id) {
        {
            try{
                return new ResponseEntity<Book>(bookService.getBookById(id).get(),HttpStatus.OK);
            }
            catch (Exception e){
                return ResponseEntity.notFound().build();
            }
        }
//        return bookService.getBookById(id)
//                .orElseThrow(
//                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book was not found by that id")
//                );
    }

    //    @Secured("ADMIN")
//    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Book> save(@Valid @RequestBody final Book command){
//        jmsTemplate.convertAndSend(
//                "Saving the Dog "+ command.getBreedName() + " to the database.");
        Optional<Book> book = bookService.addBook(command);
        if (book.isPresent())
            new ResponseEntity<Book>(book.get(),HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
//        return new ResponseEntity<Book>(book.get(),HttpStatus.OK);
//        return bookService.addBook(command)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Book with the same title already exists"));
        return ResponseEntity.badRequest().build();
    }

    //    @Secured("ADMIN")
    @PutMapping("{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @Valid @RequestBody final BookUpdateCommand bookUpdateCommand){
        Book book = new Book(id,bookUpdateCommand);
        try{
            bookService.updateBook(book);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
//        return bookService.updateBook(book)
//                .orElseThrow(
//                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dog was not found by that breedName")
//                );
    }

//    @Secured("ADMIN")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id)
    {
        try{
            bookService.deleteBook(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
