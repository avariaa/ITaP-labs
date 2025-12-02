package lab5;
import java.util.Scanner;
import java.util.regex.*;

public class WordFinder {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.println("Введите текст:");
            String text = scanner.nextLine();
            
            if (text.isEmpty()) {
                System.out.println("Ошибка: текст не может быть пустым");
                return;
            }
            
            System.out.println("Введите букву для поиска:");
            String searchLetter = scanner.nextLine();
            
            if (searchLetter.length() != 1) {
                System.out.println("Ошибка: введите только одну букву");
                return;
            }
            
            char letter = searchLetter.charAt(0);
            //проверка что это буква
            if (!Character.isLetter(letter)) {
                System.out.println("Ошибка: это не буква");
                return;
            }
            
            String regex = "[" + Character.toLowerCase(letter) + Character.toUpperCase(letter) + "][a-zA-Zа-яА-Я]*";
            
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);

            System.out.println("Найденные слова:");
            boolean found = false;
            
            while (matcher.find()) {
                String word = matcher.group();
                System.out.println("- " + word);
                found = true;
            }
            
            if (!found) {
                System.out.println("Слова на букву '" + searchLetter + "' не найдены");
            }
            
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}