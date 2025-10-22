import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class BookingSystem {

    // Hvad er dens overordnede ansvar? A: At samle en appointment og tilføje den til appointments
    // Der skal laves en Appointment ud fra dens konstruktør'



    public static void main(String[] args) {
        createAppointment();
    }


    private static ArrayList<Appointment> appointments = new ArrayList<>();
    private static int appointmentId = 1;
    private static int customerId = 1;


    public static void createAppointment() {
        Scanner input = new Scanner(System.in);

        // Indlæs kundens oplysninger
        System.out.print("Indtast kundens navn: ");
        String customerName = input.nextLine();

        System.out.print("Indtast kundens telefonnummer: ");
        int customerPhone = input.nextInt();
        input.nextLine(); // for at "cleane" scannerens newline

        // Formatering af dato og tid
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

        ArrayList<Product> products = new ArrayList<>();
        double totalPrice = 0.0;




        Appointment appointment = new Appointment(appointmentId++, customerName, customerPhone, customerId,
                date, time, products, totalPrice);

        appointments.add(appointment);
        System.out.println("Ny aftale oprettet: " + appointment);

    }









        // Derefter kan selve flowet begynde:
        // Data som vi henter fra scanner input:
        // customerName (ev.t hentet fra customerID checket)
        // customerPhone (ev.t hentet fra customerID checket)
        // date
        // time


        //for at få vores variabel products er vi nødt til at lave et
        // shoppingcart objekt som vi kalder cart
        // ShoppingCart cart = new ShoppingCart()






        // Når vi laver objektet så kaldes carts metode cart.showCart() som er et
        // print af de mulige produkter. Produkterne har et toString allerede.
        // Den bruger en scanner til at tilføje mulige produkter til en liste
        // Vi bruger cart.addProduct()
        // Metoden skal returner en arraylist af produkter som vi anvender her
        // totalPrice udregnes af ShoppingCarts "showTotalPrice()"
        // appId++ inkrementer bookingsystemets lokale værdi så vi kan have unikke bookinger
       // Appointment example =  new Appointment(appId++, customerName, customerPhone... etc)
        // appointments.add(example);




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


    public void saveAppointments (ArrayList<Appointment> appointments) {
        // kalder FileHandler med appointment listen
        // kalder FileHandler.save(appointments)
    }


}
