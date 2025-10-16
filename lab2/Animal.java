abstract class Animal {
    private String name;
    private int age;
    private String habitat;
    private String gender;
    
    private static int animalCount = 0; //внутренняя логика
    
    protected Animal(String name, int age, String habitat, String gender) {
        setName(name);
        setAge(age);
        setHabitat(habitat);
        setGender(gender);
        animalCount++;
    }

    public abstract void makeSound(); //для полиморфизма
    public abstract void move();

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
    this.name = ValidationUtils.validateString(name, "Неизвестно");
    }

    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = ValidationUtils.validateAge(age, 0);
    }

    public String getHabitat() { 
        return this.habitat; 
    }
    public void setHabitat(String habitat) {
        this.habitat = ValidationUtils.validateString(habitat, "Неизвестно");
    }

    public String getGender() { 
        return this.gender; 
    }
    public void setGender(String gender) {
        this.gender = ValidationUtils.validateString(gender, "Неизвестно");
    }


    public static int getAnimalCount() { //получение счетчика
        return animalCount; 
    }
    
    //просто общие методы
     public void sleep() {
        System.out.println(name + "спит");
    }
    public void eat() {
        System.out.println(name + "ест");
    }

    public void displayInfo() {
        System.out.println("~~~~~Ваше животное:");
        System.out.printf("Имя: %s\n", name);
        System.out.printf("Возраст: %d лет\n", age);
        System.out.printf("Среда обитания: %s\n", habitat);
        System.out.printf("Пол: %s\n", gender);
    }
}

