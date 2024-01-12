package hr.algebra.bibliosphereapi.controller;

import hr.algebra.bibliosphereapi.models.Book;
import hr.algebra.bibliosphereapi.models.Comment;
import hr.algebra.bibliosphereapi.models.Roles;
import hr.algebra.bibliosphereapi.service.BookService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);

        // Check if the currently authenticated user has "ADMIN" authority
        boolean isAdmin = checkIfUserIsAdmin();
        model.addAttribute("isAdmin", isAdmin);

        return "book/list";
    }

    @GetMapping("/{id}")
    public String getBookDetails(@PathVariable Long id, Model model) {
        Optional<Book> book = bookService.getBookById(id);
        List<Comment> comments = bookService.getCommentsByBookId(id);
        Optional<Double> avgRating = bookService.getAvgRatingOfBook(id);

        book.ifPresent(value->model.addAttribute("book", value));
        model.addAttribute("comments", comments);
        if (avgRating.isPresent()) {
            model.addAttribute("avg_rating", avgRating.get());
        }
        else{
            model.addAttribute("avg_rating", 0);
        }

        return "book/details";
    }

    // Insert a book (GET)
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/add-book";
    }

    // Update a book (POST)
    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book addBook, RedirectAttributes redirectAttributes) {
        bookService.addBook(addBook);
        redirectAttributes.addFlashAttribute("message", "Book updated successfully");
        return "redirect:/books";
    }


    // Update a book (GET)
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Optional<Book> book = bookService.getBookById(id);
        book.ifPresent(value -> model.addAttribute("book", value));
        return "book/update-book";
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
        Optional<Book> book = bookService.getBookById(id);
        book.ifPresent(value -> model.addAttribute("book", value));
        return "book/delete-book";
    }

    // Delete a book (POST)
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        bookService.deleteBook(id);
        redirectAttributes.addFlashAttribute("message", "Book deleted successfully");
        return "redirect:/books";
    }

    private boolean checkIfUserIsAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(Roles.ADMIN.name()));
    }
}
