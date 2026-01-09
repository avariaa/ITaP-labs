package lab7;
import java.util.Random;

public class ArraySumTwoThreads {
    
    public static void main(String[] args) throws InterruptedException {
        int arraySize = 200; 
        int[] array = new int[arraySize];
        Random random = new Random();
        
        System.out.print("Массив: ");
        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(10);
            System.out.print(array[i] + " ");
        }
        System.out.println();
        
        //Вычисление суммы в одном потоке
        long singleThreadSum = 0;
        for (int num : array) {
            singleThreadSum += num;
        }
        System.out.println("Однопоточная сумма: " + singleThreadSum);
        
        int mid = arraySize / 2;
        SumResult result = new SumResult();
        
        Thread thread1 = new Thread(new SumCalculator(array, 0, mid, result, 0, "Поток-1"));
        Thread thread2 = new Thread(new SumCalculator(array, mid, arraySize, result, 1, "Поток-2"));
        
        System.out.println("\nЗапускаем потоки...");
        
        thread1.start();
        thread2.start();
        
        thread1.join();
        thread2.join();
        
        //Cуммируем результаты в главном потоке
        long multiThreadSum = result.getPartialSum(0) + result.getPartialSum(1);
        
        System.out.println("\nРезультат:");
        System.out.println("Сумма от Поток-1: " + result.getPartialSum(0));
        System.out.println("Сумма от Поток-2: " + result.getPartialSum(1));
        System.out.println("Многопоточная сумма: " + multiThreadSum);
        System.out.println("Результаты совпадают: " + (singleThreadSum == multiThreadSum));
    }
    
    //Для хранения частичных сумм
    static class SumResult {
        private final long[] partialSums = new long[2];
        
        public synchronized void setPartialSum(int index, long value) {
            partialSums[index] = value;
        }
        
        public long getPartialSum(int index) {
            return partialSums[index];
        }
    }
    
    //Для вычисления суммы массива
    static class SumCalculator implements Runnable {
        private final int[] array;
        private final int start;
        private final int end;
        private final SumResult result;
        private final int resultIndex;
        private final String threadName;
        
        public SumCalculator(int[] array, int start, int end, 
                            SumResult result, int resultIndex, String threadName) {
            this.array = array;
            this.start = start;
            this.end = end;
            this.result = result;
            this.resultIndex = resultIndex;
            this.threadName = threadName;
        }
        
        @Override
        public void run() {
            long sum = 0;
            System.out.print(threadName + " вычисляет элементы " + start + "-" + (end-1) + ": ");
            for (int i = start; i < end; i++) {
                sum += array[i];
                System.out.print(array[i] + (i < end-1 ? " + " : " = "));
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(sum);
            result.setPartialSum(resultIndex, sum);
        }
    }
}