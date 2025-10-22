import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ShoppingCart {
    /**
     * Produktlisten som vi indsætter produkter i
      */
    public static final List<Product> PRODUCTS = new ArrayList<Product>(Arrays.asList(
            new Product("Hårbørste", "550", "3"),
            new Product("Shampoo", "150", "3"),
            new Product("Balsam", "150", "4")
    ));

    public ArrayList<Product> products;

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
    public static void showProductList() {
        System.out.println("Product List: ");
        for (int i = 0; i < PRODUCTS.size(); i++) {
            System.out.println((i + 1) + " " + PRODUCTS.get(i));
        }
        System.out.println("End of List. \n");
       // kan evt. bruges til debug? System.out.println(Arrays.toString(PRODUCTS.toArray()));
    }

    /*
    Den her metode har meget brug for noget validering
    Ala: Try/Catch
     */
    public void addProduct(String productName) {
        for (Product product : PRODUCTS) {
                if(product.getProductName().equalsIgnoreCase(productName)) {
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Hvor mange " + productName + " vil du købe?");
                    String productQuantity = scan.next();
                    String productPrice = product.getProductPrice();
                    products.add(new Product(productName, productPrice, productQuantity));
                } else {
                    System.out.println("Det er ikke et produkt vi har desværre.");
                    return;
                }
            }

        }
    }



/*
* Vi skal fikse det her efter vi har fået styr på data typerne med parseren i FileHandler.
 */

//    public double showTotalPrice(){
//        double total = 00.00;
//        for (Product product : products) {
//            total += product.getProductPrice() * product.getProductQuantity();
//        }
//        System.out.println("Total pris: " + total + "kr.");
//        return total;
//    }





//    public void addProduct(String productName, String quantity){
//        for (Product product : PRODUCTS) {
//            if (product.getProductName().equalsIgnoreCase(productName)){
//        //checker om der allerede er noget i Cart
//            boolean found = false;
//            for (Product p : products) {
//            if (p.getProductName().equalsIgnoreCase(productName){
//                p.setProductQuantity(quantity);
//                found = true;
//                System.out.println(quantity + " stk. " + product.getProductName() + "tilføjet til kurven. Nyt antal: " + p.getProductQuantity());
//                break;
//            }
//        }
//            if (!found) {
//                Product newProduct = new Product(product.getProductName(), product.getProductPrice());
//                 newProduct.setProductQuantity(quantity);
//                product.add(newProduct);
//                System.out.println(quantity + " stk. " + product.getProductName() + "tilføjet til kurven.");
//            }
//            return;
//        }
//            System.out.println("Produktet \"" + productName + "\" findes ikke i sortimentet.");


//        public void removeProduct(String productName, int quantity)
//        for (Product p : products) {
//            if (p.getProductName.equalsIgnoreCase(productName)) {
//                if (p.getProductQuantity() > quantity){
//                    p.setProductQuantity(product.getProductQuantity() - quantity);
//                    System.out.println(quantity + " stk. " + p.getProductName() + "fjernet fra kurv. *NYT* antal: " + p.getProductQuantity());
//                } else {
//                    product.remove(p);
//                    System.out.println(product.getProductName() + " er fjernet fra kurven.");
//                }
//                return;
//            }
//            System.out.println("Produktet \"" + productName + "\" findes ikke i kurven.");
//        }
//

