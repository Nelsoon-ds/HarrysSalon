import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class BookingSystem {

    private final ScannerHelper Sc = new ScannerHelper();
    private final FileHandler fh = new FileHandler();
    private ArrayList<Appointment> appointments = fh.readFromFile();
    private int appointmentId = appointments.getLast().getAppointmentId() + 1;
    private boolean running = true;


    public static void main(String[] args) {
        BookingSystem booking = new BookingSystem();
        booking.selectUser();
    }


    private void selectUser() {

        while (running) {
            int choice = Sc.selectUserOption();
            switch (choice) {
                case 1 -> harrietsProgram();
                case 2 -> {
                    if (authenticateUser()) {
                        harrysProgram();
                    }
                }
                case 3 -> {
                    if (authenticateUser()) {
                        revisorsProgram();
                    }
                }
            }
        }
    }


    private boolean authenticateUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Indtast adgangskode: ");
        String inputPassword = scanner.nextLine();

        if (inputPassword.equals("hairyharry")) {
            System.out.println("Adgangskode korrekt!");
            return true;
        } else {
            System.out.println("Forkert adgangskode! Adgang nægtet.");
            return false;
        }
    }

    private void harrietsProgram() {
        System.out.println("\nVelkommen Harriet! :)");

        while (running) {
            int choice = Sc.selectHarrietMenuOption();
            BookingSystem system = new BookingSystem();

            switch (choice) {
                case 1 -> createAppointment();
                case 2 -> deleteAppointment();
                case 3 -> editAppointment();
                case 4 -> system.viewCalendar();
                case 5 -> system.viewDateFromCalendar();
                case 6 -> selectUser();
                case 7 -> {
                    System.out.println("Lukker programmet...");
                    running = false;
                }
            }
        }
    }

    private void harrysProgram() {
        System.out.println("\nVelkommen Harry! :)");
        BookingSystem system = new BookingSystem();

        while (running) {
            int choice = Sc.selectHarryMenuOption();
            switch (choice) {
                case 1 -> createAppointment();
                case 2 -> deleteAppointment();
                case 3 -> editAppointment();
                case 4 -> system.viewCalendar();
                case 5 -> system.viewDateFromCalendar();
                case 6 -> new AccountingSystem(appointments);
                case 7 -> printAllAppointments();
                case 8 -> selectUser();
                case 9 -> {
                    System.out.println("Lukker programmet...");
                    running = false;
                }
            }
        }
    }

    private void revisorsProgram() {
        AccountingSystem acc = new AccountingSystem(appointments);
        System.out.println("\nVelkommen revisor! :)");

        while (running) {
            int choice = Sc.selectRevisorMenuOption();
            switch (choice) {
                case 1 -> acc.startProgram();
                case 2 -> printAllAppointments();
                case 3 -> selectUser();
                case 4 -> {
                    System.out.println("Lukker programmet...");
                    running = false;
                }
            }
        }

    }


    public void createAppointment() {
        Scanner input = new Scanner(System.in);
        int customerPhone = 0;
        // Indlæs kundens oplysninger
        try {
            System.out.print("Indtast kundens navn: ");
            String customerName = input.nextLine();
            if (customerName.isEmpty()) {
                System.out.println("Feltet kan ikke være tomt... Indtast kundens navn: ");
                return;
            }


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
                    } else if (date.isBefore(LocalDate.now())) {
                        System.out.println("Min tidsmaskin er i stykker... kan kun booke fremad i tiden!");
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
                if (time.isBefore(LocalTime.now())) {
                    System.out.println("Vælg venligst en fremtidig tid :)");
                    return;
                }
                if (!isTimeSlotAvailable(date, time)) {
                    System.out.println("Tidspunktet er allerede booket. Vælg et andet tidspunkt.");
                    return;
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
                    cart.addProduct(productName);
                }
            }
            ArrayList<Product> selectedProducts = cart.getProducts();
            double totalPrice = cart.showTotalPrice();
            Appointment appointment = new Appointment(appointmentId++, customerName, customerPhone, date, time, selectedProducts, totalPrice);
            appointments.add(appointment);
            System.out.println("Ny aftale oprettet:\n" + appointment);
            saveAppointments(appointments);

        } catch (Exception e) {
            System.out.println("Fejl ved oprettelse af aftale: " + e.getMessage());
        }
    }

    private boolean isTimeSlotAvailable(LocalDate date, LocalTime time) {
        return appointments.stream()
                .noneMatch(app -> app.getDate().equals(date) && app.getTime().equals(time));

    }

    private void viewCalendar() {
        ArrayList<Appointment> currentAppointments = getAppointments();
        CalendarUI calendar = new CalendarUI();
        calendar.printCalendarHeader();
        calendar.printWeekCalendar(appointments);

    }


    private void viewDateFromCalendar() {
        CalendarUI calendar = new CalendarUI();
        calendar.viewCalendarForSpecificDate(appointments);
    }



    private void findAppointment(String name) {
        for (Appointment app : appointments) {
            if (name.equals(app.getCustomerName())) {
                System.out.println("Du har følgende kunde med deres aftale:");
                System.out.println(app);
            }
        }
    }

    private void deleteAppointment() {
        Scanner input = new Scanner(System.in);


            while (true) {
                System.out.println("\n--- SLET BOOKING ---");
                System.out.print("Indtast Appointment ID for at slette: ");

                if (input.hasNextInt()) {
                    int appIdToDelete = input.nextInt();
                    input.nextLine();


                    boolean found = false;
                    for (Appointment app : appointments) {
                        if (app.getAppointmentId() == appIdToDelete) {
                            found = true;
                            System.out.println("Følgende aftale vil blive slettet:");
                            System.out.println(app);
                            System.out.print("Er du sikker? (ja/nej): ");
                            String confirmation = input.nextLine();

                            if (confirmation.equalsIgnoreCase("ja")) {
                                appointments.remove(app);
                                saveAppointments(appointments);
                                System.out.println("Aftale slettet!");
                                return;
                            } else {
                                System.out.println("Sletning annulleret.");
                                return;
                            }
                        }
                    }

                    if (!found) {
                        System.out.println("Ingen aftale fundet med ID: " + appIdToDelete);
                    }

                } else {
                    System.out.println("Ugyldigt input! Indtast et tal.");
                    input.nextLine();
                }
            }
        }


    private void editAppointment() {
        boolean runningMan = true;
        while (runningMan) {
            System.out.println("\n--- REDIGER BOOKING ---");
            System.out.print("Indtast Appointment ID for at redigere: ");
            Scanner input = new Scanner(System.in);
            ShoppingCart cart = new ShoppingCart();
            int appointmentId = input.nextInt();
            input.nextLine();
            for (Appointment app : appointments) {
                if (appointmentId == app.getAppointmentId()) {
                    System.out.println("Du har følgende kunde med deres aftale:");
                    System.out.println(app);
                    System.out.println("Hvad vil du gerne gøre?\n1. Tilføj produkt.\n2. Fjern produkt.\n3. Afslut betaling.\nVælg (1-3): ");
                    //Tilføj produkt
                    int userInput = input.nextInt();
                    if (userInput == 1) {
                        cart.showProductList();
                        System.out.print("Indtast produktnavn: ");
                        String productName = input.nextLine();
                        app.addNewProduct(productName);
                        saveAppointments(appointments);
                        runningMan = false;
                    }

                    //Tilføj fjern produkt
                    if (userInput == 2) {
                        System.out.print("Indtast produktnavn at fjerne: ");
                        String productToRemove = input.nextLine();
                        app.getProducts().removeIf(p -> p.getProductName().equalsIgnoreCase(productToRemove));
                        System.out.println("Produkt fjernet!");
                    }
                }
            }
        }
        System.out.println("Der var ingen aftale med følgende AppointmentID: " + appointmentId);
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void saveAppointments(ArrayList<Appointment> appointments) {
        fh.writeToFile(appointments);
    }

    public void printAllAppointments() {
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }
}

