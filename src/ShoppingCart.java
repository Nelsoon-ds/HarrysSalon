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

    ArrayList<Product> products;




    public static void showProductList() {
        System.out.println("Product List: ");
        for (int i = 0; i < PRODUCTS.size(); i++) {
            System.out.println(PRODUCTS.get(i));
        }
        System.out.println("End of List. ");

       // kan evt. bruges til debug? System.out.println(Arrays.toString(PRODUCTS.toArray()));
    }





    public void addProduct(Product product){
        System.out.println("");

    }

    public void showCart(){

    }

    public void showTotalPrice(){

    }

}
