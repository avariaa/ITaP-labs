package lab4; 
import java.io.*;


public class FileCopy {
    public static void main(String[] args) {
        FileInputStream testFile = null;
        FileOutputStream demoFile = null;

        
        try {
            //обработка ошибок открытия файлов

            try {
                testFile = new FileInputStream("source.txt");
                System.out.println("Исходный файл успешно открыт");

            } catch(FileNotFoundException e) {
                throw new IOException("Ошибка открытия исходного файла: " + e.getMessage());
            }

            try {
                demoFile = new FileOutputStream("demo.txt");
                System.out.println("Файл назначения успешно открыт");
                
            } catch(FileNotFoundException e) {
                throw new IOException("Ошибка открытия файла назначения: " + e.getMessage());
            }

            //копирование данных
            byte[] buffer = new byte[1024];
            int bytesRead;
            

            while ((bytesRead = testFile.read(buffer)) != -1) {
                demoFile.write(buffer, 0, bytesRead);
            }

            System.out.println("Файл успешно скопирован");

        } catch(IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        
        } finally {
            //обработка ошибок закрытия файлов

            try {
                if (testFile != null) {
                    testFile.close();
                    System.out.println("Исходный файл успешно закрыт");

                }

            } catch(IOException e) {
                System.out.println("Ошибка закрытия исходного файла: " + e.getMessage());
            }

            try {
                if (demoFile != null) {
                    demoFile.close();
                    System.out.println("Файл назначения успешно закрыт");
                }

            } catch(IOException e) {
                System.out.println("Ошибка закрытия файла назначения: " + e.getMessage());
            }
        } 
    }
}
