import java.io.File;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class BookingSystem {

    private static ArrayList<Appointment> appointments = new ArrayList<>(List.of(
            // Aftale 1
            new Appointment(1, "Test Kunde 1", 12345678,
                    LocalDate.of(2024, 10, 20), LocalTime.of(9, 0),
                    new ArrayList<>(), 500.0), // Bruger new ArrayList<>() for tom produktliste

            // Aftale 2
            new Appointment(2, "Test Kunde 2", 87654321,
                    LocalDate.of(2024, 10, 21), LocalTime.of(10, 30),
                    new ArrayList<>(), 750.0)

            // Tilføj flere her adskilt af komma...
    ));
    private static int appointmentId = 1;
    public static void main(String[] args) {
        appointments = FileHandler.readFromFile();
        for (int i = 0; i < appointments.size(); i++) {
            System.out.println(appointments.get(i));
        }
//        createAppointment();
//        FileHandler.writeToFile(appointments);
    }

    public static void createAppointment() {
        Scanner input = new Scanner(System.in);
        int customerPhone = 0;
        // Indlæs kundens oplysninger
        System.out.print("Indtast kundens navn: ");
        String customerName = input.nextLine();


        boolean numberCheckFalseTrue = false;
        while (numberCheckFalseTrue == false) {
            System.out.print("Indtast kundens telefonnummer: ");

            if (input.hasNextInt()) {
                customerPhone = input.nextInt();
                numberCheckFalseTrue = true;
            } else {
                System.out.println("Indtast kun tal!");
            }
            input.nextLine();
        }
        // Formatering af dato til tid
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate date = null;
        while (date == null) {
            System.out.print("Indtast dato for aftalen (Format for dato: dd/MM/yyyy): ");
            String dateString = input.nextLine();

            try {
                date = LocalDate.parse(dateString, dateFormatter);
                if (date.getDayOfWeek().getValue() >= 6) {
                    System.out.println("Brug kun en dato, på en af ugedagene: mandag - fredag");
                    date = null;
                }
            } catch (Exception e) {
                System.out.println("Fejl: forkert dato format (dd/MM/yyyy)!");
            }
        }

        LocalTime time = null;
        while (time == null) {
            System.out.print("Indtast tidspunkt for aftale (Tidspunkt format: HH:mm): ");
            String timeString = input.nextLine();
            if (timeString.length() != 5) {
                System.out.println("Brug kun formatet: HH:mm !");
                continue;
            }
            try {
                time = LocalTime.parse(timeString, timeFormatter);
                if (time.isBefore(LocalTime.of(10, 0)) || time.isAfter(LocalTime.of(18, 0))) {
                    System.out.println("Ugyldigt tidspunkt: Vær venlig at vælge et tidspunkt mellem 10:00 - 18:00!");
                    time = null;
                }
            } catch (Exception e) {
                System.out.println("Forkert format, vær venlig at bruge HH:mm!");
            }
        }
        //Shoppingcart her.
        ShoppingCart cart = new ShoppingCart();
        boolean addingProducts = true;
        while (addingProducts) {
            cart.showProductList();
            System.out.println("Indtast tilvalg/tilkøb (skriv 'stop' for at stoppe): ");
            String productName = input.nextLine();
            if (productName.equalsIgnoreCase("stop")) {
                addingProducts = false;
            } else {
                cart.addProduct(productName); // skal fikses
            }
        }

        //total pris
        System.out.println("\n--- Prisoversigt ---");
        ArrayList<Product> selectedProducts = cart.getProducts();
        System.out.println(selectedProducts);
        double totalPrice = cart.showTotalPrice();
        Appointment appointment = new Appointment(appointmentId++, customerName, customerPhone,
                date, time, selectedProducts, totalPrice);
        appointments.add(appointment);
        System.out.println("Ny aftale oprettet: " + appointment);

                System.out.println("Og print nu listen");
                System.out.println(appointment);

    }
    private void findAppointment() {
        // Find navn
        // Find telefon
        // String name = scan.next() --> true
        // int telefon = scan.nextInt(); --> true
        // Hvad er deres position i arraylisten?
        // Vi kan bruge en Scanner til at indhente navn, telefon
        // Scanner scan = new Scanner()
        // appointments.contains(navn)
        // inner loop: apppointments.contains(telefon)
        // if true --> for ( Appointment app : appointmentlist) {
        // if (app.customerName.equals(name) || app.customerPhone == telefon)
        //  print(appointment) }
        // Print de appointments som matcher navn og telefon
        // Find en appointment med de to variabler
        // Slut flow
    }

    private void deleteAppointment(int appointmentId){
        // Formål: at fjerne en appointment fra appointments
        // For hvert element i vores appointmentlist
        // Tjek om appointmentId == værdien i appointmentId på en af vores appointment
        // Try  appointments.remove(appointmentId)
        // Catch --> print("Vi fandt ikke et ID")
        // Hvis den finder et match:
    }

    private void viewCalendar(){
        // Formål: Lav et CalendarUI objekt og kald dens metoder efter behov
        // Fx. calendar.weekCalendar(weekNr) bør give brugeren et print af hele arbejdsugen
        // Fx. calendar.dateCalendar(date) bør give brugeren et print af specifik dato
        // Exit-funktion som får os tilbage til systemet i booking system
        // try catch så vi ikke bliver fanget af at sende et forkert input (fx dato som ikke
        // eksistere eller er i forkert format.
    }

    public static ArrayList<Appointment>  getAppointments() {
        return appointments;
    }

//    public void saveAppointments (ArrayList<Appointment> appointments) {
//        // kalder FileHandler med appointment listen
//        // kalder FileHandler.save(appointments)
//    }

}


