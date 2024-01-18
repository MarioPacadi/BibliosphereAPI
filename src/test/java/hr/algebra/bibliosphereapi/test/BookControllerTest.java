package hr.algebra.bibliosphereapi.test;

import hr.algebra.bibliosphereapi.controller.BookController;
import hr.algebra.bibliosphereapi.models.Book;
import hr.algebra.bibliosphereapi.service.BookService;
import hr.algebra.bibliosphereapi.test.security.TestSecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@Import(TestSecurityConfig.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void testGetAllBooks() throws Exception {
        // Mock the behavior of your service if needed
        when(bookService.getAllBooks()).thenReturn(Arrays.asList(new Book(), new Book()));

        // Perform the controller method, assuming it requires a valid JWT token
        mockMvc.perform(get("/books")
                        .header("Authorization", "Bearer mockJwtToken"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("books"))
                .andExpect(view().name("book/list"));

        // Verify any interactions if needed
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    public void testShowAddForm() throws Exception {
        mockMvc.perform(get("/books/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/add-book"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    public void testAddBook() throws Exception {
        // Mock the behavior of the service
        when(bookService.addBook(any())).thenReturn(Optional.of(new Book()));

        // Perform the controller method
        mockMvc.perform(post("/books/add")
                        .param("title", "Sample Title")
                        .param("author", "Sample Author"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attributeExists("message"));
    }

    @Test
    public void testShowUpdateForm() throws Exception {
        // Mock the behavior of the service
        when(bookService.getBookById(anyLong())).thenReturn(Optional.of(new Book()));

        // Perform the controller method
        mockMvc.perform(get("/books/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/update-book"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        // Mock the behavior of the service
        doNothing().when(bookService).updateBook(any());

        // Perform the controller method
        mockMvc.perform(post("/books/update/1")
                        .param("title", "Updated Title")
                        .param("author", "Updated Author"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attributeExists("message"));
    }

    @Test
    public void testShowDeleteForm() throws Exception {
        // Mock the behavior of the service
        when(bookService.getBookById(anyLong())).thenReturn(Optional.of(new Book()));

        // Perform the controller method
        mockMvc.perform(get("/books/delete/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/delete-book"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        // Mock the behavior of the service
        doNothing().when(bookService).deleteBook(anyLong());

        // Perform the controller method
        mockMvc.perform(post("/books/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attributeExists("message"));
    }

    // Add tests for other methods in BookController
}
