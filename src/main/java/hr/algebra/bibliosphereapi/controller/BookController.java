package hr.algebra.bibliosphereapi.controller;

import hr.algebra.bibliosphereapi.models.Book;
import hr.algebra.bibliosphereapi.models.Comment;
import hr.algebra.bibliosphereapi.models.Rating;
import hr.algebra.bibliosphereapi.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

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
        Optional<Double> avgRating = bookService.getAvgRatingOfBook(id);

        model.addAttribute("book", book);
        model.addAttribute("comments", comments);
        model.addAttribute("avg_rating", avgRating);

        return "book/details";
    }


    // Update a book (GET)
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "update-book";
    }

    // Update a book (POST)
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute("book") Book updatedBook,
                             RedirectAttributes redirectAttributes) {
        updatedBook.setId(id);
        bookService.updateBook(updatedBook);
        redirectAttributes.addFlashAttribute("message", "Book updated successfully");
        return "redirect:/books";
    }

    // Delete a book (GET)
    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "delete-book";
    }

    // Delete a book (POST)
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        bookService.deleteBook(id);
        redirectAttributes.addFlashAttribute("message", "Book deleted successfully");
        return "redirect:/books";
    }
}
