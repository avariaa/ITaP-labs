public class Program {
    public static void main(String[] args) {
    HashTable<String, Integer> table = new HashTable<>();
    
    // Добавление элементов
    table.put("apple", 10);
    table.put("banana", 20);
    table.put("orange", 30);
    table.put("grape", 40);
    table.put("lemon", 50);
    table.put("peach", 60);
    
    // Получение элементов
    System.out.println("apple: " + table.get("apple"));    
    System.out.println("banana: " + table.get("banana"));  
    System.out.println("grape: " + table.get("grape"));    
    
    // Структура таблицы
    System.out.println("\nСтруктура таблицы:");
    table.printTable();
    
    // Проверка размера и пустоты
    System.out.println("\nРазмер: " + table.size());
    System.out.println("Пуста: " + table.isEmpty());
    
    // Удаление
    Integer removed = table.remove("banana");
    System.out.println("\nУдален banana: " + removed);
    System.out.println("Размер после удаления: " + table.size());
    }
}

