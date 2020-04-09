package ru.otus.homework.springbatch.config.batch;

import org.modelmapper.ModelMapper;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.springbatch.domain.mongo.MongoBook;

@Configuration
public class ProcessorConfiguration {
    @Bean
    @StepScope
    public ItemProcessor mapProcessor(ModelMapper modelMapper) {
        return item -> modelMapper.map(item, MongoBook.class);
    }
}
