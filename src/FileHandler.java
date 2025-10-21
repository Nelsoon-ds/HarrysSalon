import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class FileHandler {

    // buildings[appointmentindex[AttributIndex]] returner navnet på appid 1 hvor at appointment er standin for appid'et

    String bookingFile = "./masterdata/bookings.csv";
    String invoiceFile = "/.masterdata/invoices.csv";

    public static void main(String[] args) {
        String bookingFile = "./masterdata/bookings.csv";
        String invoiceFile = "/.masterdata/invoices.csv";


        // Først læser vi vores bookings
        int count = 0; // Linjetæller
        try (BufferedReader br = new BufferedReader(new FileReader(bookingFile))) {
            while (br.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning: " + e.getMessage());
            return;
        }
        // Vi opretter et array med vores appointments med den rette længde
        Appointment[] appointments = new Appointment[count];

        // Læs igen og udfyld arrayet
        try (BufferedReader br = new BufferedReader(new FileReader(bookingFile))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) { //når linjen er null så er dokumentet færdig læst
                String[] parts = line.split(",");
                // line = "Thomas, Denmark, Nr"
                if (parts.length == 8) { // længden skal være lig antallet af attributter
                    int appointmentId = Integer.parseInt(parts[0]);
                    String customerName = parts[1];
                    int customerPhone = Integer.parseInt(parts[2]);
                    int customerId = Integer.parseInt(parts[3]);
                    LocalDate date = LocalDate.parse(parts[4]);
                    LocalTime time = LocalTime.parse(parts[5]);
                    ArrayList<Product> products = parts[6];
                    double totalPrice = Double.parseDouble(parts[7]);

                    appointments[i] = new Appointment(); // Vi skal have tilføjet en konstruktør
                    i++;
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning: " + e.getMessage());
        }

    }

    private void saveFile() {

        ArrayList<String[]> products = new ArrayList<>();
        products.add(new String[]{"Hårklip", "500"});
        products.add(new String[]{"Hårbørste", "800"});
        products.add(new String[]{"Hårspray", "100"});

        try (FileWriter writer = new FileWriter(bookingFile)) {
            writer.write("appointmentId,customerName,customerPhone,customerId,date,time,products,totalPrice\n");

            for (String[] appointment : appointments) {
                writer.write(String.join(",", planet) + "\n"); // alle felter på samme linje, separeret med komma
            }

            System.out.println(" skrevet til bookings.csv (komma-separeret)");

        }
        catch(IOException e) {
            System.out.println("Fejl ved skrivning til fil: " + e.getMessage());

        }
    }

    private void readFile(){


    }

}
