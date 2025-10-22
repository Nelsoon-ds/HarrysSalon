public class Product {

    private int productQuantity;
    private String productName;
    private double productPrice;

    //konstruktør (navn og pris)
    public Product(String name, double price) {
        this.productQuantity = 1;
        this.productName = name;
        this.productPrice = price;
    }
    //konstruktør (quantity, navn og pris)
    public Product(int quantity, String name, double price) {
        this.productQuantity = quantity;
        this.productName = name;
        this.productPrice = price;
    }

    //setters og getters


    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }



    public String toString() {
        return "Product{" +
                "productQuantity=" + productQuantity +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
