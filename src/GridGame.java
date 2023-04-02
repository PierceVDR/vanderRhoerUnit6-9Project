import java.util.Scanner;

public class GridGame {
    private static final Scanner SCAN = new Scanner(System.in);
    private static final String CROSS = Letters.CROSS;
    private static final String HORIZONTAL = Letters.HORIZONTAL;
    private static final String VERTICAL = Letters.VERTICAL;
    private static final String SPACE = Letters.SPACE;


    private Tile[][] grid;
    private boolean over;

    public GridGame() {} // To be used by runner

    public GridGame(int size) { // To be used by subclasses with square grids
        grid = new Tile[size][size];
        over=false;
    }
    public GridGame(int rows, int cols) { // To be used by subclasses with non-square grids
        grid = new Tile[rows][cols];
        over=false;
    }

    public void setOver(boolean over) {this.over=over;}

    public Tile[][] getGrid() {return grid;}
    public boolean isOver() {return over;}


    public void play() { // Since no game defined, ask which game
        System.out.println("Games:");
        System.out.println(" 1 - Tic-Tac-Toe");
        System.out.println(" 2 - Connect Four");
        System.out.print("Select a game: ");

        String choice = SCAN.nextLine();
        GridGame game;
        if (choice.equals("1")) {
            game = new TicTacToe();
        } else {
            game = new ConnectFour();
        }
        game.play();
    }

    public Tile getTile(int r, int c) { // Shortcut for subclasses
        return grid[r][c];
    }
    public void createTile(int r, int c, Tile t) {
        grid[r][c]=t;
    }
    public void setTile(int r, int c, String value) {
        grid[r][c].setValue(value);
    }

    public boolean isTileEmpty(int r, int c) {
        return grid[r][c].isEmpty();
    }

    public String toString() {
        return toString(true);
    }

    public String toString(boolean indices) {
        String board = "";

        String colIndices = "";
        if (indices) {colIndices += SPACE+SPACE;}

        String rowDivider = "";
        if (indices) {rowDivider += SPACE;}
        rowDivider+=CROSS;
        for (int c=0; c<grid[0].length; c++) {

            if (indices) {
                colIndices += Letters.getDigit((c + 1) % 10); // mod 10 in case there's more than 9 columns to avoid error/misalignment
                colIndices += SPACE;
            }

            rowDivider+=HORIZONTAL;
            rowDivider+=CROSS;
        }

        if (indices) {
            board+=colIndices;
            board+="\n";
        }

        for (int r=0; r<grid.length; r++) {
            board+= rowDivider + "\n";

            if (indices) {board += Letters.getDigit((r + 1) % 10);}
            board+=VERTICAL;
            for (Tile t : grid[r]) {
                board+= t;
                board+=VERTICAL;
            }
            if (indices) {board += Letters.getDigit((r + 1) % 10);}
            board+="\n";
        }
        board+=rowDivider;
        if (indices) {board += "\n"+colIndices;}

        return board;
    }

    public int[] askForTile(String prompt) {
        int[] cords = new int[2];
        System.out.print(prompt);
        String response = SCAN.nextLine();
        int comma = response.indexOf(",");
        cords[1] = Integer.parseInt(response.substring(0,comma))-1;

        int space = response.indexOf(" "); // Accommodate for if they add a space
        if (space==-1) {
            space=comma;
        }

        cords[0] = Integer.parseInt(response.substring(space+1))-1;

        return cords;
    }

    public int[] askForEmptyTile(String prompt) {
        while (true) {
            int[] cords;
            cords = askForTile(prompt);

            if (isTileEmpty(cords[0],cords[1])) {
                return cords;
            }
            // If here, tile wasn't empty:
            System.out.println("That tile isn't empty!");
        }
    }
}
