package ru.otus.homework.springbatch.config.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class JobConfiguration {

    private static final String IMPORT_DATA_JOB_NAME = "importDataFromJpaToMongoJob";

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job importBooksData(Step importDataFromJpaToMongo) {
        return jobBuilderFactory
                .get(IMPORT_DATA_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .flow(importDataFromJpaToMongo)
                .end()
                .listener(jobExecutionListener())
                .build();
    }

    @Bean
    public JobExecutionListener jobExecutionListener() {
        return new JobExecutionListener() {
            @Override
            public void beforeJob(JobExecution jobExecution) {
                log.info("Начало выполнения джобы");
            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                log.info("Окончание выполнение джобы");
            }
        };
    }


}