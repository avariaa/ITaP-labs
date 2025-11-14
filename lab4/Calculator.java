package lab4;

public class Calculator {
    public static void main(String[] args) {
        
        testOperation(10, 5, "+");
        testOperation(10, 5, "-");     
        testOperation(10, 5, "*");     
        testOperation(10, 5, "/");    
        testOperation(10, 0, "/");//деление на ноль
        testOperation(10, 5, "^");//неподдерживаемая операция
    }
    
    public static void testOperation(double a, double b, String operation) {
        System.out.println("\nПопытка выполнить: " + a + " " + operation + " " + b);
        
        try {
            double result = MathOperations.performOperation(a, b, operation);
            System.out.println("Результат: " + result);
            
        } catch (CustomUnsupportedOperationException e) {
            System.out.println("Перехвачено CustomUnsupportedOperationException: " + e.getMessage());
        }
    }
}