package org.example.structural.service;

import org.example.structural.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LibraryFacade {

    private final BookService bookService;

    @Autowired
    public LibraryFacade(BookService bookService) {
        this.bookService = bookService;
    }

    public Book addBook(Book book) {
        return bookService.addBook(book);
    }

    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    public Book getBookById(Long id) {
        return bookService.getBookById(id);
    }

    public Book updateBook(Long id, Book book) {
        return bookService.updateBook(id, book);
    }

    public void deleteBook(Long id) {
        bookService.deleteBook(id);
    }

    public List<String> getAllBookDescriptions() {
        return bookService.getAllBooks()
                .stream()
                .map(this::decorateBookDescription)
                .collect(Collectors.toList());
    }

    private String decorateBookDescription(Book book) {
        StringBuilder description = new StringBuilder(book.getDescription());

        if (book.getPrice() > 20.0) {
            description.append(" (Featured)");
        }

        if (book.getPrice() > 50.0) {
            description.append(" (Bestseller)");
        }

        return description.toString();
    }

public List<Book> findBooksByTitle(String title) {
        return bookService.findBooksByTitle(title);
    }

    public List<Book> findBooksByAuthor(String author) {
        return bookService.findBooksByAuthor(author);
    }
}
