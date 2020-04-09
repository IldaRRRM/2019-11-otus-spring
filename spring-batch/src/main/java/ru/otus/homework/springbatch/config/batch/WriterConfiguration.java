package ru.otus.homework.springbatch.config.batch;

import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.springbatch.domain.mongo.MongoBook;
import ru.otus.homework.springbatch.repositories.mongo.MongoBookRepository;

@Configuration
public class WriterConfiguration {

    @Bean
    public RepositoryItemWriter<MongoBook> mongoRepositoryWriter(MongoBookRepository mongoBookRepository) {
        return new RepositoryItemWriterBuilder<MongoBook>()
                .repository(mongoBookRepository)
                .methodName("insert")
                .build();

    }
}
