import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.time.format.TextStyle;


public class CalendarUI {


        private static Locale locale = new Locale("da", "DK");

        // Hjælpefunktion: returnerer O/X/– for et timeslot
        private static String getStatusForSlot(LocalDate date, int hour, ArrayList<Appointment> appointments) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            String dayName = dayOfWeek.getDisplayName(TextStyle.FULL, locale);

            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                return "–"; // weekend = ikke bookbar
            }

            for (Appointment appt : appointments) {
                if (appt.getDate().equals(date) && appt.getTime().getHour() == hour) {
                    return "X"; // optaget
                }
            }

            return "O"; // ledigt
        }

        // Print header
        public static void printCalendarHeader() {
            System.out.print(String.format("%3s %10s %3s %10s %3s", "|", "Dato", "|", "Ugedag", "|"));
            for (int h = 10; h <= 18; h++) {
                System.out.print(String.format("%9s %3s", h + ":00", "|"));
            }
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------------" +
                    "------------------------------------------------");
        }

        // Print hele ugen
        public static void printWeekCalendar(ArrayList<Appointment> appointments) {
            LocalDate today = LocalDate.now();

            // Find mandag i denne uge
            LocalDate monday = today.minusDays(today.getDayOfWeek().getValue() - 1);

            int calenderDateMaxAmountOfDays = 21;

            for (int i = 0; i < calenderDateMaxAmountOfDays; i++) {
                LocalDate day = monday.plusDays(i);
                DayOfWeek dayOfWeek = day.getDayOfWeek();

                // Print dato og ugedag
                System.out.print(String.format("%3s %6d %3s %10s", "|", day.getDayOfMonth(), "|", dayOfWeek));

                // Print timeslots 10–18
                for (int h = 10; h <= 18; h++) {
                    System.out.print(" " + getStatusForSlot(day, h, appointments) + "   |");
                }
                System.out.println();
            }
        }
    }
