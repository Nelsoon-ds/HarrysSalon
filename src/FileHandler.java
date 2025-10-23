import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class FileHandler {
    static String bookingFile = "masterdata/bookings.csv";
    String invoiceFile = "masterdata/invoices.csv";

    /**
     * <p>Den tager vores 8 Appointment attributer og konverterer dem til et Appointment objekt</p>
     *
     * @param parts
     * @return Returnerer et Appointment objekt
     */
    public static Appointment parseAttributes(String[] parts) {
        int appointmentId = Integer.parseInt(parts[0]);
        String customerName = parts[1];
        int customerPhone = Integer.parseInt(parts[2]);
        LocalDate date = LocalDate.parse(parts[3]);
        LocalTime time = LocalTime.parse(parts[4]);
        String rawProductString = parts[5].trim();
        ArrayList<Product> products = convertRawStringtoArrayList(rawProductString);
        double totalPrice = Double.parseDouble(parts[6]);
        return new Appointment(appointmentId, customerName, customerPhone, date, time, products, totalPrice);
    }


    public static ArrayList<Appointment> readFromFile() {
        // Vi opretter et array med vores appointments med den rette længde
        ArrayList<Appointment> appointments = new ArrayList<>();
        // Læs igen og udfyld arrayet
        try (BufferedReader br = new BufferedReader(new FileReader(bookingFile))) {
            String line = br.readLine(); // Vi vil gerne undgå at læse headeren
            while ((line = br.readLine()) != null) { // når linjen er null så er dokumentet færdig læst
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    Appointment parsedAppointment = parseAttributes(parts);
                    appointments.add(parsedAppointment);
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning: " + e.getMessage());
        }
        return appointments;
    }


    public static ArrayList<Product> convertRawStringtoArrayList(String productString) {
        ArrayList<Product> products = new ArrayList<>();
        String cleanedString = productString.trim();
        if (cleanedString.equals("[]")) {
            return products;
        }
        if (cleanedString.isEmpty()) {
            return products;
        }
        String[] individualProducts = cleanedString.split(";");

        for (String entry : individualProducts) {
            String cleanEntry = entry.trim();
            if (cleanEntry.isEmpty()) continue; // Skip
            // Del det i navn,pris,antal
            String[] parts = entry.split("\\|");
            if (parts.length == 3) {
                String productName = parts[0].trim();
                String productPrice = parts[1].trim();
                String productQuantity = parts[2].trim();
                products.add(new Product(productName, productPrice, productQuantity));
            } else {
                System.err.println("Fejl: Formateringen af produktet er forkert og er derfor ikke med i Appointments: " + entry);
            }
        }
        return products;
    }

    private static String convertProductListToString(ArrayList<Product> products) {
        if (products == null || products.isEmpty()) {
            return "[]";
        }
        ArrayList<String> productStrings = new ArrayList<>();
        for (Product product : products) {
            productStrings.add(product.getProductName() + "|" + product.getProductPrice() + "|" + product.getProductQuantity());
        }
        return String.join(";", productStrings);
    }

    static void writeToFile(ArrayList<Appointment> appointments) {
        try (FileWriter writer = new FileWriter(bookingFile)) {
            // Skriv header
            writer.write("appointmentId,customerName,customerPhone,date,time,products[],totalPrice\n");
            for (Appointment appointment : appointments) {
                String productsString = convertProductListToString(appointment.getProducts());
                String line = String.join(",",
                        String.valueOf(appointment.getAppointmentId()),
                        appointment.getCustomerName(),
                        String.valueOf(appointment.getCustomerPhone()),
                        appointment.getDate().toString(),
                        appointment.getTime().toString(),
                        productsString,
                        String.valueOf(appointment.getTotalPrice())
                );
                writer.write(line + "\n");
            }
            System.out.println(" skrevet til bookings.csv (komma-separeret)");

        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil: " + e.getMessage());
        }
    }
}

