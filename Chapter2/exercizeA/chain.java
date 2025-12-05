package Chapter2.exercizeA;

public class chain {
    static class ChainThread extends Thread {
        private final int threadNumber;
        private final int maxThreads;

        public ChainThread(int threadNumber, int maxThreads) {
            this.threadNumber = threadNumber;
            this.maxThreads = maxThreads;
        }

        @Override
        public void run() {
            Thread child = null;

            if (threadNumber < maxThreads) {
                ChainThread nextThread = new ChainThread(threadNumber + 1, maxThreads);
                nextThread.start();
                try {
                    nextThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(
                "Thread number: " + threadNumber + 
                ", Thread ID: " + Thread.currentThread().getId() +
                ", Child Thread ID: " + (child != null ? child.getId() : "none")
            );

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 0) {
            System.out.println("Please include the number of threads as an argument.");
            return;
        }
        int numberOfThreads = Integer.parseInt(args[0]); 
        ChainThread firstThread = new ChainThread(1, numberOfThreads);
        firstThread.start();
    }
}
