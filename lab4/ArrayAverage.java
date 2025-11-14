package lab4;

public class ArrayAverage {
    public static void main(String[] args) {
        Object[] arr = {1, null, 3, "a", "test", null};
        double sum = 0;
        int count = 0;

        try {
            for (int i = 0; i < arr.length+1; i++) {
                try {
                    if (arr[i] instanceof Number) {
                        sum += ((Number) arr[i]).doubleValue();
                        count++;
                    } else if (arr[i] != null) {
                        //для строк
                        throw new IllegalArgumentException("Элемент не является числом: " + arr[i]);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }
            
            if (count > 0) {
                double average = sum / count;
                System.out.println("Среднее арифметическое = " + average);
            }
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка выхода за границы массива");
        }
    }
}