package ru.rtu_mirea.practice_6;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class BookController {
    private final BookRepo bookRepo;

    public BookController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @QueryMapping
    public Iterable<Book> books() {
        return bookRepo.findAll();
    }

    @QueryMapping
    public Book book(@Argument String id) {
        return bookRepo.findById(id).orElse(null);
    }

    @MutationMapping
    public Book createBook(@Argument String title, @Argument String genre, @Argument String author_name) {
        Book book = new Book(UUID.randomUUID().toString(), title, genre, author_name);
        bookRepo.save(book);
        return book;
    }

    @MutationMapping
    public Book updateBook(@Argument String id, @Argument String name, @Argument String genre, @Argument String author_name) {
        Book book = bookRepo.findById(id).orElse(null);
        if (book != null) {
            book.setTitle(name);
            book.setGenre(genre);
            book.setAuthor_name(author_name);
            bookRepo.save(book);
        }
        return book;
    }

    @MutationMapping
    public boolean deleteBook(@Argument String id) {
        bookRepo.deleteById(id);
        return true;
    }
}
