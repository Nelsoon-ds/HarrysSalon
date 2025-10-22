import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Appointment {

    private int appointmentId = 1;
    private int customerId;
    private String customerName = "noname";
    private int customerPhone;
    private LocalDate date;
    private LocalTime time;
    private ArrayList<Product> products;
    private double totalPrice;
    //private boolean hasPaid;


    public Appointment(int appointmentId, String customerName, int customerPhone, int customerId,
                       LocalDate date, LocalTime time, ArrayList<Product> products, double totalPrice) {
        this.appointmentId = appointmentId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerId = customerId;
        this.date = date;
        this.time = time;
        this.products = products;
        this.totalPrice = totalPrice;
    }


    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
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

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    //    public boolean isHasPaid() {
    //        return hasPaid;
    //    }
    //
    //    public void setHasPaid(boolean hasPaid) {
    //        this.hasPaid = hasPaid;
    //    }


    @Override
    public String toString() {
        return "Appointment{" +
                ", totalPrice=" + totalPrice +
                ", products=" + products +
                ", time=" + time +
                ", date=" + date +
                ", customerPhone=" + customerPhone +
                ", customerName='" + customerName + '\'' +
                ", appointmentId=" + appointmentId +
                '}';
    }

    private void addProducts(Product product){
        products.add(product);

    }

    private void removeProducts(Product product){
        products.remove(product);


    }



}

