import java.util.Random;

public class ParallelBuildingSearch {
    static final int MAX_FLOORS = 1000;
    static final int MAX_SIDE_LENGTH = 10;
    static final int NOT_FOUND = -1;
    static final int FOUND = 1;

    private final int[][][] building;
    private final int floorCount;
    private final Random rand = new Random();

    private final int[] results;
    private final Thread[] threads;

    public ParallelBuildingSearch(int floorCount) {
        if (floorCount < 1 || floorCount > MAX_FLOORS) {
            throw new IllegalArgumentException("Floor count must be between 1 and " + MAX_FLOORS);
        }
        this.floorCount = floorCount;
        this.building = new int[floorCount][MAX_SIDE_LENGTH][MAX_SIDE_LENGTH];
        this.results = new int[floorCount];
        this.threads = new Thread[floorCount];
    }

    private void hidePerson() {
        for (int floor = 0; floor < floorCount; floor++) {
            for (int row = 0; row < MAX_SIDE_LENGTH; row++) {
                for (int col = 0; col < MAX_SIDE_LENGTH; col++) {
                    building[floor][row][col] = NOT_FOUND;
                }
            }
        }

        int randomFloor = rand.nextInt(floorCount);
        int randomRow = rand.nextInt(MAX_SIDE_LENGTH);
        int randomCol = rand.nextInt(MAX_SIDE_LENGTH);
        building[randomFloor][randomRow][randomCol] = FOUND;
        int room = randomRow * 10 + randomCol;

        System.err.printf("%n(Hiding location)  Floor: %d  Room: %d%n%n", randomFloor, room);
    }

    private void searchBuilding() {
        for (int floor = 0; floor < floorCount; floor++) {
            final int currentFloor = floor;
            threads[floor] = new Thread(() -> {
                results[currentFloor] = searchFloor(currentFloor);
            });
            threads[floor].start();
        }

        for (int i = 0; i < floorCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            int result = results[i];

            if (result == NOT_FOUND) {
                System.err.printf("(Floor %d) Nobody was found%n", i);
            } else {
                System.err.printf("(Floor %d) Person found in room %d%n", i, result);
                for (int j = i + 1; j < floorCount; j++) {
                    threads[j].interrupt();

                    System.err.println("\nAll other searches cancelled");
                    break;
                }
            }

        }

        
    }

    private Integer searchFloor(int floor) {
        for (int row = 0; row < MAX_SIDE_LENGTH; row++) {
            for (int col = 0; col < MAX_SIDE_LENGTH; col++) {
                if (Thread.currentThread().isInterrupted()) {
                    return NOT_FOUND;
                }
                if (building[floor][row][col] == FOUND) {
                    return row * 10 + col;
                }
            }
        }
        return NOT_FOUND;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
        System.err.println("Usage: java ParallelBuildingSearch <numFloors>");
        return;
        }

        int floors;
        try {
            floors = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Invalid floor count." + args[0]);
            return;
        }

        ParallelBuildingSearch buildingSearch = new ParallelBuildingSearch(floors);
        buildingSearch.rand.setSeed(System.currentTimeMillis());
        buildingSearch.hidePerson();
        buildingSearch.searchBuilding();
    }
}