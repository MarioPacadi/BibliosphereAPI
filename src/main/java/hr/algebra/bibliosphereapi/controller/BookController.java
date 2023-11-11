package hr.algebra.bibliosphereapi.controller;

import hr.algebra.bibliosphereapi.models.Book;
import hr.algebra.bibliosphereapi.models.Comment;
import hr.algebra.bibliosphereapi.models.Rating;
import hr.algebra.bibliosphereapi.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/books/{id}")
    public String getBookDetails(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        List<Comment> comments = bookService.getCommentsByBookId(id);
        List<Rating> ratings = bookService.getRatingsByBookId(id);

        model.addAttribute("book", book);
        model.addAttribute("comments", comments);
        model.addAttribute("ratings", ratings);

        return "book/details";
    }
}
