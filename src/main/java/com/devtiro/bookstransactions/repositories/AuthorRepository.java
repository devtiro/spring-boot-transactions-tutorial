package com.devtiro.bookstransactions.repositories;

import org.springframework.data.repository.CrudRepository;

import com.devtiro.bookstransactions.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long>{

}
