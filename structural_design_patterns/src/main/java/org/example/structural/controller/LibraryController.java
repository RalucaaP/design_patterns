package org.example.structural.controller;

import io.swagger.annotations.ApiParam;
import org.example.structural.dto.BookDto;
import org.example.structural.entity.Book;
import org.example.structural.service.LibraryFacade;
import org.example.structural.utils.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class LibraryController {

    private final LibraryFacade libraryFacade;

    @Autowired
    public LibraryController(LibraryFacade libraryFacade) {
        this.libraryFacade = libraryFacade;
    }

    @Operation(summary = "Retrieve all books")
    @GetMapping
    public List<BookDto> getAllBooks() {
        return libraryFacade.getAllBooks()
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get a book by ID")
    @GetMapping("/{id}")
    public BookDto getBookById(@ApiParam("ID of the book to retrieve") @PathVariable Long id) {
        Book book = libraryFacade.getBookById(id);
        return BookMapper.toDTO(book);
    }

    @Operation(summary = "Add a new book")
    @PostMapping
    public BookDto addBook(@ApiParam("BookDto to be added") @RequestBody BookDto bookDto) {
        Book book = BookMapper.toEntity(bookDto);
        Book savedBook = libraryFacade.addBook(book);
        return BookMapper.toDTO(savedBook);
    }

    @Operation(summary = "Update an existing book")
    @PutMapping("/{id}")
    public BookDto updateBook(
            @ApiParam("ID of the book to update") @PathVariable Long id,
            @ApiParam("Updated BookDto") @RequestBody BookDto updatedBookDto) {
        Book updatedBook = BookMapper.toEntity(updatedBookDto);
        Book savedBook = libraryFacade.updateBook(id, updatedBook);
        return BookMapper.toDTO(savedBook);
    }

    @Operation(summary = "Delete a book by ID")
    @DeleteMapping("/{id}")
    public void deleteBook(@ApiParam("ID of the book to delete") @PathVariable Long id) {
        libraryFacade.deleteBook(id);
    }

    @GetMapping("/descriptions")
    public List<String> getAllBookDescriptions() {
        return libraryFacade.getAllBookDescriptions();
    }

    @Operation(summary = "Search books by title")
    @GetMapping("/search/title")
    public List<BookDto> findBooksByTitle(@RequestParam String title) {
        return libraryFacade.findBooksByTitle(title)
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Search books by author")
    @GetMapping("/search/author")
    public List<BookDto> findBooksByAuthor(@RequestParam String author) {
        return libraryFacade.findBooksByAuthor(author)
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }
}
