package Chapter2.exercizeA;

public class fan {
    static class FanThread extends Thread {
        private final int fanNumber;

        public FanThread(int fanNumber) {
            this.fanNumber = fanNumber;
        }

        @Override
        public void run() {
            System.out.println(
                "Fan number: " + fanNumber + 
                ", Thread ID: " + Thread.currentThread().getId() +
                ", child of Thread ID: (none)" 
            );

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide the number of fan threads to create.");
            return;
        }

        int numberOfThreads = Integer.parseInt(args[0]);

        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new FanThread(i + 1);
            threads[i].start();
        }

        System.out.println(
            "Number of threads created: " + numberOfThreads +
            "Main thread ID: " + Thread.currentThread().getId() + 
            ", Child threads IDs: " + java.util.Arrays.toString(
                java.util.Arrays.stream(threads)
                    .mapToLong(Thread::getId)
                    .toArray()
            )
        );
    }
}
