import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


//
public class Zad_1 {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Użycie: java Zad_1 plik_1.txt plik_2.txt ...");
            return;
        }

        for (String nazwaPliku : args) {
            Path sciezka = Paths.get(nazwaPliku);
            System.out.println("====== Zawartość pliku: " + sciezka.toAbsolutePath() + " ======");
            try {
                List<String> linie = Files.readAllLines(sciezka);
                linie.forEach(System.out::println);
            } catch (IOException e) {
                System.out.println("Błąd podczas odczytu pliku: " + nazwaPliku);
                System.out.println("Szczegóły: " + e.getMessage());
            }
            System.out.println();
        }
    }
}
