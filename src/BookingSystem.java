import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class BookingSystem {

    // Hvad er dens overordnede ansvar? A: At samle en appointment og tilføje den til appointments
    // Der skal laves en Appointment ud fra dens konstruktør

    public static void main(String[] args) {
        createAppointment();
    }


    private static ArrayList<Appointment> appointments = new ArrayList<>();
    private static int appointmentId = 1;
    private static int customerId = 1;
    private static int customerPhone = 0;


    public static void createAppointment() {
        Scanner input = new Scanner(System.in);

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
        //Shoppingcart her.
        ShoppingCart cart = new ShoppingCart();
        boolean addingProducts = true;
        while (addingProducts) {
            System.out.println("Indtast tilvalg/tilkøb (skriv 'stop' for at stoppe): ");
            String productName = input.nextLine();
            if (productName.equalsIgnoreCase("stop")) {
                addingProducts = false;
            } else {
                cart.addProduct(productName);
            }
        }

        //vis kurv
        System.out.println("\n--- " + customerName "s Kurv ---");
        cart.showCart();

        //total pris
        System.out.println("\n--- Prisoversigt ---");
        double totalPrice = cart.showTotalPrice();
        ArrayList<Product> products = new ArrayList<>();

        ArrayList<Product> selectedProducts = cart.getProducts();

        System.out.println();

        Appointment appointment = new Appointment(appointmentId++, customerName, customerPhone, customerId,
                date, time, products, totalPrice);

        appointments.add(appointment);
        System.out.println("Ny aftale oprettet: " + appointment);


    } }}

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

    public ArrayList<Appointment> getAppointments() {
        // Formål: At hente en liste af bookinger fra vores master fil
        // Den skal have et objekt af vores FileHandler som så bruges til at kalde
        // FileHandlers read() metode
        // Derefter skal vi gemme resultaterne af det i vores appointments arraylist
        // Vi starter dog med at bruge fake data til at arbejde med:
        // Fake data:
        Appointment app1 = new Appointment();
        Appointment app2 = new Appointment();
        Appointment app3 = new Appointment();
        Appointment app4 = new Appointment();
        appointments.add(app1);
        appointments.add(app2);
        appointments.add(app3);
        appointments.add(app4);
        return appointments;
    }

        public void saveAppointments (ArrayList<Appointment> appointments) {
        // kalder FileHandler med appointment listen
        // kalder FileHandler.save(appointments)
    }


}
