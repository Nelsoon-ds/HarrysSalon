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
    //opretter ny arrylist opbejct?
    public ArrayList<Product> products = new ArrayList<>();


    public static void showProductList() {
        System.out.println("Product List: ");
        for (int i = 0; i < PRODUCTS.size(); i++) {
            System.out.println((i + 1) + " " + PRODUCTS.get(i));
        }
        System.out.println("End of List. \n");

       // kan evt. bruges til debug? System.out.println(Arrays.toString(PRODUCTS.toArray()));
    }



    public void addProduct(String productName, int quantity){
        for (Product product : PRODUCTS) {
            if (product.getProductName().equalsIgnoreCase(productName)){

        //checker om der allerede er noget i Cart
            boolean found = false;
            for (Product p : products) {
            if (p.getProductName.equalsIgnoreCase(product.getProductName())) {
                p.setProductQuantity(p.setProductQuantity() + quantity);
                found = true;
                System.out.println(quantity + " stk. " + product.getProductName() + "tilføjet til kurven. Nyt antal: " + p.getProductQuantity());
                break;
            }
        }
            if (!found) {
                Product newProduct = new Product(product.getProductName(), product.getProductPrice());
                 newProduct.setProductQuantity(quantity);
                product.add(newProduct);
                System.out.println(quantity + " stk. " + product.getProductName() + "tilføjet til kurven.");
            }
            return;
        }
            System.out.println("Produktet \"" + productName + "\" findes ikke i sortimentet.");


        public void removeProduct(String productName, int quantity)
        for (Product p : products) {
            if (p.getProductName.equalsIgnoreCase(productName)) {
                if (p.getProductQuantity() > quantity){
                    p.setProductQuantity(product.getProductQuantity() - quantity);
                    System.out.println(quantity + " stk. " + p.getProductName() + "fjernet fra kurv. *NYT* antal: " + p.getProductQuantity());
                } else {
                    product.remove(p);
                    System.out.println(product.getProductName() + " er fjernet fra kurven.");
                }
                return;
            }
            System.out.println("Produktet \"" + productName + "\" findes ikke i kurven.");
        }



    public ArrayList<Product> getProducts() {
        return products;
    }

    public void showCart(){
        System.out.println("Produkter tilføjet: ");
        if (products.isEmpty()) {
            System.out.println("Ingen tilkøbsprodukter tiløjet ");
        } else {
            for (int i = 0); i < product.size(); i++ {
                System.out.println((i + 1) + ". " + product.get(i));
            }
        }
        System.out.println();

    }

    public double showTotalPrice(){
        double total = 00.00;
        for (Product product : products) {
            total += product.getProductPrice() * product.getProductQuantity();
        }
        System.out.println("Total pris: " + total + "kr.");
        return total;

    }

}
