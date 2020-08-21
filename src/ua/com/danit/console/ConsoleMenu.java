package ua.com.danit.console;

import ua.com.danit.controller.BookController;
import ua.com.danit.controller.FlightController;
import ua.com.danit.dao.booking.CollectionBookingDao;
import ua.com.danit.dao.flight.CollectionFlightDao;
import ua.com.danit.entity.Flight;
import ua.com.danit.exception.FlightException;
import ua.com.danit.service.BookingService;
import ua.com.danit.service.FlightService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class ConsoleMenu {
    public static CollectionFlightDao collectionFlightDao = new  CollectionFlightDao();
    public static FlightService flightService = new FlightService(collectionFlightDao);
    public static FlightController flightController = new  FlightController(flightService);
    public static CollectionBookingDao collectionBookingDao = new CollectionBookingDao();
    public static BookingService bookingService = new BookingService(collectionBookingDao);
    public static BookController bookController = new BookController(bookingService, flightController);

    static {
        flightController.uploadFlights();
    }
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        boolean stopProgram = false;
        while (!stopProgram) {
            printMenuItems();
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    safeCall(ConsoleMenu::scoreboardOnline, scanner);
                    break;
                case "2":
                    safeCall(ConsoleMenu::flightsInfo, scanner);
                    break;
                case "3":
                    safeCall(ConsoleMenu::searchBooking, scanner);
                    break;
                case "4":
                    safeCall(ConsoleMenu::cancelBooking, scanner);
                    break;
                case "5":
                    safeCall(ConsoleMenu::myFlights, scanner);
                    break;
                case "6":
                    stopProgram = true;
                    flightController.loadFlights();
                    break;
                default:
                    System.out.println("Unknown input");
            }
        }
    }
    private static void scoreboardOnline(Scanner scanner) {
        System.out.println("Online scoreboard");
       List <Flight> flights = flightController.getDayFlights();
       flights.forEach(System.out::println);
    }

    private static void flightsInfo(Scanner scanner) {
        System.out.println("View flight information");
        System.out.println("Enter flight number");
        Long flightId = readTyped(scanner, Long::parseLong, "Not flight number, please try again");
        String flight = flightController.getFlightInfo(flightId);
        System.out.println(flight);
    }

    private static void searchBooking(Scanner scanner) {
        System.out.println("Search and book a flight");
        System.out.println("Departure");
        String homePlace = readTyped(scanner, String::valueOf, "No place, try again");
        System.out.println("Destination");
        String flightPlace= readTyped(scanner, String::valueOf, "No place, try again");
        System.out.println("Enter Date (dd.mm.yyyy):");
        String flightDate = readTyped(scanner, String::valueOf, "Not date, try again");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Long date = 0l;
        try {
            date = simpleDateFormat.parse(flightDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Number of persons");
        Integer flightPassengerQty = readTyped(scanner, Integer::parseInt, "Not a number, try again");
        List<Flight> flights = flightController.searchFlights(homePlace, flightPlace, date, flightPassengerQty);
        System.out.println("0. Exit");
        int index = 1;
        for (Flight item: flights) {
            System.out.println((index++) + ". " + item);
        }
        System.out.println("Enter chosen option");
        Integer option = readTyped(scanner, Integer::parseInt, "Not a number, try again");
        Flight flight = flights.get(option - 1);
        if (option == 0) {
            return;
        }
        for(int i = 0; i < flightPassengerQty; i++) {
            System.out.println("Enter Name");
            String passengerName= readTyped(scanner, String::valueOf, "Not a name, try again");
            System.out.println("Enter Surname");
            String passengerSurname= readTyped(scanner, String::valueOf, "Not a surname, try again");
            try {
                bookController.registerPassengerForFlight(passengerName, passengerSurname, flight);
            } catch (FlightException e) {
                e.printStackTrace();
            }
            System.out.println(bookController.getRegistrationsByPassanger(passengerName, passengerSurname));
        }
    }
    private static void cancelBooking(Scanner scanner) {
        System.out.println("Cancel booking");
        System.out.println("Enter booking number");
        String bookingNumber = readTyped(scanner, String::valueOf, "Not a number, try again");
        bookController.cancelRegistrationForFlight(bookingNumber);
        System.out.println("Your booking number " + bookingNumber + " was canceled");
    }
    private static void myFlights(Scanner scanner) {
        System.out.println("My flights");
        System.out.println("Enter Name");
        String accountName= readTyped(scanner, String::valueOf, "Not a name, try again");
        System.out.println("Enter Surname");
        String accountSurname= readTyped(scanner, String::valueOf, "Not a surname, try again");
        bookController.getRegistrationsByPassanger(accountName, accountSurname);
        System.out.println("List of your flights: " + accountName + " " + accountSurname);
        System.out.println(bookController.getRegistrationsByPassanger(accountName, accountSurname));
    }
    private static void safeCall(Consumer<Scanner> consumer, Scanner scanner) {
        try {
            consumer.accept(scanner);
        } catch (Exception e) {
            System.err.printf("An error has occurred: %s%n", e.getMessage());
        }
    }
    private static void printMenuItems() {
        System.out.println("Enter the command");
        System.out.println("1. Online scoreboard");
        System.out.println("2. View flight information");
        System.out.println("3. Search and book a flight");
        System.out.println("4. Cancel booking");
        System.out.println("5. My flights");
        System.out.println("6. Exit");
    }
    private static <T> T readTyped(Scanner scanner, Function<String, T> func, String errMessage) {
        T tValue = null;

        do {
            String input = scanner.nextLine();

            try {
                tValue = func.apply(input);
            } catch (Exception e) {
                System.out.println(errMessage);
            }
        } while (tValue == null);

        return tValue;
    }
}
