
import java.util.Random;

public class BingoGame {
    static final int MAX_SIZE = 5;
    static final int MAX_ITERATIONS = 10;

    static class BingoCell {
        int number;
        boolean isMarked;
    }

    private final BingoCell[][] card = new BingoCell[MAX_SIZE][MAX_SIZE];

    public static void main(String[] args) {
        new BingoGame().run();
    }

    private void run() {
        initializeCard();
        printCard();

        for (int i = 1; i <= MAX_ITERATIONS; i++) {
            final int choice = i;
            final int threadIdx = 1;
            Thread thread = new Thread(() -> {
                long osPid = ProcessHandle.current().pid();
                long simPid = osPid + threadIdx;

                switch (choice) {
                    case 1:
                        checkForBingoFirstRow(simPid);
                        break;
                    case 2:
                        checkForDiagonalBingoLR(simPid);
                        break;
                    default:
                        System.out.printf("%n(%d) Unknown choice: %d%n", simPid, choice);
                        break;
                }
            });
            thread.start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    private void initializeCard() {
        Random rand = new Random();
        for (int row = 0; row < MAX_SIZE; row++) {
            for (int col = 0; col < MAX_SIZE; col++) {
                int baseNbr = (row + 1) + (col * 15);
                int randNbr = baseNbr + rand.nextInt(11);
                int cellNbr = baseNbr + randNbr;

                BingoCell cell = new BingoCell();
                cell.number = cellNbr;
                if (cellNbr % 3 == 0 || cellNbr % 5 == 0 || cellNbr % 7 == 0) {
                    cell.isMarked = true;
                } else {
                    cell.isMarked = false;
                }
                card[row][col] = cell;
            }
        }
    }

    private void printCard() {
        System.out.println(" B    I    N    G    O");
        System.out.println(" --   --   --   --   --");
        for (int row = 0; row < MAX_SIZE; row++) {
            for (int col = 0; col < MAX_SIZE; col++) {
                BingoCell cell = card[row][col];
                if (cell.isMarked) {
                    System.out.printf("(");
                } else {
                    System.out.printf(" ");
                }

                if (row == 2 && col == 2) {
                    System.out.printf("**");
                } else {
                    System.out.printf("%2d ", cell.number);
                }

                if (cell.isMarked) {
                    System.out.printf(")");
                } else {
                    System.out.printf(" ");
                }
            }
            System.out.println();
        }
    }

    private void checkForBingoFirstRow(long displayPid) {
        int cellCount = 0;
        for (int col = 0; col < MAX_SIZE; col++) {
            if (card[0][col].isMarked) {
                cellCount++;
            }
        }
        if (cellCount == 5) {
            System.out.printf("%n(%d) !!!CONGRATULATIONS!!! There is a Bingo in the first row%n", displayPid);
        } else {
            System.out.printf("%n(%d) There is no Bingo in the first row%n", displayPid);
        }
    }

    private void checkForDiagonalBingoLR(long displayPid) {
        int cellCount = 0;
        if (card[0][0].isMarked) {
            cellCount++;
        }
        if (card[1][1].isMarked) {
            cellCount++;
        }
        if (card[2][2].isMarked) {
            cellCount++;
        }
        if (card[3][3].isMarked) {
            cellCount++;
        }
        if (card[4][4].isMarked) {
            cellCount++;
        }

        if (cellCount == 5) {
            System.out.printf("%n(%d) !!!CONGRATULATIONS!!! There is a Bingo in the LR diagonal%n", displayPid);
        } else {
            System.out.printf("%n(%d) There is no Bingo in the LR diagonal%n", displayPid);
        }
    }
}
