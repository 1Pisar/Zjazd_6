// Definicja własnego wyjątku
class NiepelnoletniException extends Exception {
    public NiepelnoletniException(String message) {
        super(message);
    }
}

public class Zad_8 {

    public static void main(String[] args) {
        int wiek = 18;

        try {
            sprawdzWiek(wiek);
            System.out.println("Osoba jest pełnoletnia.");
        } catch (NiepelnoletniException e) {
            System.out.println("Błąd: " + e.getMessage());
        } finally {
            System.out.println("Success.");
        }
    }

    // Metoda rzuca wyjątek, jeśli wiek < 18
    public static void sprawdzWiek(int wiek) throws NiepelnoletniException {
        if (wiek < 18) {
            throw new NiepelnoletniException("Osoba jest niepełnoletnia! Wiek: " + wiek);
        }
    }
}
