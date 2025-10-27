import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Håndterer al logik for at læse og skrive Appointment-objekter
 * til og fra en CSV-fil (bookings.csv).
 * Denne klasse er ansvarlig for "oversættelsen" mellem Java-objekter
 * og tekst-baseret data i filen.
 */
public class FileHandler {

    // Stien til den fil, vi læser fra og skriver til.
    static final String bookingFile = "masterdata/bookings.csv";

    // TODO: Denne fil nåede vi ikke at implementere men skulle være den data som Accounting kunne skrive til.
    static final String invoiceFile = "masterdata/invoices.csv";

    /**
     * "Parser" en enkelt linje (allerede splittet) fra CSV-filen om til et Appointment-objekt.
     * Metoden antager, at 'parts' har præcis 7 elementer i den korrekte rækkefølge.
     *
     * @param parts Et String-array, der repræsenterer en enkelt række fra bookings.csv, splittet ved ','.
     * @return Et færdigt initialiseret Appointment-objekt.
     */
    private Appointment parseAttributes(String[] parts) {
        // Indlæs og konverter hver del af CSV-linjen til den korrekte datatype
        int appointmentId = Integer.parseInt(parts[0]);
        String customerName = parts[1];
        int customerPhone = Integer.parseInt(parts[2]);
        LocalDate date = LocalDate.parse(parts[3]);
        LocalTime time = LocalTime.parse(parts[4]);

        // Den femte kolonne (produkter) er selv en "indlejret" liste,
        // så den skal have sin egen parse-metode.
        String rawProductString = parts[5].trim();
        ArrayList<Product> products = convertRawStringtoArrayList(rawProductString);

        double totalPrice = Double.parseDouble(parts[6]);

        // Opret og returner det færdige objekt
        return new Appointment(appointmentId, customerName, customerPhone, date, time, products, totalPrice);
    }


    /**
     * Læser *alle* aftaler fra 'bookingFile' (bookings.csv) og returnerer dem som en liste.
     * Håndterer 'try-with-resources' for sikker fil-lukning og skipper CSV-headeren.
     *
     * @return En ArrayList<Appointment> med alle aftaler fra filen.
     * Listen er tom, hvis filen er tom eller ikke kan læses.
     */
    public ArrayList<Appointment> readFromFile() {
        // Opret den liste, vi skal fylde op og returnere
        ArrayList<Appointment> appointments = new ArrayList<>();

        // Brug try-with-resources (BufferedReader br = ...).
        // Det sikrer, at filen lukkes automatisk, selv hvis der sker en fejl.
        try (BufferedReader br = new BufferedReader(new FileReader(bookingFile))) {

            // Læs og ignorer den første linje (headeren: "appointmentId,customerName,...")
            br.readLine();

            String line;
            // Løb igennem resten af filens linjer, så længe der er flere
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // Split linjen ved hvert komma

                // Sikkerhedstjek: Sørg for, at linjen har det forventede antal kolonner (7)
                // Dette forhindrer fejl, hvis en linje er korrupt eller tom.
                if (parts.length == 7) {
                    // Brug vores hjælpe-metode til at omdanne linjen til et objekt
                    Appointment parsedAppointment = parseAttributes(parts);
                    // Tilføj det nye objekt til listen
                    appointments.add(parsedAppointment);
                }
            }
        } catch (IOException e) {
            // Håndter fejl, hvis filen f.eks. ikke findes eller ikke kan læses
            System.out.println("Fejl ved læsning af fil: " + e.getMessage());
        }

