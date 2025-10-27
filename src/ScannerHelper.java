import java.util.Scanner;

public class ScannerHelper {
    private Scanner sc;

    //Constructor
    public ScannerHelper() {
        this.sc = new Scanner(System.in);
    }


    public int selectUserOption() {
        boolean numCorrect = false;
        int selectInt = 0;
        int selectIntMax = 3;

            System.out.println("");
            System.out.println("    ******************************************");
            System.out.println("    *     💈H A R R Y ' S   S A L O N 💈     *");
            System.out.println("    *----------------------------------------*");
            System.out.println("    *     ✂️  CUTS | SHAVES | STYLES ✂️      *");
            System.out.println("    ******************************************\n");




        System.out.println("\nVælg bruger:");
        System.out.println("1: Harriet\uD83D\uDC69\uD83C\uDFFD\u200D\uD83E\uDDB1");
        System.out.println("2: Harry\uD83D\uDC68\uD83C\uDFFB");
        System.out.println("3: Revisor\uD83D\uDCBC");
        System.out.print("Indtast tal (1-3): ");

        while (!numCorrect) {

            if (sc.hasNextInt()) {
                selectInt = sc.nextInt();
                sc.nextLine();

                if (selectInt < 0) {
                    System.out.println("Du har indtastet et negativt tal. Prøv igen");
                } else if (selectInt == 0) {
                    System.out.println("0 er ikke en mulighed. Prøv igen");
                } else if (selectInt > selectIntMax) {
                    System.out.println("Du kan ikke indtaste tal større end " + selectIntMax + ". Prøv igen");
                } else {
                    numCorrect = true;
                }
            } else {
                System.out.println("Det er ikke et tal. Prøv igen");
                sc.nextLine(); // rydder forkert input
            }
        }
        return selectInt;
    }

    public int selectHarrietMenuOption() {
        boolean numCorrect = false;
        int selectInt = 0;
        int selectIntMax = 7;


        System.out.println("\nVælg funktion:");
        System.out.println("1: Opret booking");
        System.out.println("2: Slet booking");
        System.out.println("3: Rediger booking");
        System.out.println("4: Se kalender");
        System.out.println("5: Se bookings på dato");
        System.out.println("6: Skift bruger");
        System.out.println("7: Luk program");
        System.out.print("Indtast tal (1-7): ");

        while (!numCorrect) {

            if (sc.hasNextInt()) {
                selectInt = sc.nextInt();
                sc.nextLine();

                if (selectInt < 0) {
                    System.out.println("Du har indtastet et negativt tal. Prøv igen");
                } else if (selectInt == 0) {
                    System.out.println("0 er ikke en mulighed. Prøv igen");
                } else if (selectInt > selectIntMax) {
                    System.out.println("Du kan ikke indtaste tal større end " + selectIntMax + ". Prøv igen");
                } else {
                    numCorrect = true;
                }
            } else {
                System.out.println("Det er ikke et tal. Prøv igen");
                sc.nextLine(); // rydder forkert input
            }
        }
        return selectInt;
    }


    public int selectHarryMenuOption() {
        boolean numCorrect = false;
        int selectInt = 0;
        int selectIntMax = 9;

        System.out.println("\nVælg funktion:");
        System.out.println("1: Opret booking");
        System.out.println("2: Slet booking");
        System.out.println("3: Rediger booking");
        System.out.println("4: Se kalender");
        System.out.println("5: Se bookings på dato");
        System.out.println("6: Se salgsstatistik");
        System.out.println("7: Udskriv alle bookings");
        System.out.println("8: Skift bruger");
        System.out.println("9: Luk program");
        System.out.print("Indtast tal (1-9): ");

        while (!numCorrect) {

            if (sc.hasNextInt()) {
                selectInt = sc.nextInt();
                sc.nextLine();

                if (selectInt < 0) {
                    System.out.println("Du har indtastet et negativt tal. Prøv igen");
                } else if (selectInt == 0) {
                    System.out.println("0 er ikke en mulighed. Prøv igen");
                } else if (selectInt > selectIntMax) {
                    System.out.println("Du kan ikke indtaste tal større end " + selectIntMax + ". Prøv igen");
                } else {
                    numCorrect = true;
                }
            } else {
                System.out.println("Det er ikke et tal. Prøv igen");
                sc.nextLine(); // rydder forkert input
            }
        }
        return selectInt;
    }

    public int selectRevisorMenuOption() {
        boolean numCorrect = false;
        int selectInt = 0;
        int selectIntMax = 4;

        System.out.println("\nVælg funktion:");
        System.out.println("1: Se salgsstatistik");
        System.out.println("2: Udskriv alle bookings");
        System.out.println("3: Skift bruger");
        System.out.println("4: Luk program");
        System.out.print("Indtast tal (1-4): ");

        while (!numCorrect) {

            if (sc.hasNextInt()) {
                selectInt = sc.nextInt();
                sc.nextLine();

                if (selectInt < 0) {
                    System.out.println("Du har indtastet et negativt tal. Prøv igen");
                } else if (selectInt == 0) {
                    System.out.println("0 er ikke en mulighed. Prøv igen");
                } else if (selectInt > selectIntMax) {
                    System.out.println("Du kan ikke indtaste tal større end " + selectIntMax + ". Prøv igen");
                } else {
                    numCorrect = true;
                }
            } else {
                System.out.println("Det er ikke et tal. Prøv igen");
                sc.nextLine(); // rydder forkert input
            }
        }
        return selectInt;
    }
}