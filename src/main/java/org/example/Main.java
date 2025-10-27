package org.example;
import org.example.taskOne.HashTable;
import org.example.taskTwo.ProductManagement;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nЗадание 1");
        taskOne();
        System.out.println("\nЗадание 2");
        taskTwo();
    }
    public static void taskOne() {
        HashTable<String, Integer> hashTable = new HashTable<>();

        hashTable.put("Яблоко", 1);
        hashTable.put("Банан", 2);
        hashTable.put("Апельсин", 3);

        hashTable.put("Яблоко", 4); // Можно обновить значение

        System.out.println("Размер: " + hashTable.size());
        System.out.println("Таблица пустая: " + hashTable.isEmpty());

        System.out.println("Показать Яблоки: " + hashTable.get("Яблоко"));
        System.out.println("Показать Бананы: " + hashTable.get("Банан"));
        System.out.println("Показать Грушы: " + hashTable.get("Груша"));

        System.out.println("Удаляем Банан: " + hashTable.remove("Банан"));
        System.out.println("Разсер после удаления: " + hashTable.size());

        hashTable.put("Груша", 7);
        hashTable.display();
    }
    public static void taskTwo() {
        ProductManagement productManagement = new ProductManagement();

        productManagement.addProduct("1234567890", "Молоко", 85.50, 100);
        productManagement.addProduct("0987654321", "Хлеб", 45.00, 200);
        productManagement.addProduct("5555555555", "Сыр", 320.00, 50);

        productManagement.findProduct("1234567890");
        productManagement.findProduct("9999999999"); // Несуществующий штрихкод

        productManagement.showAllProducts();

        productManagement.removeProduct("5555555555");
        productManagement.removeProduct("0000000000"); // Попытка удалить несуществующий

        productManagement.showAllProducts();
    }
}