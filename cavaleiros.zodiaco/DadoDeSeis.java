import java.util.Random;

public class DadoDeSeis implements Sorteador {
    public int sortear() {
        final Random random = new Random();
        final int min = 1, max = 6;
        return random.nextInt((max - min) + 1) + min;
    }
}