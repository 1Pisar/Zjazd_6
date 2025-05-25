import java.io.*;
import java.util.Scanner;

public class Zad_3 {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Użycie: java Zad_3 plik_1.txt plik_2.txt ...");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        for (String nazwa : args) {
            File plik = new File(nazwa);

            System.out.println("===== " + plik.getAbsolutePath() + " =====");

            if (!plik.exists()) {
                System.out.println("Plik nie istnieje: " + nazwa);
                continue;
            }

            // Czytanie zawartości pliku
            try (BufferedReader reader = new BufferedReader(new FileReader(plik))) {
                String linia;
                while ((linia = reader.readLine()) != null) {
                    System.out.println(linia);
                }
            } catch (IOException e) {
                System.out.println("Błąd odczytu: " + e.getMessage());
                continue;
            }

            // Dopisywanie tekstu
            System.out.print("Co dopisać na końcu pliku (lub zostaw puste, by pominąć): ");
            String tekst = scanner.nextLine();

            if (!tekst.isBlank()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(plik, true))) {
                    writer.write(tekst);
                    writer.newLine();
                    System.out.println("Dopisano.");
                } catch (IOException e) {
                    System.out.println("Błąd zapisu: " + e.getMessage());
                }
            } else {
                System.out.println("Pominięto dopisywanie.");
            }

            System.out.println();
        }

        scanner.close();
    }
}
