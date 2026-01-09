package lab7;

import java.util.*;
import java.util.concurrent.*;

public class WarehouseForkJoin {
    
    static class Product { 
        int id, weight; 
        Product(int id, int weight) { 
            this.id = id; 
            this.weight = weight; 
        } 
    }
    
    static class Loader {
        int id;
        List<Product> basket = new ArrayList<>();
        int currentWeight = 0;
        
        Loader(int id) { 
            this.id = id; 
        }
        
        public synchronized void take(Product p) {
            basket.add(p);
            currentWeight += p.weight;
        }

        public synchronized void deliver() {
            if (basket.isEmpty()) return;
            System.out.println("Грузчик " + id + " везет " + basket.size() + " товаров (" + currentWeight + "кг)");
            basket.clear();
            currentWeight = 0;
        }

        public synchronized int getCurrentWeight() {
            return currentWeight;
        }

        public synchronized List<Product> getBasket() {
            return new ArrayList<>(basket);
        }
    }

    static class TotalWeightManager {
        private final Loader[] loaders;
        private static final int MAX_TOTAL_WEIGHT = 150;
        TotalWeightManager(Loader[] loaders) {
            this.loaders = loaders;
        }
        //общий вес
        public synchronized int getTotalWeight() {
            int total = 0;
            for (Loader loader : loaders) {
                total += loader.getCurrentWeight();
            }
            return total;
        }
        //проверка лимита
        public synchronized boolean canAddProduct(Product p) {
            int totalWeight = getTotalWeight();
            return totalWeight + p.weight <= MAX_TOTAL_WEIGHT;
        }
        
        public synchronized boolean addProductToLoader(Product p, int loaderId) {
            int totalWeight = getTotalWeight();
            if (totalWeight + p.weight > MAX_TOTAL_WEIGHT) {
                return false;
            }
            
            //добавляется к грузчику по ID
            for (Loader loader : loaders) {
                if (loader.id == loaderId) {
                    loader.take(p);
                    return true;
                }
            }
            return false;
        }
        
        //отправка всех, очистка всех корзин
        public synchronized void deliverAll() {
            System.out.println("Отправка всех грузчиков");
            for (Loader loader : loaders) {
                loader.deliver();
            }
        }
        
        //грузчик с минимальной загрузкой
        public synchronized int findLoaderWithMinWeight() {
            int minWeight = Integer.MAX_VALUE;
            int loaderId = 1;
            for (Loader loader : loaders) {
                if (loader.getCurrentWeight() < minWeight) {
                    minWeight = loader.getCurrentWeight();
                    loaderId = loader.id;
                }
            }
            return loaderId;
        }
        
        public synchronized boolean addToBestLoader(Product p) {
            if (!canAddProduct(p)) {
                return false;
            }
            int bestLoaderId = findLoaderWithMinWeight();
            return addProductToLoader(p, bestLoaderId);
        }
        //статус всех грузчиков
        public synchronized void printStatus() {
            for (Loader loader : loaders) {
                System.out.println("Грузчик " + loader.id + " везет " + loader.getCurrentWeight() + "кг " + loader.getBasket().size() + " товаров");
            }
            System.out.println("Общий вес: " + getTotalWeight() + "/" + MAX_TOTAL_WEIGHT + "кг");
        }
    }
    
    static class MoveTask extends RecursiveAction {
        private final List<Product> products;
        private final int start, end;
        private final TotalWeightManager weightManager;
        private static final int BATCH_SIZE = 5;
        
        MoveTask(List<Product> products, int start, int end, TotalWeightManager weightManager) {
            this.products = products;
            this.start = start;
            this.end = end;
            this.weightManager = weightManager;
        }
        
        @Override
        protected void compute() {
            if (end - start <= BATCH_SIZE) {
                processBatch();
            } else {
                int middle = (start + end) / 2;
                MoveTask left = new MoveTask(products, start, middle, weightManager);
                MoveTask right = new MoveTask(products, middle, end, weightManager);
                invokeAll(left, right);
            }
        }
        
        private void processBatch() {
            for (int i = start; i < end; i++) {
                Product product = products.get(i);
                //добавляем товар к грузчику с минимальной загрузкой
                boolean placed = weightManager.addToBestLoader(product);
                
                if (placed) {
                    System.out.println("Товар #" + product.id + " (" + product.weight + 
                                     "кг) добавлен. Общий вес: " + 
                                     weightManager.getTotalWeight() + "/150кг");
                } else {
                    System.out.println("Товар #" + product.id + "не помещается. Общий вес: " + weightManager.getTotalWeight() + "/150кг");
                    weightManager.deliverAll();
                    
                    //пробуем снова после разгрузки
                    placed = weightManager.addToBestLoader(product);
                    if (placed) {
                        System.out.println("После разгрузки товар #" + product.id + " добавлен");
                    }
                }
                //показываем статус каждые 5 товаров
                if (i % 5 == 0) {
                    weightManager.printStatus();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        //товары на складе
        List<Product> warehouse = new ArrayList<>();
        Random random = new Random();
        int productCount = 30;
        
        System.out.println("Товары на складе:");
        for (int i = 1; i <= productCount; i++) {
            int weight = 20 + random.nextInt(61);
            warehouse.add(new Product(i, weight));
            System.out.println("Товар #" + i + ": " + weight + "кг");
        }
        
        System.out.println("\nНа складе " + productCount + " товаров");
        System.out.println("Общий лимит веса для всех грузчиков: 150кг");
        System.out.println("Работают 3 грузчика");
        System.out.println("Начинаем работу...\n");

        Loader[] loaders = {
            new Loader(1),
            new Loader(2), 
            new Loader(3)
        };
        
        //общий вес
        TotalWeightManager weightManager = new TotalWeightManager(loaders);

        ForkJoinPool pool = new ForkJoinPool(4);

        MoveTask task = new MoveTask(warehouse, 0, warehouse.size(), weightManager);
        pool.invoke(task);
        
        //отправляем оставшиеся товары
        System.out.println("\nОтправляем последние партии товаров...");
        weightManager.deliverAll();

        pool.shutdown();
        System.out.println("Все " + productCount + " товаров перенесены");
    }
}