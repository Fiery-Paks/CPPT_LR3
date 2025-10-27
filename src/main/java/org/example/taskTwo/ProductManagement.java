package org.example.taskTwo;
import java.util.HashMap;
import java.util.Map;

public class ProductManagement {
    private Map<String, Product> productMap;

    public ProductManagement() {
        this.productMap = new HashMap<>();
    }

    public void addProduct(String barcode, String name, double price, int quantity) {
        Product product = new Product(name, price, quantity);
        productMap.put(barcode, product);
        System.out.println("Продукт добавлен: " + name + " (штрхкод: " + barcode + ")");
    }

    public Product findProduct(String barcode) {
        Product product = productMap.get(barcode);
        if (product != null) {
            System.out.println("Найден продукт: " + product);
        } else {
            System.out.println("Продукт со штрихкодом " + barcode + " не найден");
        }
        return product;
    }

    public boolean removeProduct(String barcode) {
        Product removedProduct = productMap.remove(barcode);
        if (removedProduct != null) {
            System.out.println("Продукт удален: " + removedProduct.getName() + " (штрихкод: " + barcode + ")");
            return true;
        } else {
            System.out.println("Продукт со штрихкодом " + barcode + " не найден для удаления");
            return false;
        }
    }

    public void showAllProducts() {
        if (productMap.isEmpty()) {
            System.out.println("Склад пуст");
            return;
        }

        System.out.println("\nВСЕ ПРОДУКТЫ НА СКЛАДЕ");
        for (Map.Entry<String, Product> entry : productMap.entrySet()) {
            System.out.println("Штрихкод: " + entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println();
    }
}
