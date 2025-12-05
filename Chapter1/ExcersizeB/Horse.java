import java.util.Random;

public class Horse implements Runnable {
    private int id;
    private Random random;

    public Horse(int id) {
        this.id = id;
        this.random = new Random();
    }

    @Override
    public void run() {
        int distance = 0;
        while (distance < 100) {
            int stride = random.nextInt(5) + 6;
            distance += stride;
            System.out.println("Horse " + id + " ran " + stride + " : " + distance);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
