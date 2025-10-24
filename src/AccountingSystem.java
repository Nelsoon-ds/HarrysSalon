import java.time.LocalDate;
import java.time.MonthDay;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;

public class AccountingSystem {
    ArrayList<Appointment> bookings = FileHandler.readFromFile();
    // Produkter vi sælger
    static int shampooSales = 0;
    static int balsamSales = 0;
    static int hårKlipningSales = 0;
    static int hårbørsteSales = 0;
    // Priser:
    int shampooPrice = 550;
    int balsamPrice = 150;
    int hårbørstePrice = 550;
    int hårklipningPrice = 150;
    // Penge vi har tjent på forskellige produkter
    int shampooInDkk = 0;
    int balsamInDkk = 0;
    int hårKlipningInDkk = 0;
    int hårbørsteInDkk = 0;
    // Definer værdier for operationer
    double totalSales = 0;
    static int totalProductsSold = 0;
    int monthSale = 0;
    int daySale = 0;


    static void main(String[] args) {
        AccountingSystem acc = new AccountingSystem();
        acc.startProgram();


    }

    private void startProgram() {
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
        System.out.println(shampooInDkk);
        balsamInDkk = balsamSales * balsamPrice;
        System.out.println("Penge tjent på Balsam:");
        System.out.println(balsamInDkk);
        hårbørsteInDkk = hårbørsteSales * hårbørstePrice;
        System.out.println("Penge tjent på hårbørster:");
        hårKlipningInDkk = hårKlipningSales * hårklipningPrice;
        System.out.println(hårKlipningInDkk);
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



