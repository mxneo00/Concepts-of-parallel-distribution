package Chapter2.exercizeB;

public class HTMLReader implements Runnable {
    private final int threadNumber;
    private String fileName;

    public HTMLReader(int threadNumber, String fileName) {
        this.threadNumber = threadNumber;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        int tagCount = 0;
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tagCount += countTags(line);
            }

            System.out.println(
                "Thread " + threadNumber + 
                ": File Name " + fileName + 
                " Tags: " + tagCount
            );
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    
    private int countTags(String line) {
        int count = 0;
        int index = 0;
        while ((index = line.indexOf("<", index)) != -1) {
            count++;
            index++;
        }
        return count;
    }
}
