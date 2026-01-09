package lab8;

public class Main {
    public static void main(String[] args) {

        DataManager manager = new DataManager();

        //регистрируем обработчики
        manager.registerDataProcessor(new FilterProcessor());
        manager.registerDataProcessor(new TransformProcessor());

        //читаем файл input.txt
        manager.loadData("lab8/test_data.txt");

        //запускаем обработку
        manager.processData();

        //сохраняем результат
        manager.saveData("lab8/output.txt");
    }
}