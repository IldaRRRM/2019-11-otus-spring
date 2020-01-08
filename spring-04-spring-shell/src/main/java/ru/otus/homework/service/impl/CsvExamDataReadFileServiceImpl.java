package ru.otus.homework.service.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.CsvExamData;
import ru.otus.homework.service.ReadFileService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
@Getter
@Service
public class CsvExamDataReadFileServiceImpl implements ReadFileService {

    @Override
    public List<? extends CsvExamData> read(String pathToResource) throws IOException {
        return getCsvExamData(ClassLoader.getSystemResourceAsStream(pathToResource));
    }

    private List<? extends CsvExamData> getCsvExamData(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
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
