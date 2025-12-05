package Chapter2.exercizeB;

public class threads {
    public static void run(String[] args, int fileCount) {
        Thread[] threads = new Thread[fileCount];

        for (int i = 0; i < fileCount; i++) {
            HTMLReader reader = new HTMLReader(i + 1, args[i]);
            threads[i] = new Thread(reader);
            threads[i].start();
        }

        for (int i = 0; i < fileCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All threads have finished.");
    }
}
