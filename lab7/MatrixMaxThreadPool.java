package lab7;

import java.util.Random;
import java.util.concurrent.*;

public class MatrixMaxThreadPool {
    public static void main(String[] args) throws InterruptedException {
        int rows = 10;
        int cols = 15;
        int[][] matrix = new int[rows][cols];
        Random random = new Random();
        
        //Заполнение матрицы
        System.out.println("Матрица " + rows + "x" + cols + ":");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(1000);
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        
        //Однопоточный максимум
        int singleThreadMax = Integer.MIN_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] > singleThreadMax) {
                    singleThreadMax = matrix[i][j];
                }
            }
        }
        System.out.println("Однопоточный максимум: " + singleThreadMax);
        
        //Пул потоков
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("\nИспользуем пул из " + numberOfThreads + " потоков");
        
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        int[] partialMaxValues = new int[numberOfThreads];
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        
        int rowsPerThread = rows / numberOfThreads;
        
        for (int i = 0; i < numberOfThreads; i++) {
            final int threadId = i;
            int startRow = i * rowsPerThread;
            int endRow = (i == numberOfThreads - 1) ? rows : (i + 1) * rowsPerThread;
            
            executor.execute(() -> {
                int maxInPart = Integer.MIN_VALUE;
                for (int row = startRow; row < endRow; row++) {
                    for (int col = 0; col < cols; col++) {
                        if (matrix[row][col] > maxInPart) {
                            maxInPart = matrix[row][col];
                        }
                    }
                }
                partialMaxValues[threadId] = maxInPart;
                System.out.println("Поток " + threadId + ": строки [" + startRow + ".." + (endRow-1) + 
                                 "], максимум = " + maxInPart);
                latch.countDown();
            });
        }
        
        //Ждем завершения всех потоков
        latch.await();
        
        //Находим общий максимум
        int multiThreadMax = Integer.MIN_VALUE;
        for (int i = 0; i < numberOfThreads; i++) {
            if (partialMaxValues[i] > multiThreadMax) {
                multiThreadMax = partialMaxValues[i];
            }
        }

        executor.shutdown();
        
        System.out.println("\nМногопоточный максимум: " + multiThreadMax);
        System.out.println("Результаты совпадают: " + (singleThreadMax == multiThreadMax));
        
        if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }
}