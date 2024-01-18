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
    }

    //    @Secured("ADMIN")
//    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Book> save(@Valid @RequestBody final BookUpdateCommand bookUpdateCommand){
        Book command = new Book(bookUpdateCommand);
        Optional<Book> book = bookService.addBook(command);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

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
    }

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
