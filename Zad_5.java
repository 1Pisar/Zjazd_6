import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.*;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Zad_5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ścieżki do plików ZIP do rozpakowania (wpisz 'koniec' aby zakończyć):");

        while (true) {
            System.out.print("Plik ZIP: ");
            String linia = scanner.nextLine().trim();
            if (linia.equalsIgnoreCase("koniec")) {
                break;
            }

            Path sciezkaZip = Paths.get(linia);
            if (!Files.exists(sciezkaZip) || !Files.isRegularFile(sciezkaZip)) {
                System.out.println("Plik nie istnieje lub nie jest plikiem ZIP: " + linia);
                continue;
            }

            rozpakujZip(sciezkaZip, Paths.get("rozpakowane"));
        }
    }

    private static void rozpakujZip(Path zipPath, Path folderDocelowy) {
        System.out.println("Rozpakowywanie: " + zipPath.getFileName());

        try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(zipPath))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                Path sciezkaWyjsciowa = folderDocelowy.resolve(entry.getName());

                if (entry.isDirectory()) {
                    Files.createDirectories(sciezkaWyjsciowa);
                } else {
                    Files.createDirectories(sciezkaWyjsciowa.getParent());
                    try (OutputStream os = Files.newOutputStream(sciezkaWyjsciowa)) {
                        byte[] buf = new byte[1024];
                        int dl;
                        while ((dl = zis.read(buf)) > 0) {
                            os.write(buf, 0, dl);
                        }
                    }
                }
                zis.closeEntry();
            }

            System.out.println("Rozpakowano do: " + folderDocelowy.toAbsolutePath());

        } catch (IOException e) {
            System.err.println("Błąd podczas rozpakowywania " + zipPath.getFileName() + ": " + e.getMessage());
        }
    }
}
