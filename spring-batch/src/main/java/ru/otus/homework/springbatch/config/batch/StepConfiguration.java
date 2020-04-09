package ru.otus.homework.springbatch.config.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class StepConfiguration {

    private final StepBuilderFactory stepBuilderFactory;
    @Value("${app.batch.step.chunk-size}")
    private Integer chunkSize;

    @Bean
    public Step importDataFromJpaToMongo(RepositoryItemReader jpaReader, RepositoryItemWriter mongoWriter, ItemProcessor mapperProcessor) {
        return stepBuilderFactory.get("importDataFromJpaToMongo")
                .chunk(chunkSize)
                .reader(jpaReader)
                .processor(mapperProcessor)
                .writer(mongoWriter)
                .listener(itemReadListener())
                .listener(itemProcessListener())
                .listener(itemWriteListener())
                .build();
    }

    @Bean
    public ItemReadListener itemReadListener() {
        return new ItemReadListener() {
            @Override
            public void beforeRead() {
                log.info("Начало чтения из Jpa репозитория");
            }

            @Override
            public void afterRead(Object o) {
                log.info("Окончание чтения из Jpa репозитория");

            }

            @Override
            public void onReadError(Exception e) {
                log.error("Ошибка при чтении из источника" + e.getMessage(), e);
            }
        };
    }

    @Bean
    public ItemProcessListener itemProcessListener() {
        return new ItemProcessListener() {

            @Override
            public void beforeProcess(Object o) {
                log.info("Начало работы маппера");
            }

            @Override
            public void afterProcess(Object o, Object o2) {
                log.info("Маппер успешно закончил преобразование");
            }

            @Override
            public void onProcessError(Object o, Exception e) {
                log.error(e.getMessage(), e);
            }
        };
    }

    @Bean
    public ItemWriteListener itemWriteListener() {
        return new ItemWriteListener() {
            @Override
            public void beforeWrite(List list) {
                log.info("Начало записи");
            }

            @Override
            public void afterWrite(List list) {
                log.info("Конец записи");
            }

            @Override
            public void onWriteError(Exception e, List list) {
                log.error(e.getMessage(), e);
            }
        };
    }
}
