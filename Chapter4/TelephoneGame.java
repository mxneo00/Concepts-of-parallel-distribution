public class TelephoneGame {
    public static void main(String[] args) {
        int numberOfPlayers = Integer.parseInt(args[0]);
        String[] message = new String[args.length -1];
        for (int i=1; i<args.length; i++) {
            message[i-1] = args[i];
        }

        Player[] players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player(i);
        }

        String[] currentMessage = message;
        for (int i = 0; i < numberOfPlayers; i++) {
            currentMessage = players[i].receiveMessage(currentMessage);
        }

        System.out.print("Final message: ");
        for (String word : currentMessage) {
            System.out.print(word + " ");
        }
        System.out.println();
    }
}
