package com.devtiro.bookstransactions.services.impl;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.devtiro.bookstransactions.domain.Author;
import com.devtiro.bookstransactions.domain.Book;
import com.devtiro.bookstransactions.repositories.BookRepository;
import com.devtiro.bookstransactions.repositories.AuthorRepository;
import com.devtiro.bookstransactions.services.BookService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    public BookServiceImpl(
        final BookRepository bookRepository,
        final AuthorRepository authorRepository) {
        this.bookRepository  = bookRepository;
        this.authorRepository = authorRepository;
    }

    /**
     * WARNING - This is a very VERY contrived example.
     *
     * There are easier ways to code this logic when you're not
     * demonstrating transactions -- take a look as Hibernate cascades!
     */
    @Transactional
    @Override
    public Book save(final Book book) {
        if(null == book.getAuthor()) {
            throw new RuntimeException("Author must be provided.");
        }
        final Author bookAuthor = book.getAuthor();

        // 1. Save book without author
        book.setAuthor(null);
        final Book savedBook = bookRepository.save(book);

        // 2. Create/Retrieve the Author
        if(null == bookAuthor.getId()) {
            final Author author = authorRepository.save(bookAuthor);
            savedBook.setAuthor(author);

        } else {
            final Author author = authorRepository.findById(bookAuthor.getId())
            .orElseThrow(() -> new RuntimeException("Author not found"));
            savedBook.setAuthor(author);
        }

        // 3. Save the Book with the Author and return
        return bookRepository.save(savedBook);
    }

    @Override
    public Optional<Book> findById(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public Iterable<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public boolean isBookExists(Book book) {
        return bookRepository.existsById(book.getIsbn());
    }

    @Override
    public void deleteBookById(String isbn) {
        try {
            bookRepository.deleteById(isbn);

        } catch(final EmptyResultDataAccessException ex) {
            log.debug("Attempted to delete non-existing book", ex);
        }
    }
}
