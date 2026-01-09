package lab8;

import java.util.List;
import java.util.stream.Collectors;

public class TransformProcessor {

    @DataProcessor(name = "toUpperCase", description = "Преобразование в верхний регистр", priority = 1)
    public List<String> toUpperCase(List<String> input) {
        return input.stream()
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());
    }

    @DataProcessor(name = "duplicateWords", description = "Удвоение каждого слова", priority = 2)
    public List<String> duplicateWords(List<String> input) {
        return input.stream()
                .map(s -> s + " " + s)
                .collect(Collectors.toList());
    }
}