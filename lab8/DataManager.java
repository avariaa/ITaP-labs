package lab8;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class DataManager {

    private final List<Object> processors = new ArrayList<>();
    private List<String> data = new ArrayList<>();
    private List<String> processedData = new ArrayList<>();

    public void registerDataProcessor(Object processor) {
        processors.add(processor);
    }

    public void loadData(String source) {
        try {
            data = Files.readAllLines(Paths.get(source));
            System.out.println("Данные загружены: " + data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processData() {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        
        //общее количество
        int totalMethods = 0;
        for (Object processor : processors) {
            for (Method method : processor.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(DataProcessor.class)) {
                    totalMethods++;
                }
            }
        }
        CountDownLatch latch = new CountDownLatch(totalMethods);
        
        List<List<String>> allResults = new ArrayList<>();
        
        for (Object processor : processors) { 
            for (Method method : processor.getClass().getDeclaredMethods()) { 
                if (method.isAnnotationPresent(DataProcessor.class)) {
                    
                    executor.submit(() -> {
                        try {
                            List<String> result = (List<String>) method.invoke(processor, data);
                            synchronized (allResults) {
                                allResults.add(result);
                            }
                            System.out.println("Метод " + method.getName() + " выполнен");
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            latch.countDown();
                        }
                    });
                }
            }
        }

        try {
            //ждём завершения всех задач
            latch.await();
            executor.shutdown();
            
            //объединяем все результаты
            processedData = allResults.stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
                    
            System.out.println("Обработанные данные: " + processedData);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void saveData(String destination) {
        try {
            Files.write(Paths.get(destination), processedData);
            System.out.println("Результат сохранён в " + destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}