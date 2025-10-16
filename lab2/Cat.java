public class Cat extends Animal {
    private String breed;
    private String furColor;

    public Cat() {
        super("Безымянный кот", 1, "Улица", "Mальчик");
        this.breed = "Дворовой";
        this.furColor = "Серый";
    }

    public Cat(String name, int age, String habitat, String gender, String breed, String furColor) {
        super(name, age, habitat, gender);
        setBreed(breed);
        setFurColor(furColor);
    }

    public String getBreed() { 
        return breed; 
    }
    public void setBreed(String breed) {
        this.breed = ValidationUtils.validateString(breed, "Неизвестно");
    }

    public String getFurColor() { 
        return furColor; 
    }
    public void setFurColor(String furColor) {
        this.furColor = ValidationUtils.validateString(furColor, "Неизвестно");
    }

    //методы из Animal
    @Override
    public void makeSound() {
        System.out.println(getName() + " говорит: мяу-мяу");
    }
    @Override
    public void move() {
        System.out.println(getName() + " грациозно ходит и бегает");
    }

    //собственные методы
    public void purr() {
        System.out.println(getName() + " мурлычет");
    }
    
    public void climbSurface() {
        System.out.println(getName() + " ловко лазает по поверхностям");
    }
    
    public void hunt() {
        if (getHabitat().equals("Улица")) {
            System.out.println(getName() + " охотится на птиц");
        } else {
            System.out.println(getName() + " играет с игрушкой");
        }
    }

    //переопределение displayInfo
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Порода: %s\n", breed);
        System.out.printf("Цвет шерсти: %s\n", furColor);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
    }
}

