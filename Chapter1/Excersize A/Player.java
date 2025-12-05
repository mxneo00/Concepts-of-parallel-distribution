import java.util.Random;

public class Player {
    private int id;
    private Random random;

    public Player(int id) {
        this.id = id;
        this.random = new Random();
    }

    public String[] receiveMessage(String[] message) {
        String[] newMessage = message.clone();

        int indexToChange1 = random.nextInt(newMessage.length);
        int indexToChange2 = random.nextInt(newMessage.length);
        String temp = newMessage[indexToChange1];
        newMessage[indexToChange1] = newMessage[indexToChange2];
        newMessage[indexToChange2] = temp;

        return newMessage;
    }
}
