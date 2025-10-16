public class ValidationUtils {
     public static String validateString(String value, String defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            System.out.println("Некорректная запись. Установлено значение по умолчанию: " + defaultValue);
            return defaultValue;
        } else {
            return value;
        }
    }
    
    public static int validateAge(int age, int defaultValue) {
        if (age < 0 || age > 50) {
            System.out.println("Некорректная запись. Установлено значение по умолчанию: " + defaultValue);
            return defaultValue;
        } else {
            return age;
        }
    }

    public static int validateSize(int size, int defaultValue) {
        if (size < 1 || size > 1000000) {
            System.out.println("Некорректная запись. Установлено значение по умолчанию: " + defaultValue);
            return defaultValue;
        } else {
            return size;
        }
    }
}