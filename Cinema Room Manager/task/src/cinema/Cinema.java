package cinema;

import java.util.Scanner;

public class Cinema {

    private static final Scanner scanner = new Scanner(System.in);
    private static final char SEAT = 'S';
    private static final char OCCUPIED_SEAT = 'B';
    private static char[][] cinema;
    private static final int FIRST_HALF_TICKET = 10;
    private static final int SECOND_HALF_TICKET = 8;
    private static int currentIncome;
    private static int purchasedTickets;
    private static int totalIncome;

    public static void main(String[] args) {
        createCinema();
        int action;
        do {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            action = scanner.nextInt();
            System.out.println();
            switch (action) {
                case 0:
                    break;
                case 1:
                    showTheSeats(cinema);
                    break;
                case 2:
                    buyTicket(cinema);
                    break;
                case 3:
                    showStatistics(cinema);
                    break;
            }
        } while (action != 0);
    }

    public static void showTheSeats(char[][] cinema) {

        System.out.printf("Cinema:%n ");
        for (int i = 1; i <= cinema[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < cinema.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < cinema[0].length; j++) {
                System.out.print(" " + cinema[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void buyTicket(char[][] cinema) {

        int rowNumber;
        int seatNumber;

        while (true) {
            System.out.println("Enter a row number:");
            rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt();
            if (rowNumber > cinema.length || seatNumber > cinema[0].length) {
                System.out.println("Wrong input!");
            } else {
                if (cinema[rowNumber - 1][seatNumber - 1] == OCCUPIED_SEAT) {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    cinema[rowNumber - 1][seatNumber - 1] = OCCUPIED_SEAT;
                    break;
                }
            }
        }

        int ticketPrice;
        if (cinema.length * cinema[0].length <= 60) {
            ticketPrice = FIRST_HALF_TICKET;
        } else {
            if (rowNumber <= (cinema.length / 2)) {
                ticketPrice = FIRST_HALF_TICKET;
            } else {
                ticketPrice = SECOND_HALF_TICKET;
            }
        }

        currentIncome += ticketPrice;
        purchasedTickets++;

        System.out.println("Ticket price: $" + ticketPrice);
        System.out.println();
    }

    public static void createCinema() {

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        System.out.println();
        cinema = new char[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = SEAT;
            }
        }

        int totalSeats = rows * seats;
        if (totalSeats <= 60) {
            totalIncome = FIRST_HALF_TICKET * totalSeats;
        } else {
            int firstHalf = seats * (rows / 2);
            int secondHalf = totalSeats - firstHalf;
            totalIncome = FIRST_HALF_TICKET * firstHalf + (SECOND_HALF_TICKET) * secondHalf;
        }
    }

    public static void showStatistics(char[][] cinema) {

        double allSeats = cinema.length * cinema[0].length;
        double percentageOfOccupancy = 100 * purchasedTickets / allSeats;
        System.out.printf("Number of purchased tickets: %d%n" +
                        "Percentage: %.2f%%%n" +
                        "Current income: $%d%n" +
                        "Total income: $%d%n", purchasedTickets, percentageOfOccupancy,
                currentIncome, totalIncome);
    }
}