import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.file.*;
import java.util.Scanner;

public class Zad_6 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj adresy URL (http/https), które chcesz pobrać (wpisz 'koniec', aby zakończyć):");

        while (true) {
            System.out.print("URL: ");
            String linia = scanner.nextLine().trim();
            if (linia.equalsIgnoreCase("koniec")) {
                break;
            }

            try {
                URL url = new URL(linia);
                Path plikDocelowy = ustalNazwePliku(url);
                pobierzPlik(url, plikDocelowy);
                System.out.println("Zapisano do pliku: " + plikDocelowy.toAbsolutePath());
            } catch (MalformedURLException e) {
                System.out.println("Nieprawidłowy adres URL: " + linia);
            } catch (IOException e) {
                System.out.println("Błąd pobierania: " + e.getMessage());
            }
        }
    }

    private static Path ustalNazwePliku(URL url) {
        String path = url.getPath();
        String nazwa = path.substring(path.lastIndexOf('/') + 1);
        if (nazwa.isEmpty()) {
            nazwa = "pobrane_dane.html"; // domyślna nazwa dla stron
        }
        return Paths.get(nazwa);
    }

    private static void pobierzPlik(URL url, Path plikWyjsciowy) throws IOException {
        URLConnection polaczenie = url.openConnection();

        try (InputStream in = polaczenie.getInputStream();
             OutputStream out = Files.newOutputStream(plikWyjsciowy)) {

            byte[] buf = new byte[1024];
            int dl;
            while ((dl = in.read(buf)) > 0) {
                out.write(buf, 0, dl);
            }
        }
    }
}
