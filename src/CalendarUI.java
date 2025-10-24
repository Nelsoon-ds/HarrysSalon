import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Locale;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Scanner;

public class CalendarUI {

    private final Locale locale;

    // ikoner til visning i kalenderen:
    String weekendFillerIcon = "- * -";
    String bookedTimeSlotIcon = "[  X  ]";
    String freeTimeSlotIcon = "( ledig )";

    // antal dage/datoer der vises i kalenderen:
    int calendarDateMaxAmountOfDays = 21;

    //brede til variabler til Header
    int calenderColumnWeekHeader = 8;
    int calenderColumnDateHeader = 12;
    int calenderColumnDayHeader = 12;
    int calenderColumnHourHeader = 10;


    //brede til variabler til Body
    int calenderColumnWeekBody = 8;
    int calenderColumnDateBody = 12;
    int calenderColumnDayBody = 12;
    int calenderColumnHourBody = 10;

    public CalendarUI() {
        this.locale = new Locale("da", "DK");
    }

    public CalendarUI(Locale locale) {
        this.locale = locale;
    }

    //metode til centering af text
    private String centerText(String text, int width) {
        if (text.length() >= width) return text;
        int padding = width - text.length();
        int left = padding / 2;
        int right = padding - left;
        return " ".repeat(left) + text + " ".repeat(right);
    }

    //metode til status af ledige tider / checker om der er en appointment på given dato.
    private String getStatusForSlot(LocalDate date, int hour, ArrayList<Appointment> appointments) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return weekendFillerIcon;
        }

        for (Appointment appt : appointments) {
            if (appt.getDate().equals(date) && appt.getTime().getHour() == hour) {
                return bookedTimeSlotIcon;
            }
        }
        return freeTimeSlotIcon;
    }

    //udprint af CalendarHeader
    public void printCalendarHeader() {

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


    // Udprint af CalendarBody
    public void printWeekCalendar(ArrayList<Appointment> appointments) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate today = LocalDate.now();
        LocalDate monday = today.minusDays(today.getDayOfWeek().getValue() - 1);

        for (int i = 0; i < calendarDateMaxAmountOfDays; i++) {
            LocalDate day = monday.plusDays(i);
            DayOfWeek dayOfWeek = day.getDayOfWeek();

            String dayName = dayOfWeek.getDisplayName(TextStyle.FULL, locale);
            String dayNumber = day.format(dateFormatter);
            String monthName = day.getMonth().getDisplayName(TextStyle.FULL, locale);
            int weekNumber = day.get(WeekFields.of(locale).weekOfWeekBasedYear());

            //marker dato "i dag".
            if (day.equals(LocalDate.now())) {
                dayName = "> " + dayName.toUpperCase() + " <";
                dayNumber = ">" + dayNumber + "<";
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

            // sikre at der ikke bliver printet ekstra linjer, hvis der ikke er en uge.
            if (dayOfWeek == DayOfWeek.SUNDAY) {
                System.out.println("└" + "─".repeat(line.length() - 2) + "┘");

                if (i < calendarDateMaxAmountOfDays - 1) {
                    System.out.println("┌" + "─".repeat(line.length() - 2) + "┐");
                }
            }
        }
    }

    public void setCalendarDateMaxAmountOfDays(int days) {
        this.calendarDateMaxAmountOfDays = days;
    }

    public void setIcons(String weekend, String booked, String free) {
        this.weekendFillerIcon = weekend;
        this.bookedTimeSlotIcon = booked;
        this.freeTimeSlotIcon = free;
    }


    public void viewCalendarForSpecificDate(ArrayList<Appointment> appointments) {
        Scanner input = new Scanner(System.in);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = null;

        while (date == null) {
            System.out.println("Indtast dato for at se aftaler på den ønskede dato (dd-MM-yyyy):");
            String userDateInput = input.nextLine();

            try {
                date = LocalDate.parse(userDateInput, dateFormatter);
            } catch (Exception e) {
                System.out.println("Fejl: forkert dato format, brug kun 'dd/MM/yyyy'!");
                continue;
            }

            System.out.println("\nAftaler for dato: " + date.format(dateFormatter) + "\n");

            for (Appointment appointment : appointments) {
                if (appointment.getDate().equals(date)) {
                    System.out.println(appointment);
                }
            }
        }
    }
}
