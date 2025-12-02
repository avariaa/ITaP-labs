package lab5;
import java.util.Scanner;

public class CheckPass {
    
    public static void checkPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Пароль не может быть null");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Пароль слишком короткий: " + password.length() + " символов (требуется 8-16)");
        }
        if (password.length() > 16) {
            throw new IllegalArgumentException("Пароль слишком длинный: " + password.length() + " символов (требуется 8-16)");
        }
        if (!password.matches("[a-zA-Z0-9]*")) {
            throw new IllegalArgumentException("Пароль содержит недопустимые символы (разрешены только латинские буквы и цифры)");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Пароль должен содержать хотя бы одну заглавную букву");
        }
        if (!password.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("Пароль должен содержать хотя бы одну цифру");
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Введите пароль для проверки:");
        String password = scanner.nextLine();
        
        try {
            checkPassword(password);
            System.out.println("Пароль корректен");
            
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
            
        } finally {
            scanner.close();
        }
    }
}