package ru.otus.homework.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class CsvExamData {

    @CsvBindByName(column = "question")
    private String question;

    @CsvBindByName(column = "answer")
    private String answer;

}
