package lab7;

import java.util.Random;
import java.util.concurrent.*;

public class ArraySumThreadPool {
    
    public static void main(String[] args) throws InterruptedException {
        int arraySize = 200;
        int[] array = new int[arraySize];
        Random random = new Random();

        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(10);
        }
        
        //Сумма в одном потоке
        long singleThreadSum = 0;
        for (int num : array) {
            singleThreadSum += num;
        }
        System.out.println("Однопоточная сумма: " + singleThreadSum);
        
        //Количество потоков = количество ядер процессора
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Используем пул из " + numberOfThreads + " потоков");
        
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        
        //Массив для хранения частичных сумм
        long[] partialSums = new long[numberOfThreads];
        //Счетчик для синхронизации работы потоков
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        int chunkSize = arraySize / numberOfThreads;

        for (int i = 0; i < numberOfThreads; i++) {
            final int threadId = i;
            int start = i * chunkSize;
            int end = (i == numberOfThreads - 1) ? arraySize : (i + 1) * chunkSize;
            
            executor.execute(() -> {
                long sum = 0;
                for (int j = start; j < end; j++) {
                    sum += array[j];
                }
                partialSums[threadId] = sum;
                System.out.println("Поток " + threadId + " вычислил сумму элементов [" + 
                                 start + ".." + (end-1) + "]: " + sum);
                latch.countDown();
            });
        }
        
        latch.await();
        
        //Суммируем результаты в главном потоке
        long multiThreadSum = 0;
        for (int i = 0; i < numberOfThreads; i++) {
            multiThreadSum += partialSums[i];
            System.out.println("Часть " + i + ": сумма = " + partialSums[i]);
        }
        
        executor.shutdown();
        
        System.out.println("\nМногопоточная сумма: " + multiThreadSum);
        System.out.println("Результаты совпадают: " + (singleThreadSum == multiThreadSum));
        if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }
}