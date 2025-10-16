import java.util.Scanner;

public class AnimalsDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //КОНСТРУКТОРЫ ПО УМОЛЧАНИЮ
        System.out.println("Животные по умолчанию...");
        Cat defaultCat = new Cat();
        Fish defaultFish = new Fish();
        Parrot defaultParrot = new Parrot();

        defaultCat.displayInfo();
        defaultFish.displayInfo();
        defaultParrot.displayInfo();

        //ВВОД С КЛАВИАТУРЫ
        System.out.println("Ввод новго животного...");
        System.out.print("Введите имя кошки: ");
        String catName = scanner.nextLine();

        System.out.print("Введите возраст кошки: ");
        int catAge = scanner.nextInt();

        System.out.print("Введите среду обитания кошки: ");
        scanner.nextLine();
        String catHabitat = scanner.nextLine();

        System.out.print("Введите пол кошки: ");
        String catGender = scanner.nextLine();

        System.out.print("Введите породу кошки: ");
        String catBreed = scanner.nextLine();

        System.out.print("Введите цвет шерсти кошки: ");
        String catColor = scanner.nextLine();

        Cat userCat = new Cat(catName, catAge, catHabitat, catGender, catBreed, catColor);
        
        System.out.println("Внесена новая кошка:");
        userCat.displayInfo();


        //ИСПОЛЬЗОВАНИЕ МЕТОДОВ АБСТРАКТНОГО КЛАССА
        System.out.println();
        System.out.println("Демонстрация полиморфизма:");
        Animal[] animals = {
            new Cat("Барсик", 6, "Дом", "Мальчик", "Сиамская", "Серый"), 
            new Parrot("Кеша", 2, "Клетка", "Мальчик", "Голубой", false, "Спокойный"),
            new Fish("Люся", 1, "Аквариум", "Девочка", "Синий", 5, false)
        };

        for (Animal animal: animals) {
            animal.makeSound();
            animal.move();
            System.out.println();
        }

        //ИНКАПСУЛЯЦИЯ
        System.out.println("Демонстрация инкапсуляции:");
        Cat testCat = new Cat();
        System.out.println("Изначальное имя: " + testCat.getName());
        //пытаемся установить некорректное имя
        testCat.setName("");

        System.out.println("Изначальный возраст: " + testCat.getAge());
        testCat.setAge(9);
        System.out.println("Новый возраст: " + testCat.getAge());
        System.out.println();

        //СТАТИЧЕСКИЙ СЧЕТЧИК
        System.out.println("Всего создано животных: " + Animal.getAnimalCount());

        //еще животные для счетчика
        Cat anotherCat = new Cat();
        Parrot anotherParrot = new Parrot();
        Fish anotherFish = new Fish();
        System.out.println("После создания новых животных: " + Animal.getAnimalCount());

    //СПЕЦИФИЧЕСКОЕ ПОВЕДЕНИЕ
        System.out.println("Специфическое поведение:");
        userCat.purr();
        userCat.climbSurface();
        userCat.hunt();
        
        animals[1].sleep(); // Попугай спит
        animals[2].eat();   // Рыбка ест
        
        scanner.close();
        
        System.out.println("~~~~~~~~~~~~~~Конец списка~~~~~~~~~~~~~~~~~~");
        System.out.println("Итоговое количество созданных животных: " + Animal.getAnimalCount());

    }

}
