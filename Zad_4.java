import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Zad_4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Path> plikiDoSpakowania = new ArrayList<>();

        System.out.println("Podaj ścieżki do plików do spakowania (wpisz 'koniec' aby zakończyć):");

        while (true) {
            System.out.print("Plik: ");
            String linia = scanner.nextLine().trim();
            if (linia.equalsIgnoreCase("koniec")) {
                break;
            }

            Path sciezka = Paths.get(linia);
            if (Files.exists(sciezka) && Files.isRegularFile(sciezka)) {
                plikiDoSpakowania.add(sciezka);
            } else {
                System.out.println("Plik nie istnieje lub nie jest plikiem: " + linia);
            }
        }

        if (plikiDoSpakowania.isEmpty()) {
            System.out.println("Nie podano żadnych prawidłowych plików do spakowania.");
            return;
        }

        Path archiwum = Paths.get("archiwum.zip");
        try (ZipOutputStream zipOut = new ZipOutputStream(Files.newOutputStream(archiwum))) {
            for (Path plik : plikiDoSpakowania) {
                ZipEntry entry = new ZipEntry(plik.getFileName().toString());
                zipOut.putNextEntry(entry);
                Files.copy(plik, zipOut);
                zipOut.closeEntry();
            }
            System.out.println("Pliki zostały spakowane do: " + archiwum.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Błąd podczas pakowania: " + e.getMessage());
        }
    }
}
