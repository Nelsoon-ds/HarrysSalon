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
        int calenderColumnWeekHeader = 8;
        int calenderColumnDateHeader = 10;
        int calenderColumnDayHeader = 12;
        int calenderColumnHourHeader = 10;

        StringBuilder header = new StringBuilder();
        header.append("║ ").append(centerText("Uge", calenderColumnWeekHeader))
                .append(" │ ").append(centerText("Dato", calenderColumnDateHeader))
                .append(" │ ").append(centerText("Ugedag", calenderColumnDayHeader)).append(" │");

        for (int h = 10; h <= 18; h++) {
            header.append(" ").append(centerText(h + ":00", calenderColumnHourHeader)).append(" ║");
        }

        int totalLength = header.length();

        System.out.println("╔" + "═".repeat(totalLength - 2) + "╗");
        System.out.println(header);
        System.out.println("╚" + "═".repeat(totalLength - 2) + "╝");
        System.out.println("┌" + "─".repeat(totalLength - 2) + "┐");
    }


    public static void printWeekCalendar(ArrayList<Appointment> appointments) {
        int calenderColumnWeekBody = 8;
        int calenderColumnDateBody = 10;
        int calenderColumnDayBody = 12;
        int calenderColumnHourBody = 10;

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
                line.append("│ ").append(centerText("Uge " + weekNumber, calenderColumnWeekBody)).append(" │");
            } else {
                line.append("│ ").append(centerText("", calenderColumnWeekBody)).append(" │");
            }


            line.append(" ").append(centerText(dayNumber, calenderColumnDateBody))
                    .append(" │ ").append(centerText(dayName, calenderColumnDayBody)).append(" │");


            for (int h = 10; h <= 18; h++) {
                String status = getStatusForSlot(day, h, appointments);
                line.append(" ").append(centerText(status, calenderColumnHourBody)).append(" │");
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
