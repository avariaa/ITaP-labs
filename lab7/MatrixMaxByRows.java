package lab7;

import java.util.Random;

public class MatrixMaxByRows {
    
    public static void main(String[] args) throws InterruptedException {
        int rows = 10;
        int cols = 15;
        int[][] matrix = new int[rows][cols];
        Random random = new Random();
        
        System.out.println("Матрица " + rows + "x" + cols + ":");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(1000);
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        
        //Поиск максимума в одном потоке
        int singleThreadMax = Integer.MIN_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] > singleThreadMax) {
                    singleThreadMax = matrix[i][j];
                }
            }
        }
        System.out.println("Однопоточный максимум: " + singleThreadMax);
        
        //Создаем потоки для каждой строки
        Thread[] threads = new Thread[rows];
        int[] rowMaxValues = new int[rows];
        
        //Создаем и запускаем потоки
        for (int i = 0; i < rows; i++) {
            final int rowIndex = i;
            threads[i] = new Thread(() -> {
                int rowMax = Integer.MIN_VALUE;
                for (int j = 0; j < cols; j++) {
                    if (matrix[rowIndex][j] > rowMax) {
                        rowMax = matrix[rowIndex][j];
                    }
                }
                rowMaxValues[rowIndex] = rowMax;
                System.out.println("Строка " + rowIndex + ": максимум = " + rowMax);
            });
            threads[i].start();
        }
        
        //Ждем завершения всех потоков
        for (Thread thread : threads) {
            thread.join();
        }
        
        //Находим общий максимум в главном потоке
        int multiThreadMax = Integer.MIN_VALUE;
        for (int i = 0; i < rows; i++) {
            if (rowMaxValues[i] > multiThreadMax) {
                multiThreadMax = rowMaxValues[i];
            }
        }
        
        System.out.println("\nМногопоточный максимум (по строкам): " + multiThreadMax);
        System.out.println("Результаты совпадают: " + (singleThreadMax == multiThreadMax));
        
        //Находим строку с максимальным элементом
        for (int i = 0; i < rows; i++) {
            if (rowMaxValues[i] == multiThreadMax) {
                System.out.println("Максимум находится в строке: " + i);
            }
        }
    }
}