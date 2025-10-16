public class Parrot extends Animal{
    private String featherColor;
    private boolean canTalk;
    private String personality;

    //по умолчанию
    public Parrot() {
        super("Безымянный попугай", 1, "Тропики", "Mальчик");
        this.featherColor = "Зеленый";
        this.canTalk = false;
        this.personality = "Скромный";
    }

    //с параметрами
    public Parrot(String name, int age, String habitat, String gender, String featherColor, boolean canTalk, String personality) {
        super(name, age, habitat, gender);
        setColor(featherColor);
        this.canTalk = canTalk;
        setPersonality(personality);
    }

    public String getColor() { 
        return featherColor; 
    }
    public void setColor(String featherColor) {
        this.featherColor = ValidationUtils.validateString(featherColor, "Неизвестно");
    }

    public String getPersonality() { 
        return personality; 
    }
    public void setPersonality(String personality) {
        this.personality = ValidationUtils.validateString(personality, "Неизвестно");
    }

    public boolean canTalk() { 
        return canTalk; 
    }

    //методы из Animal
    @Override
    public void makeSound() {
        if (canTalk) {
            System.out.println(getName() + " повторяет за вами");
        } else {
            System.out.println(getName() + " чирикает");
        }
    }
    @Override
    public void move() {
        System.out.println(getName() + " летает и прыгает");
    }

    //собственные методы
    public void singSong() {
        System.out.println(getName() + " напевает песенку");
    }
    
    public void crackNuts() {
        System.out.println(getName() + " раскалывает орешки");
    }
    
    public void hunt() {
        if (getHabitat().equals("Тропики")) {
            System.out.println(getName() + " охотится на насекомых");
        } else {
            System.out.println(getName() + " грызет корм");
        }
    }

    //переопределение displayInfo
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Цвет оперения: %s\n", featherColor);
        System.out.printf("Умеет говорить: %s\n", canTalk ? "да" : "нет");
        System.out.printf("Характер: %s\n", personality);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
