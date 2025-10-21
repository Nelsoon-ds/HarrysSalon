public class Product {

    private String productName;
    private double productPrice;

    public Product(String name, double price) {
        this.productName = name;
        this.productPrice = price;
    }

    public String getProductName(){
        return productName;
    }

    public double getProductPrice(){
        return productPrice;
    }

    public String toString(){
        return "product name: " + productName + " product price: " + productPrice;
    }
}