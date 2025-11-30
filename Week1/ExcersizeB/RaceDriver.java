public class RaceDriver {
    public static void main(String[] args) {
        int numHorses = 2;
        if (args.length == 1) {
            try {
                int input  = Integer.parseInt(args[0]);
                if (input >= 2 && input <=5) {
                    numHorses = input;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number of horses. Using default of 2.");
            }
        }

        Thread[] horses = new Thread[numHorses];

        for (int i = 0; i < numHorses; i++) {
            horses[i] = new Thread(new Horse(i+1));
            horses[i].start();
        }
    }
}
