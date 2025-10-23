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
    private static ArrayList<Appointment> appointments = FileHandler.readFromFile();
    private static int appointmentId = appointments.getLast().getAppointmentId() + 1;


    public static void main(String[] args) {
        System.out.println(appointmentId);
        createAppointment();
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
        ArrayList<Product> selectedProducts = cart.getProducts();
        double totalPrice = cart.showTotalPrice();
        Appointment appointment = new Appointment(appointmentId++, customerName, customerPhone, date, time, selectedProducts, totalPrice);
        appointments.add(appointment);
        System.out.println("Ny aftale oprettet:\n" + appointment);
        saveAppointments(appointments);
    }

    private void findAppointment(String name) {
        for (Appointment app : appointments) {
            if (name.equals(app.getCustomerName())) {
                System.out.println("Du har følgende kunde med deres aftale:");
                System.out.println(app);
            }
        }
    }

    private void deleteAppointment(int appointmentId) {
        for (Appointment app : appointments) {
            if (appointmentId == app.getAppointmentId()) {
                System.out.println("Du har følgende kunde med deres aftale:");
                System.out.println(app);
            }
        }
        System.out.println("Der var ingen aftale med følgende AppointmentID: " + appointmentId);
    }

    private void viewCalendar() {
        // Formål: Lav et CalendarUI objekt og kald dens metoder efter behov
        // Fx. calendar.weekCalendar(weekNr) bør give brugeren et print af hele arbejdsugen
        // Fx. calendar.dateCalendar(date) bør give brugeren et print af specifik dato
        // Exit-funktion som får os tilbage til systemet i booking system
        // try catch så vi ikke bliver fanget af at sende et forkert input (fx dato som ikke
        // eksistere eller er i forkert format.
    }

    public static ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public static void saveAppointments(ArrayList<Appointment> appointments) {
        FileHandler.writeToFile(appointments);
    }

}


