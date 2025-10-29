import java.util.LinkedList;

class HashTable<K, V> {
    private static double LOAD_FACTOR = 0.75;

    private LinkedList<Entry<K, V>>[] table;
    private int size;

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
        public void setValue(V value) { this.value = value; }
    }

    HashTable() {
        table = new LinkedList[16];
        size = 0;
    }
    HashTable(int capacity) {
        table = new LinkedList[capacity];
        size = 0;
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode()) % table.length;
    }

    public void put(K key, V value) {
        if ((double) size / table.length > LOAD_FACTOR) {
            resize();
        }

        int index = hash(key);

        if (table[index] == null) {
            table[index] = new LinkedList<Entry<K, V>>();
        }

        for (Entry<K, V> entry: table[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }

        // Если ключ не найден - добавляем 
        table[index].add(new Entry<K, V>(key, value));
        size++;
    }
    
    // Метод get получает значения по ключу
    public V get(K key) {
        if (key == null) {
            return null;
        }

        int index = hash(key);

        if (table[index] == null) {
            return null;
        }

        for (Entry<K, V> entry: table[index]) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    // Метод remove удаляет пару по ключу
    public V remove(K key) {
        if (key == null) {
            return null;
        }
        
        int index = hash(key);
        
        // Если цепочка пуста - ключа нет
        if (table[index] == null) {
            return null;
        }
        
        // Ищем ключ в цепочке
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                // Нашли - удаляем и возвращаем значение
                V value = entry.getValue();
                table[index].remove(entry);
                size--;
                
                // Если цепочка стала пустой - удаляем LinkedList
                if (table[index].isEmpty()) {
                    table[index] = null;
                }
                
            return value;
            }
        }
        return null; // Если ключ не найден
    }

    // Метод size возвращает кол-во элементов
    public int size() {
        return size;
    }

    // Метод isEmpty проверяет, пуста ли таблица
    public boolean isEmpty() {
        return size == 0;
    }

    // Изменение размера таблицы (рехеширование)
    private void resize(){
        LinkedList<Entry<K, V>>[] oldTable = table;
        table = new LinkedList[oldTable.length * 2];
        size = 0;

        for (LinkedList<Entry<K, V>> bucket : oldTable) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    // Метод для отладки - печать всей таблицы
    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            System.out.print("Bucket " + i + ": ");
            if (table[i] != null) {
                for (Entry<K, V> entry : table[i]) {
                    System.out.print("[" + entry.getKey() + "=" + entry.getValue() + "] -> ");
                }
            }
            System.out.println("null");
        }
    }
}
