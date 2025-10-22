import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingCart {
    /**
     * Produktlisten som vi indsætter produkter i
      */
    public static final List<Product> PRODUCTS = new ArrayList<Product>(Arrays.asList(
            new Product("Hårbørste", 550),
            new Product("Shampoo", 150),
            new Product("Balsam", 150)
    ));
    public ArrayList<Product> getProducts;

    ArrayList<Product> products;




    public static void showProductList() {
        System.out.println("Product List: ");
        for (int i = 0; i < PRODUCTS.size(); i++) {
            System.out.println((i + 1) + " " + PRODUCTS.get(i));
        }
        System.out.println("End of List. ");

       // kan evt. bruges til debug? System.out.println(Arrays.toString(PRODUCTS.toArray()));
    }



    public void addProduct(String productName){
        for (Product product : PRODUCTS) {
            if (product.getProductName().equalsIgnoreCase(productName)){
        }

        boolean found = false;

        //checker om der allerede er noget i Cart
        for (Product p1 : products) {
            if (p1.getProductName.equals(product.getProductName())) {
                p1.setQuantity(p1.setQuantity()) + quantity);
                found = true;
                System.out.println(quantity + " " + product.getProductName() + "tilføjet til kurven. Nyt antal: " + p1.getQuantity());
                break;
            }
        }

    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void showCart(){
        System.out.println("Produkter tilføjet: ");
        if (products.isEmpty()) {
            System.out.println("Ingen tilkøbsprodukter tiløjet ");
        } else {


        }

    }

    public double showTotalPrice(){
        double total = 00.00;
        System.out.println("Total pris: " + total + "kr.");
        return total;

    }

}
