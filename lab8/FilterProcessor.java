package lab8;

import java.util.List;
import java.util.stream.Collectors;

public class FilterProcessor {

    @DataProcessor(name = "filterShortWords", description = "Фильтрация коротких слов", priority = 1)
    public List<String> filterShortWords(List<String> input) {
        return input.stream()
                .filter(s -> s.length() <= 3)
                .collect(Collectors.toList());
    }

    @DataProcessor(name = "filterNumeric", description = "Фильтрация слов с цифрами", priority = 2)
    public List<String> filterNumeric(List<String> input) {
        return input.stream()
                .filter(s -> s.matches(".*\\d.*"))
                .collect(Collectors.toList());
    }
}