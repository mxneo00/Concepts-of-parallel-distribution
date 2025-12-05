package Chapter2.exercizeB;

public class Main {
    private static final int MAX_FILE_COUNT = 3;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide file names.");
            return;
        }

        int fileCount = Math.min(args.length, MAX_FILE_COUNT);

        String[] fileNames = new String[FILE_COUNT];
        for (int i = 0; i < FILE_COUNT; i++) {
            fileNames[i] = args[i];
        }

        threads.run(args, FILE_COUNT);
    }
}
