import java.time.LocalDate;
import java.time.MonthDay;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;

public class AccountingSystem {
    ArrayList<Appointment> bookings = FileHandler.readFromFile();
    // Produkter vi sælger
    private int shampooSales = 0;
    private int balsamSales = 0;
    private int hårKlipningSales = 0;
    private int hårbørsteSales = 0;
    // Priser:
    private final int shampooPrice = 550;
    private final int balsamPrice = 150;
    private final int hårbørstePrice = 550;
    private final int hårklipningPrice = 150;
    // Penge vi har tjent på forskellige produkter
    private int shampooInDkk = 0;
    private int balsamInDkk = 0;
    private int hårKlipningInDkk = 0;
    private int hårbørsteInDkk = 0;
    // Definer værdier for operationer
    private double totalSales = 0;
    private int totalProductsSold = 0;
    private int monthSale = 0;
    private int daySale = 0;
    private final String valuta = ",00 DKK";


    public static void main(String[] args) {
        AccountingSystem acc = new AccountingSystem();
        acc.startProgram();


    }

    public void startProgram() {
        AccountingSystem accounting = new AccountingSystem();
        getProductCount();
        accounting.calculateProductSales();
        accounting.countOfProductSales();
    }

    private void getProductCount() {
        for (int i = 0; i < bookings.size(); i++) {
            ArrayList<Product> currBooking = bookings.get(i).getProducts();
            for (Product product : currBooking) {
                totalProductsSold++;
                if (product.getProductName().equalsIgnoreCase("Shampoo")) {
                    shampooSales++;
                } else if (product.getProductName().equalsIgnoreCase("Balsam")) {
                    balsamSales++;
                } else if (product.getProductName().equalsIgnoreCase("Hårklipning")) {
                    hårKlipningSales++;
                } else if (product.getProductName().equalsIgnoreCase("Hårbørste")) {
                    hårbørsteSales++;
                }
            }
        }
    }


    private void countOfProductSales() {
        System.out.println("Antal af produktsalg");
        System.out.println();
        System.out.println("Shampoos solgt: " + shampooSales);
        System.out.println("-------------------");
        System.out.println("Balsams solgt: " + balsamSales);
        System.out.println("-------------------");
        System.out.println("Hårklipninger solgt: " + hårKlipningSales);
    }

    /**
     * Tager vores salg og ganger den med vores priser for at beregne den samlede intjening i DKK
     */
    private void calculateProductSales() {
        shampooInDkk = shampooSales * shampooPrice;
        System.out.println("Penge tjent på Shampoo:");
        System.out.println(shampooInDkk + valuta);
        balsamInDkk = balsamSales * balsamPrice;
        System.out.println("Penge tjent på Balsam:");
        System.out.println(balsamInDkk + valuta);
        hårbørsteInDkk = hårbørsteSales * hårbørstePrice;
        System.out.println("Penge tjent på hårbørster:");
        hårKlipningInDkk = hårKlipningSales * hårklipningPrice;
        System.out.println(hårKlipningInDkk + valuta);
        totalSales = shampooInDkk + balsamInDkk + hårKlipningInDkk;
    }

    private void calculateMonth(LocalDate month) {
        for (Appointment appointment : bookings) {
            if (month.isEqual(ChronoLocalDate.from(appointment.getDate().getMonth()))) {
                monthSale += appointment.getTotalPrice();
            }
        }
    }

//    private void calculateDay(LocalDate day) {
//        for (Appointment appointment : bookings) {
//            if (day.isEqual(ChronoLocalDate.from(appointment.getDate().getDayOfWeek() {
//                monthSale += appointment.getTotalPrice();
//            }
//        }
//
//    }
}



