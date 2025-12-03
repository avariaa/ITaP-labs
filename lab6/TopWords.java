package lab6;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TopWords {
    public static void main(String[] args) {
        String filePath = "lab6\\text.txt";
        
        File file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
            return;
        }
        
        Map<String, Integer> wordCount = new HashMap<>();
        
        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase()
                .replaceAll("[^a-zA-Zа-яА-Я]", ""); //удаляем все знаки кроме букв
            
            if (!word.isEmpty()) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1); 
            }
        }
        
        scanner.close();
        
        //список из элементов map
        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordCount.entrySet());
        //перезаписываем compare 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, 
                              Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        
        System.out.println("Топ-10 самых частых слов:");
        int count = 0;
        for (Map.Entry<String, Integer> entry : list) {
            count++;
            if (count > 10) break;
            System.out.println(count + ". " + entry.getKey() + ": " + entry.getValue() + " раз(а)");
        }
    }
}