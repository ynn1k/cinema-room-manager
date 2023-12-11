package cinema;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Cinema {
    static int[][] cinema;
    
    static int[][] createCinemaRoom(int rows, int rowSeats) {
        return new int[rows][rowSeats];
    }

    static void printCinemaRoom() {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int s = 0; s < cinema[0].length; s++) {
            System.out.print(" "+(s+1));
        }
        System.out.println();
        for (int r = 0; r < cinema.length; r++) {
            System.out.print(r+1);
            for (int s = 0; s < cinema[r].length; s++) {
                if (cinema[r][s] == 1) {
                    System.out.print(" B");
                } else {
                    System.out.print(" S");
                }
            }
            System.out.println();
        }
    }

    static int getTicketPrice(int row, int[][] cinema) {
        int seats = cinema.length * cinema[0].length;
        int price = 0;

        if (seats > 60 && row >= (cinema.length / 2)) {
            price = 8;
        } else {
            price = 10;
        }

        return price;
    }
    
    static void buyTicket() {
        Scanner scanner = new Scanner(System.in);
        boolean available = false;
        while(!available) {
            System.out.println("Enter a row number:");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = scanner.nextInt();

            try {
                if(cinema[row-1][seat-1] == 1) {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    cinema[row-1][seat-1] = 1;
                    System.out.println("Ticket price: $"+getTicketPrice(row-1, cinema));
                    available = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong input!");
            }
        }
    }

    static void printStatistics() {
        int purchased = 0;
        int currentIncome = 0;
        int maxIncome = 0;
        int seats = cinema.length * cinema[0].length;

        for (int r = 0; r < cinema.length; r++) {
            for (int s = 0; s < cinema[r].length; s++) {
                if(cinema[r][s] == 1) {
                    purchased++;
                    currentIncome += getTicketPrice(r,cinema);
                }

                maxIncome += getTicketPrice(r,cinema);
            }
        }

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        System.out.println("Number of purchased tickets: " + purchased);
        System.out.println("Percentage: " + (decimalFormat.format( ((double) purchased / seats) * 100)) + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + maxIncome);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int rowSeats = scanner.nextInt();

        cinema = createCinemaRoom(rows, rowSeats);

        while (true) {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    printCinemaRoom();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    printStatistics();
                    break;
                case 0:
                    return;
            }
        }
    }
}