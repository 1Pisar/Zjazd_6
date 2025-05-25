import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

public class Zad_2 {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Użycie: java Zad_2 plik_1.txt plik_2.txt ...");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        for (String nazwa : args) {
            Path plik = Paths.get(nazwa);

            System.out.println("===== " + plik.toAbsolutePath() + " =====");

            try {
                List<String> linie = Files.readAllLines(plik, StandardCharsets.UTF_8);
                linie.forEach(System.out::println);

                // Zapytanie użytkownika o tekst do dopisania
                System.out.print("Co dopisać na końcu pliku (lub zostaw puste, by pominąć): ");
                String tekst = scanner.nextLine();

                if (!tekst.isBlank()) {
                    Files.writeString(plik, tekst + System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                    System.out.println("Dopisano.");
                } else {
                    System.out.println("Pominięto dopisywanie.");
                }

            } catch (IOException e) {
                System.out.println("Błąd pliku: " + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }
}
