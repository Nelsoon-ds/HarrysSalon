import java.time.LocalDate;
// import java.time.MonthDay; // Fjernet - Blev ikke brugt
// import java.time.chrono.ChronoLocalDate; // Fjernet - Blev ikke brugt
import java.util.ArrayList;

public class AccountingSystem {

    // Data:
    private final FileHandler fh = new FileHandler();
    private ArrayList<Appointment> bookings = fh.readFromFile();

    // Priser:
    private final int shampooPrice = 550;
    private final int balsamPrice = 150;
    private final int hårbørstePrice = 550;
    private final int hårklipningPrice = 150;
    private final String valuta = ",00 DKK";

    // Felter til at gemme beregnet tilstand (state)
    private int shampooSales = 0;
    private int balsamSales = 0;
    private int hårKlipningSales = 0;
    private int hårbørsteSales = 0;
    private int totalProductsSold = 0;

    private int shampooInDkk = 0;
    private int balsamInDkk = 0;
    private int hårKlipningInDkk = 0;
    private int hårbørsteInDkk = 0;
    private double totalSales = 0;

    public static void main(String[] args) {
        // 2. Opret objektet med dataen
        AccountingSystem acc = new AccountingSystem();

        // 3. Kør rapporten
        acc.startProgram();
    }

    public void startProgram() {
        // 1. Tæl alle produkter solgt
        calculateAllProductCounts();
        printProductCounts();

        System.out.println("\n--- Omsætning ---");

        // 2. Beregn omsætning baseret på de tal
        calculateAllProductRevenue();
        printProductRevenue();

        // 3. Print totalen
        System.out.println("-------------------");
        System.out.println("Total omsætning: " + totalSales + valuta);
    }

    /**
     * Tæller alle produktsalg og gemmer dem i klassens felter.
     */
    private void calculateAllProductCounts() {
        for (Appointment appointment : bookings) {
            ArrayList<Product> currBooking = appointment.getProducts();
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

    /**
     * Printer de optalte produktsalg til konsollen.
     */
    private void printProductCounts() {
        System.out.println("--- Antal af produktsalg ---");
        System.out.println("Shampoos solgt: " + shampooSales);
        System.out.println("-------------------");
        System.out.println("Balsams solgt: " + balsamSales);
        System.out.println("-------------------");
        System.out.println("Hårklipninger solgt: " + hårKlipningSales);
        System.out.println("-------------------");
        System.out.println("Hårbørster solgt: " + hårbørsteSales);
    }

    /**
     * Beregner den samlede omsætning for hvert produkt
     * og den samlede omsætning for alle salg.
     */
    private void calculateAllProductRevenue() {
        shampooInDkk = shampooSales * shampooPrice;
        balsamInDkk = balsamSales * balsamPrice;
        hårbørsteInDkk = hårbørsteSales * hårbørstePrice;
        hårKlipningInDkk = hårKlipningSales * hårklipningPrice;
        totalSales = shampooInDkk + balsamInDkk + hårKlipningInDkk + hårbørsteInDkk;
    }

    /**
     * Printer den beregnede omsætning til konsollen.
     */
    private void printProductRevenue() {
        System.out.println("Penge tjent på Shampoo:");
        System.out.println(shampooInDkk + valuta);

        System.out.println("Penge tjent på Balsam:");
        System.out.println(balsamInDkk + valuta);

        System.out.println("Penge tjent på Hårbørster:");
        System.out.println(hårbørsteInDkk + valuta);

        System.out.println("Penge tjent på Hårklipning:");
        System.out.println(hårKlipningInDkk + valuta);
    }
}