        // Returner den færdigfyldte liste
        return appointments;
    }

    /**
     * Konverterer produkt-strengen fra CSV-filen til en liste af Product-objekter.
     * Strengen forventes at have formatet: "ProduktNavn|Pris|Antal;NæsteProdukt|Pris|Antal"
     *
     * @param productString Den rå streng fra CSV-filens 'products[]'-kolonne.
     * @return En ArrayList<Product>. Returnerer en tom liste, hvis strengen er tom eller "[]".
     */
    private ArrayList<Product> convertRawStringtoArrayList(String productString) {
        ArrayList<Product> products = new ArrayList<>();
        String cleanedString = productString.trim();

        // Håndter det specielle tilfælde for en tom liste, som gemmes som "[]"
        if (cleanedString.equals("[]") || cleanedString.isEmpty()) {
            return products; // Returner en tom liste
        }

        // Split strengen op i individuelle produkter (adskilt af ';')
        String[] individualProducts = cleanedString.split(";");

        for (String entry : individualProducts) {
            String cleanEntry = entry.trim();
            if (cleanEntry.isEmpty()) continue; // Spring over

            // Split det enkelte produkt op i dets attributter (adskilt af '|')
            String[] parts = entry.split("\\|");

            // Igen, et sikkerhedstjek: Vi skal have præcis 3 dele (Navn, Pris, Antal)
            if (parts.length == 3) {
                String productName = parts[0].trim();
                double productPrice = Double.parseDouble(parts[1].trim());
                int productQuantity = Integer.parseInt(parts[2].trim());
                products.add(new Product(productName, productPrice, productQuantity));
            } else {
                // Print en tydelig fejl, hvis dataen er korrupt, så vi ved det.
                System.err.println("Fejl: Formateringen af produktet er forkert og er derfor ikke med i Appointments: " + entry);
            }
        }
        return products;
    }

    /**
     * Konverterer en liste af Product-objekter tilbage til en enkelt streng,
     * der sikkert kan gemmes i en CSV-kolonne.
     * Formatet er: "ProduktNavn|Pris|Antal;NæsteProdukt|Pris|Antal"
     *
     * @param products Listen af produkter tilknyttet en aftale.
     * @return En formateret streng. Returnerer "[]" hvis listen er tom eller null.
     */
    private String convertProductListToString(ArrayList<Product> products) {
        // Håndter tomme lister ved at returnere "[]" som standard
        if (products == null || products.isEmpty()) {
            return "[]";
        }

        ArrayList<String> productStrings = new ArrayList<>();
        for (Product product : products) {
            // Byg den enkelte produkt-streng (f.eks. "Shampoo|150|1")
            productStrings.add(product.getProductName() + "|" + product.getProductPrice() + "|" + product.getProductQuantity());
        }

        // Saml alle produkt-strenge til én lang streng, adskilt af ';'
        return String.join(";", productStrings);
    }

    /**
     * Gemmer den *komplette* liste af aftaler til 'bookingFile' (bookings.csv).
     * Denne metode OVERSKRIVER hele filen med den nye data.
     *
     * @param appointments Den (opdaterede) liste af alle aftaler, der skal gemmes.
     */
    public void writeToFile(ArrayList<Appointment> appointments) {
        // Det er unødvendigt at lave en 'new FileHandler()' her,
        // da vi allerede ER i en FileHandler-instans.
        // Vi kan kalde 'convertProductListToString' direkte.

        // Brug try-with-resources til at sikre, at filen lukkes korrekt
        try (FileWriter writer = new FileWriter(bookingFile)) {
            // Skriv CSV-headeren (kolonnenavnene) som den allerførste linje
            writer.write("appointmentId,customerName,customerPhone,date,time,products[],totalPrice\n");

            // Løb igennem hver aftale i listen
            for (Appointment appointment : appointments) {
                // Konverter den nestede produktliste til et String format
                String productsString = convertProductListToString(appointment.getProducts());

                // Saml alle aftalens data til en enkelt CSV-linje (adskilt af ',')
                String line = String.join(",",
                        String.valueOf(appointment.getAppointmentId()),
                        appointment.getCustomerName(),
                        String.valueOf(appointment.getCustomerPhone()),
                        appointment.getDate().toString(),
                        appointment.getTime().toString(),
                        productsString,
                        String.valueOf(appointment.getTotalPrice())
                );
                // Skriv linjen til filen, efterfulgt af et linjeskift
                writer.write(line + "\n");
            }
            System.out.println(appointments.size() + " aftaler er skrevet til bookings.csv");

        } catch (IOException e) {
            // Håndter fejl, hvis filen f.eks. er skrivebeskyttet
            System.out.println("Fejl ved skrivning til fil: " + e.getMessage());
        }
    }
}