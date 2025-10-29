class Contact {
    private String name;
    private String email;
    private String additionalInfo;

    public Contact(String name, String email, String additionalInfo) {
        this.name = name;
        this.email = email;
        this.additionalInfo = additionalInfo;
    }

    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getAddInfo() {return additionalInfo;}

    public void setName(String name) {this.name = name;}
    public void setEmail(String email) {this.email = email;}
    public void setAddInfo(String additionalInfo) {this.additionalInfo = additionalInfo;}

    @Override
    public String toString() {
        return String.format("Contact{name='%s', email='%s', additionalInfo='%s'}", name, email, additionalInfo);
    }
}



public class PhoneBook {
    private HashTable<String, Contact> table;

    public PhoneBook() {
        table = new HashTable<>();
    }

    // Использование методов из класса HashTable
    public void addContact(String phone, Contact contact) {
        table.put(phone, contact);
    }

    public Contact getContact(String phone) {
        return table.get(phone);
    }

    public Contact removeContact(String phone) {
        return table.remove(phone);
    }

    public void printAll() {
        table.printTable();
    }

    public int size() {
        return table.size();
    } 
}


// Тестирование
class PhoneBookApp {
    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();

        book.addContact("+79111234567", new Contact("Алиса", "alice@mail.com", "Москва"));
        book.addContact("+79121234567", new Contact("Борис", "boris@mail.com", "СПб"));
        book.addContact("+79131234567", new Contact("Катя", "katya@mail.com", "Казань"));

        System.out.println("\nСтруктура таблицы:");
        book.printAll();

        System.out.println("Поиск контакта: " + book.getContact("+79121234567"));
        System.out.println("Удаляем: " + book.removeContact("+79111234567"));
        System.out.println("\nВсе контакты после изменений таблицы:");
        book.printAll();
        System.out.println("Размер таблицы: " + book.size());
    }
}
