import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Zad_7 {

    public static void main(String[] args) {
        int szerokosc = 600;
        int wysokosc = 300;

        BufferedImage obraz = new BufferedImage(szerokosc, wysokosc, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = obraz.createGraphics();

        // Tło
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, szerokosc, wysokosc);

        // Ustawienia czcionki
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.setColor(Color.WHITE);

        // Treść
        String imie = "Bartłomiej";
        String nazwisko = "Pisarek";
        String indeks = "168289";

        int y = 100;
        g2d.drawString("Imię: " + imie, 50, y);
        g2d.drawString("Nazwisko: " + nazwisko, 50, y + 40);
        g2d.drawString("Nr indeksu: " + indeks, 50, y + 80);

        // Zakończenie rysowania
        g2d.dispose();

        // Zapis pliku
        try {
            File plik = new File("moj_obraz.png");
            ImageIO.write(obraz, "png", plik);
            System.out.println("Obraz został zapisany jako: " + plik.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Błąd zapisu obrazu: " + e.getMessage());
        }
    }
}
