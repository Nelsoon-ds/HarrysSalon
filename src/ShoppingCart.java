import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ShoppingCart {
    /**
     * Produktlisten som vi indsætter produkter i
      */
    public static final List<Product> PRODUCTS = new ArrayList<Product>(Arrays.asList(
            new Product("Hårbørste", "550", "1"),
            new Product("Shampoo", "150", "1"),
            new Product("Balsam", "150", "1")
    ));

    public static ArrayList<Product> products = new ArrayList<>();
    static {
        products.add(new Product("Hårklipning", "150", "1"));
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void showCart(){
        System.out.println("Produkter tilføjet: ");
        if (products.isEmpty()) {
            System.out.println("Ingen tilkøbsprodukter tiløjet ");
        } else {
            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". " + products.get(i));
            }
        }
        System.out.println();

    }
    public void showProductList() {
        System.out.println("Harrys Salon Produktliste:");
        System.out.printf("%-3s %-20s %5s %6s\n", "Nr", "Navn", "Pris", "Antal");
        for (int i = 0; i < PRODUCTS.size(); i++) {
            System.out.printf("%-3s %s\n", (i + 1), PRODUCTS.get(i));        }
    }

    public double showTotalPrice(){
        double total = 00.00;
        ArrayList<Product> products = getProducts();
        for (Product product : products) {
            total += Double.valueOf(product.getProductPrice()) * Double.valueOf(product.getProductQuantity());
        }
        System.out.println("Total pris: " + total + "kr.");
        return total;
    }

    public void addProduct(String productName) {
        for (Product product : PRODUCTS) {
                if(product.getProductName().equalsIgnoreCase(productName)) {
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Hvor mange " + productName.toUpperCase() + " vil du købe?");
                    String productQuantity = scan.next();
                    String productPrice = product.getProductPrice();
                    this.products.add(new Product(productName, productPrice, productQuantity));
                    return;
                }
            }
        System.out.println("Det er ikke et produkt vi har desværre.");

    }

    public void removeProduct(String productName) {
        for (Product product : PRODUCTS) {
            if(product.getProductName().equalsIgnoreCase(productName)) {
                this.products.remove(product);
                return;
            }
        }
        System.out.println("Det er ikke et produkt vi har desværre.");

    }
    }
