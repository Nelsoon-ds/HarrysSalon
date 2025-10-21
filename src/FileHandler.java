import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {

    // buildings[appointmentindex[AttributIndex]] returner navnet på appid 1 hvor at appointment er standin for appid'et

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

                    int year = Integer.parseInt(parts[2]);
                    appointments[i] = new Appointment(); // Vi skal have tilføjet en konstruktør
                    i++;
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning: " + e.getMessage());
        }

        // Udskriv bygningerne
        System.out.println("Famous Buildings:");
        for (Building b : buildings) {
            System.out.println(b);
        }







    }
    private void saveFile(){

        // branch
        // something new

    }

    private void readFile(){


    }

}
