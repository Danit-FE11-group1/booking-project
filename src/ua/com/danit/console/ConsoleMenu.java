package ua.com.danit.console;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public class ConsoleMenu {
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
                    flightsList();
                    boolean stopMenuFlights = false;
                    while (!stopMenuFlights) {
                        Scanner scanner2 = new Scanner(System.in);
                        String input2 = scanner2.nextLine();
                        switch (input2) {
                            case "0":
                                stopMenuFlights = true;
                                break;
                            default:
//                              // добавить в flightsBooking колличество итераций с searchBooking
                                safeCall(ConsoleMenu::flightsBooking, scanner2);
                                stopMenuFlights = true;
                        }
                    }
                    break;
                case "4":
                    safeCall(ConsoleMenu::cancelBooking, scanner);
                    break;
                case "5":
                    safeCall(ConsoleMenu::myFlights, scanner);
                    break;
                case "6":
                    stopProgram = true;
                    break;
                default:
                    System.out.println("Неизвестная команда");
            }
        }
    }
    private static void scoreboardOnline(Scanner scanner) {
        System.out.println("Онлайн-табло");
//        add code here Online Scoreboard connect to  DB
        System.out.println("ДОБАВИТЬ ВЫВОД Всех рейсов из Киева в ближайшие 24 часа");
    }

    private static void flightsInfo(Scanner scanner) {
        System.out.println("Посмотреть информацию о рейсе");
        System.out.println("Введите номер рейса");
        Integer customerId = readTyped(scanner, Integer::parseInt, "Не номер рейса, попробуйте еще раз");
//        add code here Flight Information connect to  DB
        System.out.println("Номер рейса: " + customerId + " Дата: " + " Время: " + " Место назначения: " + " Количество свободных мест: " );
    }

    private static void searchBooking(Scanner scanner) {
        System.out.println("Поиск и бронировка рейса");
        System.out.println("Место назначения");
        String flightPlace= readTyped(scanner, String::valueOf, "Не место, попробуйте еще раз");
        System.out.println("Введите дату (dd.mm.yyyy):");
//      ?????? доработаь проверку даты и ее печать пока стоит как стринга
        String flightDate = readTyped(scanner, String::valueOf, "Не дата, попробуйте еще раз");
        System.out.println("Количество человек");
        Integer flightPassengerQty = readTyped(scanner, Integer::parseInt, "Не число, попробуйте еще раз");
        System.out.println("Список подходящих рейсов: в город " + flightPlace +" - на "+ flightDate +" - для "+ flightPassengerQty + " человек");
    }
    private static void flightsList() {
        //      заменить flights на ответ с DB
        System.out.println("0. Выход");
        List<String> flights = Arrays.asList("2343", "3453", "3453", "4534");
        int index = 1;
        for (String s : flights) {
            System.out.println((index++) + ". " + s);
        }
    }
    private static void flightsBooking(Scanner scanner) {
        // change 2 на flightPassengerQty from searchBooking
        for(int i = 0; i < 2; i++) {
            System.out.println("Ведите имя");
            String passengerName= readTyped(scanner, String::valueOf, "Не имя, попробуйте еще раз");
            System.out.println("Ведите фамилию");
            String passengerSurname= readTyped(scanner, String::valueOf, "Не фамилия, попробуйте еще раз");
            //Есть возможность добавить № бронирования ???
            System.out.println("Вы забронировали рейс"+ "- для " + passengerName + "- " + passengerSurname + "# бронирования");
        }
     }
    private static void cancelBooking(Scanner scanner) {
        System.out.println("Отменить бронирование");
        System.out.println("Введите номер бронирования");
        Integer bookingNumber = readTyped(scanner, Integer::parseInt, "Не число, попробуйте еще раз");
//      add code here delete booking in BD return conformation from BD
        System.out.println("Ваша бронь " + bookingNumber + " была отменена");
    }
    private static void myFlights(Scanner scanner) {
        System.out.println("Мои рейсы");
        System.out.println("Ведите имя");
        String accountName= readTyped(scanner, String::valueOf, "Не имя, попробуйте еще раз");
        System.out.println("Ведите фамилию");
        String accountSurname= readTyped(scanner, String::valueOf, "Не фамилия, попробуйте еще раз");
//      add code here return list of bookings from BD
        System.out.println( accountName + accountSurname + " ваш список бронирований: ");
    }
    private static void safeCall(Consumer<Scanner> consumer, Scanner scanner) {
        try {
            consumer.accept(scanner);
        } catch (Exception e) {
            System.err.printf("Произошла ошибка: %s%n", e.getMessage());
        }
    }
    private static void printMenuItems() {
        System.out.println("Введите команду");
        System.out.println("1. Онлайн-табло");
        System.out.println("2. Посмотреть информацию о рейсе");
        System.out.println("3. Поиск и бронировка рейса");
        System.out.println("4. Отменить бронирование");
        System.out.println("5. Мои рейсы");
        System.out.println("6. Выход");
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
