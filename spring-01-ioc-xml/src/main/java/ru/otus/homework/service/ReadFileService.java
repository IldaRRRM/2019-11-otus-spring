package ru.otus.homework.service;


import ru.otus.homework.domain.CsvExamData;

import java.io.IOException;
import java.util.List;

public interface ReadFileService {

    List<? extends CsvExamData> readFromInnerFields() throws IOException;

    List<? extends CsvExamData> read(String pathToResource) throws IOException;
}
