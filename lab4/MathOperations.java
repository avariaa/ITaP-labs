package lab4;

public class MathOperations {
    
    public static double performOperation(double a, double b, String operation) {
        switch (operation) {
            case "+":
                return a + b;
                
            case "-":
                return a - b;
                
            case "*":
                return a * b;
                
            case "/":
                if (b == 0) {
                    throw new CustomUnsupportedOperationException("Деление на ноль недопустимо");
                }
                return a / b;
                
            default:
                throw new CustomUnsupportedOperationException(
                    "Операция '" + operation + "' не поддерживается. " +
                    "Доступные операции: +, -, *, /"
                );
        }
    }
}