package com.devtiro.bookstransactions.repositories;

import org.springframework.data.repository.CrudRepository;

import com.devtiro.bookstransactions.domain.Book;

public interface BookRepository extends CrudRepository<Book, String>{
}
