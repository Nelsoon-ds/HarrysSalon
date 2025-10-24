import java.io.File;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class BookingSystem {

    private static ScannerHelper Sc = new ScannerHelper();
    private static ArrayList<Appointment> appointments = FileHandler.readFromFile();
    private static int appointmentId = appointments.getLast().getAppointmentId() + 1;
    private static boolean running = true;


    public static void main(String[] args) {
        ArrayList<Appointment> currentAppointments = getAppointments();
        CalendarUI calendar = new CalendarUI();
        calendar.printCalendarHeader();
        calendar.printWeekCalendar(currentAppointments);

        calendar.viewCalendarForSpecificDate(appointments);

    }

    private static void selectUser() {


        while (running) {
            int choice = Sc.selectUserOption();
            switch (choice) {
                case 1 -> harrietsProgram();
                case 2 -> harrysProgram();
                case 3 -> revisorsProgram();
            }
        }
    }


    private static void harrietsProgram() {
        System.out.println("\nVelkommen Harriet! :)");

        while (running) {
            int choice = Sc.selectHarrietMenuOption();

            BookingSystem system = new BookingSystem();

            switch (choice) {
                case 1 -> createAppointment();
                case 2 -> deleteAppointment();
                case 3 -> system.viewCalendar();
                // case x -> editAppointment(); --> I can make this!
                case 4 -> selectUser();
                case 5 -> running = false;
            }
        }
    }

    private static void harrysProgram() {
        System.out.println("\nVelkommen Harry! :)");

        while (running) {
            int choice = Sc.selectHarryMenuOption();
            switch (choice) {
                case 1 -> createAppointment();
                //case 2 -> deleteAppointment();
                //case 3 -> weekCalendar();
                //case 4 -> invoices();
                case 5 -> selectUser();
                case 6 -> running = false;
            }
        }
    }

    private static void revisorsProgram() {
        System.out.println("\nVelkommen revisor! :)");

        while (running) {
            int choice = Sc.selectRevisorMenuOption();
            switch (choice) {
                //case 1 -> invoices();
                case 2 -> selectUser();
                case 3 -> running = false;
            }
        }

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
                continue;
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

    private void viewCalendar() {
        ArrayList<Appointment> currentAppointments = getAppointments();
        CalendarUI calendar = new CalendarUI();
        calendar.printCalendarHeader();
        calendar.printWeekCalendar(appointments);
    }


    private void findAppointment(String name) {
        for (Appointment app : appointments) {
            if (name.equals(app.getCustomerName())) {
                System.out.println("Du har følgende kunde med deres aftale:");
                System.out.println(app);
            }
        }
    }

    private static void deleteAppointment() {
        Scanner input = new Scanner(System.in);
        int appIdtoDelete = input.nextInt();
        for (Appointment app : appointments) {
            if (appIdtoDelete == app.getAppointmentId()) {
                System.out.println("Du har følgende kunde med deres aftale:");
                System.out.println(app);
                appointments.remove(app);
                saveAppointments(appointments);
            }
        }
        System.out.println("Der var ingen aftale med følgende AppointmentID: " + appointmentId);
    }

    public static ArrayList<Appointment> getAppointments() {
        return BookingSystem.appointments;
    }

    public static void saveAppointments(ArrayList<Appointment> appointments) {
        FileHandler.writeToFile(appointments);
    }
}

