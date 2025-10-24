import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Appointment {

    private int appointmentId = 1;
    private String customerName = "noname";
    private int customerPhone;
    private LocalDate date;
    private LocalTime time;
    private ArrayList<Product> products;
    private boolean hasPaid;

    // Accounting Variables
    private double totalPrice;


    public Appointment(int appointmentId, String customerName, int customerPhone,
                       LocalDate date, LocalTime time, ArrayList<Product> products, double totalPrice) {
        this.appointmentId = appointmentId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.date = date;
        this.time = time;
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public void addNewProduct(String productName) {
        for (Product product : ShoppingCart.PRODUCTS) {
            if (productName.equalsIgnoreCase(product.getProductName())) {
                products.add(product);
                System.out.println(product);
            }
        }
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(int customerPhone) {
        this.customerPhone = customerPhone;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    @Override
    public String toString() {
        String productsSummary = products.stream()
                .map(product ->
                        product.getProductName() +
                                ": " + product.getProductPrice() +
                                ", Antal: " + product.getProductQuantity())
                .collect(Collectors.joining("; ")); // Join with a semicolon and space
        return "--- AFTALER ---" +
                "\nID: " + appointmentId +
                "\nKunde: " + customerName + " (" + customerPhone + ")" +
                "\nDato/Tidspunkt: " + date + " @ " + time +
                "\nTotal Pris: " + String.format("DKK %.2f", totalPrice) +
                "\nStatus: " + (hasPaid ? "BETALT" : "UBETALT") +
                "\nTilkøbs Produkter: [" + productsSummary+ "]" +
                "\n---------------------";
    }
}

