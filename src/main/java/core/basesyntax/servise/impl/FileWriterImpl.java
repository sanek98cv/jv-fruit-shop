package core.basesyntax.servise.impl;

import core.basesyntax.model.Fruit;
import core.basesyntax.servise.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileWriterImpl implements FileWriter {
    private static final String HEADER = "fruit, quantity";

    @Override
    public void createReport(Map<Fruit, Integer> map, String filePath) {
        Path file = Path.of(filePath);
        List<String> report = map.entrySet().stream()
                .map(e -> e.getKey() + "," + e.getValue())
                .collect(Collectors.toList());
        report.add(0, HEADER);
        try {
            Files.write(file, report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
