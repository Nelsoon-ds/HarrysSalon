import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class FileHandler {

    static String bookingFile = "masterdata/bookings.csv";
    String invoiceFile = "masterdata/invoices.csv";

    public static void main(String[] args) {
        readFromFile();
        writeToFile();

    }

    public static void readFromFile() {
        // Først læser vi vores bookings
        int count = 1; // Linjetæller
        try (BufferedReader br = new BufferedReader(new FileReader(bookingFile))) {
            while (br.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning: " + e.getMessage());
            return;
        }
        // Vi opretter et array med vores appointments med den rette længde
        Appointment[] appointments = new Appointment[count];;
        System.out.println(appointments.length);

        // Læs igen og udfyld arrayet
        try (BufferedReader br = new BufferedReader(new FileReader(bookingFile))) {
            System.out.println("Vi er igang med at udfylde arrayet");

            String line;
            int i = 0;
            while ((line = br.readLine()) != null) { //når linjen er null så er dokumentet færdig læst
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    System.out.println(parts[5]);// længden skal være lig antallet af attributter
                    System.out.println(parts[6]);// længden skal være lig antallet af attributter
                    int appointmentId = Integer.parseInt(parts[0]);
                    String customerName = parts[1];
                    int customerPhone = Integer.parseInt(parts[2]);
                    LocalDate date = LocalDate.parse(parts[3]);
                    LocalTime time = LocalTime.parse(parts[4]);
                    String rawProductString = parts[5];
                    ArrayList<Product> products = convertRawStringtoArrayList(parts[5]);
                    double totalPrice = Double.parseDouble(parts[6]);
              appointments[i] = new Appointment(appointmentId, customerName, customerPhone, date, time, products, totalPrice); // Vi skal have tilføjet en konstruktør
                    i++;
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning: " + e.getMessage());
        }
    }

    public static ArrayList<Product> convertRawStringtoArrayList(String productString) {
        ArrayList<Product> products = new ArrayList<>();
        String cleanedString = productString.trim();

        if (cleanedString.startsWith("[") && cleanedString.startsWith("]")) {
            cleanedString = cleanedString.substring(1, cleanedString.length() - 1); // fjern start og slut
        } else {
            return products;
        }
        System.out.println(cleanedString);
        String[] parts = cleanedString.split(";");
      //  name:price;
      //  name:price;
        for (int i = 0; i < parts.length; i++) {
            String productName = parts[i].trim();
            String productPrice = parts[i + 1].trim();
            String productQuantity = parts[i + 2].trim();
            products.add(new Product(productName, productPrice, productQuantity));}
        return products;
    }

    private static void writeToFile() {

        String[] fakeListe = fakeApps();

        try (FileWriter writer = new FileWriter(bookingFile)) {
            writer.write("appointmentId,customerName,customerPhone,date,time,products[],totalPrice\n");

            for (String appointment : fakeListe) {
                writer.write(String.join(",", appointment) + "\n"); // alle felter på samme linje, separeret med komma
            }

            System.out.println(" skrevet til bookings.csv (komma-separeret)");

        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil: " + e.getMessage());

        }
    }

    private static String[] fakeApps() {
        // 1. Opret listen, der skal returneres
        ArrayList<Appointment> appointmentsList = new ArrayList<>();
        // Appointment 1
        ArrayList<Product> products = new ArrayList<>();
        Product h1 = new Product("Hårvask", "100", "3");
        Product h2 = new Product("Hårvask", "200", "3");
        products.add(h1);
        products.add(h2);

        Appointment app1 = new Appointment(1, "Anna Jensen", 22334455, LocalDate.of(2025, 10, 28)
                , LocalTime.of(10, 0), products, 649.0);
        appointmentsList.add(app1); // <-- Tilføj til listen


        String[] stringAppointmentList = new String[appointmentsList.size()];
        // Det samme gør sig gældende for vores appointments
        for (int i = 0; i < appointmentsList.size(); i++) {
            stringAppointmentList[i] = appointmentsList.get(i).toString();
        }

        // 2. Returner den færdige liste
        return stringAppointmentList;
    }

    private void readFile() {
    }
}

