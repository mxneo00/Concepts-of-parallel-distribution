package Chapter2.exercizeA;

public class simple {
    static class childThread extends Thread {
        @Override
        public void run() {
            System.out.println("Child thread id: " + this.getId());
        }
    }

    public static void main(String[] args) {
        childThread child = new childThread();
        child.start();
        System.out.println("Main thread id: " + Thread.currentThread().getId() + " (parent of child thread id: " + child.getId() + ")");
    }
}
