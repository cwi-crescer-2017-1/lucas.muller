import java.util.Random;

public class Porcentagem implements Sorteador {
    public int sortear() {
        final Random random = new Random();
        return random.nextInt(100);
    }
}