import java.util.Random;

public class Message {
    public static String changeMessage(String message) {
        String[] words = message.split("\\s+");
        Random rand = new Random();
        int i = rand.nextInt(words.length);
        int j = rand.nextInt(words.length);
        while (i == j) {
            j = rand.nextInt(words.length);
        }
        String temp = words[i];
        words[i] = words[j];
        words[j] = temp;
        return String.join(" ", words);
    }
}