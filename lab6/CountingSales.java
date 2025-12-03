package lab6;
import java.util.*;

public class CountingSales {
    private static Map<String, Integer> products = new TreeMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        while (choice != 5) {
            System.out.println("Учет продаж");
            System.out.println("1. Добавить товар");
            System.out.println("2. Вывести список проданных товаров");
            System.out.println("3. Вывести количество проданных товаров");
            System.out.println("4. Вывести наиболее популярный товар");
            System.out.println("5. Выход");
            System.out.println("Выберите дейтсвие:");

            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    displayProducts();
                    break;
                case 3:
                    calculateTotalSales();
                    break;
                case 4:
                    findMostPopularProduct();
                    break;
                case 5:
                    System.out.println("Программа завершена.");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
        scanner.close();
    }

    //Добавление
    public static void addProduct(Scanner scanner) {
        System.out.println("Введите название товара: ");
        String productName = scanner.nextLine();
        

        System.out.print("Введите количество: ");
        int quantity = scanner.nextInt();
        
        if (quantity <= 0) {
            System.out.println("Количество должно быть положительным числом");
            return;
        }

        products.put(productName, products.getOrDefault(productName, 0) + quantity);
        System.out.println("Товар " + productName + " добавлен в количестве " + quantity);
    }

    //Просмотр
    public static void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("Список товаров пуст");
            return;
        }
        
        System.out.println("Список проданных товаров:");

        for (Map.Entry<String, Integer> entry: products.entrySet()) {
            System.out.println(entry.getKey() + "---" + entry.getValue());
        }
    }

    //Подсчет суммы
    public static void calculateTotalSales() {
        if (products.isEmpty()) {
            System.out.println("Список товаров пуст");
            return;
        }
        
        int total = 0;
        for (int quantity : products.values()) {
            total += quantity;
        }
        
        System.out.println("Общая сумма продаж: " + total);
    }

    //Поиск наиболее популярного товара
    private static void findMostPopularProduct() {
        if (products.isEmpty()) {
            System.out.println("Список товаров пуст");
            return;
        }
        
        String mostPopular = null;
        int maxQuantity = 0;
        
        for (Map.Entry<String, Integer> entry : products.entrySet()) {
            if (entry.getValue() > maxQuantity) {
                maxQuantity = entry.getValue();
                mostPopular = entry.getKey();
            }
        }   
        System.out.println("Наиболее популярный товар: " + mostPopular);
        System.out.println("Количество продаж: " + maxQuantity);
    }
}
