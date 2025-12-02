package lab5;
import java.util.Scanner;
import java.util.regex.*;

public class FindAlpha {
    public static void main(String[] args) {
        System.out.println("Введите строку для замены:");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Pattern pattern = Pattern.compile("[a-z][A-Z]");
        Matcher matcher = pattern.matcher(line);
        String result = matcher.replaceAll("!$0!");
        System.out.println("Новая строка: " + result);
    }
}
