import java.util.ArrayList;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Locale;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;

public class CalendarUI {

    private static final Locale locale = new Locale("da", "DK");


    private static String centerText(String text, int width) {
        if (text.length() >= width) return text;
        int padding = width - text.length();
        int left = padding / 2;
        int right = padding - left;
        return " ".repeat(left) + text + " ".repeat(right);
    }


    private static String getStatusForSlot(LocalDate date, int hour, ArrayList<Appointment> appointments) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return "- * -";
        }

        for (Appointment appt : appointments) {
            if (appt.getDate().equals(date) && appt.getTime().getHour() == hour) {
                return "[  X  ]";
            }
        }

        return "( ledig )";
    }


    public static void printCalendarHeader() {
        int colWeek = 8;
        int colDate = 10;
        int colDay = 12;
        int colHour = 10;

        StringBuilder header = new StringBuilder();
        header.append("║ ").append(centerText("Uge", colWeek))
                .append(" │ ").append(centerText("Dato", colDate))
                .append(" │ ").append(centerText("Ugedag", colDay)).append(" │");

        for (int h = 10; h <= 18; h++) {
            header.append(" ").append(centerText(h + ":00", colHour)).append(" ║");
        }

        int totalLength = header.length();

        System.out.println("╔" + "═".repeat(totalLength - 2) + "╗");
        System.out.println(header);
        System.out.println("╚" + "═".repeat(totalLength - 2) + "╝");
        System.out.println("┌" + "─".repeat(totalLength - 2) + "┐");
    }


    public static void printWeekCalendar(ArrayList<Appointment> appointments) {
        int colWeek = 8;
        int colDate = 10;
        int colDay = 12;
        int colHour = 10;

        LocalDate today = LocalDate.now();
        LocalDate monday = today.minusDays(today.getDayOfWeek().getValue() - 1);

        int calendarDateMaxAmountOfDays = 21;

        for (int i = 0; i < calendarDateMaxAmountOfDays; i++) {
            LocalDate day = monday.plusDays(i);
            DayOfWeek dayOfWeek = day.getDayOfWeek();
            String dayName = dayOfWeek.getDisplayName(TextStyle.FULL, locale);
            String dayNumber = String.valueOf(day.getDayOfMonth());
            int weekNumber = day.get(WeekFields.of(locale).weekOfWeekBasedYear());

            //vis dato "i dag".
            if (day.equals(LocalDate.now())) {
                dayName = "> " + dayName.toUpperCase() + " <";
                dayNumber = ">> " + dayNumber + " <<";
            }


            StringBuilder line = new StringBuilder();

            if (dayOfWeek == DayOfWeek.MONDAY) {
                line.append("│ ").append(centerText("Uge " + weekNumber, colWeek)).append(" │");
            } else {
                line.append("│ ").append(centerText("", colWeek)).append(" │");
            }


            line.append(" ").append(centerText(dayNumber, colDate))
                    .append(" │ ").append(centerText(dayName, colDay)).append(" │");


            for (int h = 10; h <= 18; h++) {
                String status = getStatusForSlot(day, h, appointments);
                line.append(" ").append(centerText(status, colHour)).append(" │");
            }

            System.out.println(line);


            if (dayOfWeek == DayOfWeek.SUNDAY) {
                System.out.println("└" + "─".repeat(line.length() - 2) + "┘");

                if (i < calendarDateMaxAmountOfDays - 1) {
                    System.out.println("┌" + "─".repeat(line.length() - 2) + "┐");
                }
            }



        }
    }
}
