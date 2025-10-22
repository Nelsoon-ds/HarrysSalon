public class Product {

    private String productName;
    private String productPrice;
    private String delimiter = ";";
    private String productQuantity;


    public Product(String productName, String productPrice, String productQuantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    //setters og getters


    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String toString(){
        return productName + "|" + productPrice + "|" + productQuantity;
}
}
