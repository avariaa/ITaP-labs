package lab6;

public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(10);
        
        stack.push(1);
        stack.push(2);
        stack.push(3);
        
        System.out.println("Содержимое стека: " + stack);
        System.out.println("Размер стека: " + stack.size());
        
        System.out.println("pop(): " + stack.pop());  
        System.out.println("peek(): " + stack.peek()); 
        System.out.println("Содержимое стека: " + stack);
        
        stack.push(4);
        System.out.println("push(4)");
        System.out.println("pop(): " + stack.pop());  
        
        System.out.println("Стек пуст? " + stack.isEmpty());
        

        Stack<String> stringStack = new Stack<>(5);
        stringStack.push("Hello");
        stringStack.push("World");
        
        System.out.println("\nСтек строк:");
        while (!stringStack.isEmpty()) {
            System.out.println(stringStack.pop());
        }
    }
}
