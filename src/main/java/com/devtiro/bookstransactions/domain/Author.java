package com.devtiro.bookstransactions.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="authors")
public class Author {

    @Id
    @SequenceGenerator(name="authors_generator", sequenceName="authors_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authors_generator")
    private Long id;

    /**
     * Must be less than 20 chars!
     * But we're not going to enforce this with validation.
     * Why? Because it's a contrived example!
     */
    private String name;

    private Integer age;
}
