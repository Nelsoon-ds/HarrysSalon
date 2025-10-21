import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingCart {
    /**
     * Produktlisten som vi indsætter produkter i
      */
    public static final List<Product> PRODUCTS = new ArrayList<Product>(Arrays.asList(
            new Product("Hårbørste", "550"),
            new Product("Shampoo", "150"),
            new Product("Balsam", "150")
    ));

    ArrayList<Product> products;



    public void addProduct(Product product){

    }

    public void showCart(){

    }

    public void showTotalPrice(){

    }

}
