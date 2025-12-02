package lab5;
import java.util.Scanner;
import java.util.regex.*;

public class NumberFinder {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.println("Введите текст для поиска чисел:");
            String text = scanner.nextLine();
            
            if (text == null || text.trim().isEmpty()) {
                System.out.println("Ошибка: текст не может быть пустым");
                return;
            }
            
            Pattern pattern = Pattern.compile("[-]?\\d+(\\.\\d+)?");
            Matcher matcher = pattern.matcher(text);
            
            System.out.println("Найденные числа:");
            boolean found = false;
            
            while (matcher.find()) {
                String number = matcher.group();
                System.out.println(number);
                found = true;
            }
            
            if (!found) {
                System.out.println("Числа не найдены");
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}