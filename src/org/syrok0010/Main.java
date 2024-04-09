package org.syrok0010;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        String lastName = getValidName("фамилию", scanner);
        String name = getValidName("имя", scanner);
        String middleName = getValidName("отчество", scanner);

        LocalDate dateOfBirth = getValidDateOfBirth(scanner);

        System.out.print(
                lastName + ' ' + name.charAt(0) + '.' + middleName.charAt(0) + '.' + '\n' +
                "Пол: " + (middleName.endsWith("на") ? "женский" : "мужской") + '\n' +
                "Возраст: " + getValidAgeString(Period.between(dateOfBirth, LocalDate.now()).getYears())
        );
    }

    private static String getValidAgeString(int age) {
        if (age >= 10 && age <= 20) return age + " лет";
        return switch (age % 10) {
            case 1 -> age + " год";
            case 2, 3, 4 -> age + " года";
            default -> age + " лет";
        };
    }

    private static LocalDate getValidDateOfBirth(Scanner scanner) {
        LocalDate res = null;
        do {
            try {
                System.out.print("Введите дату рождения (дд.мм.гггг):");
                res = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            }
            catch (Exception ignored){
                System.out.println("Дата рождения введена неверно, повторите попытку.");
            }
        } while (res == null || res.isAfter(LocalDate.now()));
        return res;
    }

    private static String getValidName(String nameType, Scanner scanner) {
        String res;
        do {
            System.out.printf("Введите %s: ", nameType);
            res = scanner.next().trim();
        } while (res.isBlank() || !res.chars().allMatch(Character::isLetter));
        return res.substring(0, 1).toUpperCase() + res.substring(1).toLowerCase();
    }
}