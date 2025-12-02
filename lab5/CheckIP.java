package lab5;
import java.util.Scanner;

public class CheckIP {
    public static boolean isValidIP(String ip) {
        try {
            return ip.matches("\\s*((25[0-5]|2[0-4][0-9]|[01][0-9][0-9])\\.){3}" + "(25[0-5]|2[0-4][0-9]|[01][0-9][0-9])\\s*");
        } catch (Exception e) {
            System.out.println("Ошибка при проверке IP: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите IP-адрес: ");
            String ip = scanner.nextLine();

            if (ip.isEmpty()) {
                throw new IllegalArgumentException("Вы ввели пустую строку");
            }
            if (isValidIP(ip)) {
                System.out.println("IP-адрес корректен");
            } else {
                System.out.println("IP-адрес некорректен");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

