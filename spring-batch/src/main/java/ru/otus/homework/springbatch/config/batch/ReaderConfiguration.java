package ru.otus.homework.springbatch.config.batch;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.springbatch.domain.jpa.Book;
import ru.otus.homework.springbatch.repositories.jpa.BookRepository;

import java.util.HashMap;

@Configuration
public class ReaderConfiguration {

    @StepScope
    @Bean
    public RepositoryItemReader<Book> jpaRepositoryReader(BookRepository bookRepository) {
        return new RepositoryItemReaderBuilder<Book>()
                .name("bookItemReader")
                .repository(bookRepository)
                .methodName("findAll")
                .sorts(new HashMap<>())
                .build();
    }
}
