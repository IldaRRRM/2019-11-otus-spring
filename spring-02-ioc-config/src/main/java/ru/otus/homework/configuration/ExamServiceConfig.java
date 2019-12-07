package ru.otus.homework.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource("classpath:application.properties")
public class ExamServiceConfig {

    @Value(value = "classpath:data.csv")
    private Resource resource;
    @Value(value = "${exam.pass.answers.count}")
    private Integer passExamCount;

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms
                = new ReloadableResourceBundleMessageSource();
        ms.setBasename("bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean
    public Resource resource() {
        return this.resource;
    }

    @Bean
    Integer passExamCount() {
        return passExamCount;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
