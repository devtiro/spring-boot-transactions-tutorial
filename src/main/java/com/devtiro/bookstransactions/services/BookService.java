package com.devtiro.bookstransactions.services;

import java.util.Optional;

import com.devtiro.bookstransactions.domain.Book;

public interface BookService {

    boolean isBookExists(Book book);

    Book save(Book book);

    Optional<Book> findById(String isbn);

    Iterable<Book> listBooks();

    void deleteBookById(String isbn);

}
