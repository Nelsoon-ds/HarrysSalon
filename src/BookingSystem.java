import java.lang.reflect.Array;
import java.util.ArrayList;

public class BookingSystem {

    private ArrayList<Appointment> appointments;
    private int appId = 1;
    private void makeAppointment(){
        // Hvad er dens overordnede ansvar? A: At samle en appointment og tilføje den til appointments
        // Der skal laves en Appointment ud fra dens konstruktør

        // Der skal være et IF-check som ser om der er et customerID
        // Hvis der et et customerID så læs deres navn & telefonnummer ud fra filen

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
