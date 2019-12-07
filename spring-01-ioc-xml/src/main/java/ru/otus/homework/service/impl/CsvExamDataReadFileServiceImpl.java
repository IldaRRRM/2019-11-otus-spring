package ru.otus.homework.service.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.io.Resource;
import ru.otus.homework.domain.CsvExamData;
import ru.otus.homework.service.ReadFileService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
@Getter
public class CsvExamDataReadFileServiceImpl implements ReadFileService {

    private final Resource csvFile;

    @Override
    public List<CsvExamData> read() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()))) {
            Iterator<CsvExamData> examDataIterator = new CsvToBeanBuilder(reader)
                    .withType(CsvExamData.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build().iterator();
            List<CsvExamData> allQuestions = new ArrayList<>();
            while (examDataIterator.hasNext()) {
                CsvExamData examData = examDataIterator.next();
                allQuestions.add(examData);
            }
            return allQuestions;
        }

    }
}
