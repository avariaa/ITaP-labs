public class Fish extends Animal{
    private String scaleColor;
    private int size;
    private boolean isPredator;
    private String waterType;

    //по умолчанию
    public Fish() {
        super("Безымянная рыба", 1, "Аквариум", "Mальчик");
        this.scaleColor = "Синий";
        this.size = 5;
        this.isPredator = false;
        this.waterType = "Пресная";
    }

    //с параметрами
    public Fish(String name, int age, String habitat, String gender, String scaleColor, int size, boolean isPredator) {
        super(name, age, habitat, gender);
        setColor(scaleColor);
        setSize(size);
        this.isPredator = isPredator;
        setWater(waterType);
    }

    public String getColor() { 
        return scaleColor; 
    }
    public void setColor(String scaleColor) {
        this.scaleColor = ValidationUtils.validateString(scaleColor, "Неизвестно");
    }

    public int getSize() { 
        return size; 
    }
    public void setSize(int size) {
        this.size = ValidationUtils.validateSize(size, 0);
    }

    public boolean isPredator() { 
        return isPredator; 
    }

    public String getWater() { 
        return this.waterType; 
    }
    public void setWater(String waterType) {
        this.waterType = ValidationUtils.validateString(waterType, "Неизвестно");
    }

    //методы из Animal
    @Override
    public void makeSound() {
        System.out.println(getName() + " пускает пузыри: буль-буль");
    }
    
    @Override
    public void move() {
       System.out.println(getName() + " шустро плавает среди водорослей");
        
    }

    //собственные методы
    public void swim() {
        if (waterType.equals("Пресная")) {
            System.out.println(getName() + " плавает в пресной воде");
        } else {
            System.out.println(getName() + " плавает в соленой воде");
        }
    }
    
    public void hideInCoral() {
        System.out.println(getName() + " прячется в кораллах");
    }
    
    public void breatheUnderwater() {
        System.out.println(getName() + " дышит через жабры");
    }

    //переопределение displayInfo
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Цвет: %s\n", scaleColor);
        System.out.printf("Размер: %d см\n", size);
        System.out.printf("Хищник: %s\n", isPredator ? "есть" : "нет");
        System.out.printf("Тип воды: %s\n", waterType);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
