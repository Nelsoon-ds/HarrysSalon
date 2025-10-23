import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class CalendarUI {

    static ArrayList<Appointment> appointmentList = FileHandler.readFromFile();

    static void main(String[] args) {

        for (int i = 0; i < appointmentList.size(); i++) {

        }

    }


    static private void weekCalendar(){

    }



    static private void dateCalendar(){

    }

    public void displayWeeklySchedule() {
        LocalDate userStartDate;
        System.out.println("Enter the start date for the week (e.g., a Monday):");
        userStartDate = inputUserDate();

        System.out.println("\n--- Weekly Schedule starting from " + userStartDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " ---");

        // Print header for hours
        System.out.printf("%-15s", "Date/Day");
        for (int hour = 10; hour <= 18; hour++) { // Assuming 10:00 to 18:00 as fixed hours
            System.out.printf("%-10s", hour + ":00");
        }
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");

        for (int dayOffset = 0; dayOffset < 7; dayOffset++) {
            LocalDate currentDate = userStartDate.plusDays(dayOffset);
            System.out.printf("%-15s", currentDate.format(DateTimeFormatter.ofPattern("EE dd/MM"))); // Date and Day

            for (int hour = 10; hour <= 18; hour++) {
                boolean slotFound = false;
                for (BookingDateTime booking : bookingTimes) {
                    if (booking.compareDates(currentDate) && booking.getDateTime().getHour() == hour) {
                        if (booking.getBookingStatus()) {
                            System.out.printf("%-10s", "Booked"); // Or customer name if space allows
                        } else if (!booking.getAvailability()) {
                            System.out.printf("%-10s", "Blocked");
                        } else {
                            System.out.printf("%-10s", "Avail");
                        }
                        slotFound = true;
                        break; // Found the slot for this hour, move to next hour
                    }
                }
                if (!slotFound) {
                    System.out.printf("%-10s", "---"); // No slot found for this hour
                }
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
    }
}
