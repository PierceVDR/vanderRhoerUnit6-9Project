import java.util.Scanner;

public class ConnectFour extends GridGame {
    private static final Scanner SCAN = new Scanner(System.in); // Because this game takes column #s for input, it needs its own scanner
    private static final int ROWS=6;
    private static final int COLS=7;
    private static final int CONNECT=4; // Even though it's in the name, if for some reason you want to change it to Connect Five, this'll make it easier

    private String player = "X";
    public ConnectFour() {
        super(ROWS,COLS);
        for (int r=0; r<ROWS; r++) {
            for (int c=0; c<COLS; c++) {
                createTile(r,c, new Tile());
            }
        }
    }

    @Override
    public void play() {
        System.out.println(this);
        int c = askForNotFullCol("Player " + player + ", enter your move!\n" +
                "Enter a column # to deposit your piece into: ");

        int r=0;
        while (r+1<ROWS && isTileEmpty(r+1,c)) {r++;} // Find row of tile to be filled

        setTile(r,c,Letters.getLetter(player));


        String winner = checkIfGameOver(r, c);

        if (isOver()) {
            System.out.println(this);
            System.out.println("Game over!" );
            if (winner!=null) {
                System.out.println("Player " + winner + " has won!");
            } else {
                System.out.println("Nobody won!");
            }
        } else {
            swapPlayers();
            play();
        }
    }

    private boolean checkIfWon(int r, int c) {
        Tile t=getTile(r,c);

        // Check if it made a complete row:
        int rowSize=1;
        int checkC=c-1;
        while (checkC>=0) {
            Tile checkT=getTile(r,checkC);
            if (checkT.equals(t)) {
                rowSize++;
                checkC--;
            } else {
                break;
            }
        }
        checkC=c+1;
        while (checkC<COLS) {
            Tile checkT=getTile(r,checkC);
            if (checkT.equals(t)) {
                rowSize++;
                checkC++;
            } else {
                break;
            }
        }
        if (rowSize>=CONNECT) {return true;}



        // Check if it made a complete column:
        int colSize=1;
        int checkR=r-1;
        while (checkR>=0) {
            Tile checkT=getTile(checkR,c);
            if (checkT.equals(t)) {
                colSize++;
                checkR--;
            } else {
                break;
            }
        }
        checkR=r+1;
        while (checkR<ROWS) {
            Tile checkT=getTile(checkR,c);
            if (checkT.equals(t)) {
                colSize++;
                checkR++;
            } else {
                break;
            }
        }
        if (colSize>=CONNECT) {return true;}



        // Check if it made a diagonal:
        int num=1;
        checkR=r-1;
        checkC=c-1;
        while (checkR>=0 && checkC>=0) {
            Tile checkT=getTile(checkR,checkC);
            if (checkT.equals(t)) {
                num++;
                checkR--;
                checkC--;
            } else {
                break;
            }
        }
        checkR=r+1;
        checkC=c+1;
        while (checkR<ROWS && checkC<COLS) {
            Tile checkT=getTile(checkR,checkC);
            if (checkT.equals(t)) {
                num++;
                checkR++;
                checkC++;
            } else {
                break;
            }
        }
        if (num>=CONNECT) {return true;}

        // Check if it made a diagonal in the other direction:
        num=1;
        checkR=r+1;
        checkC=c-1;
        while (checkR<ROWS && checkC>=0) {
            Tile checkT=getTile(checkR,checkC);
            if (checkT.equals(t)) {
                num++;
                checkR++;
                checkC--;
            } else {
                break;
            }
        }
        checkR=r-1;
        checkC=c+1;
        while (checkR>0 && checkC<COLS) {
            Tile checkT=getTile(checkR,checkC);
            if (checkT.equals(t)) {
                num++;
                checkR--;
                checkC++;
            } else {
                break;
            }
        }
        if (num>=CONNECT) {return true;}

        return false;
    }

    private int askForNotFullCol(String prompt) {
        while (true) {
            System.out.print(prompt);
            String response = SCAN.nextLine();

            int c = Integer.parseInt(response)-1;

            if (isTileEmpty(0,c)) {
                return c;
            }
            // If here, that column was full
            System.out.println("That column is full!");
        }
    }

    private String checkIfGameOver(int r, int c) { // Returns winner, or null if nobody has won
        if (checkIfWon(r,c)) {
            setOver(true);
            return player;
        } // Only need to check if current player has won

        for (Tile[] row : getGrid()) {
            for (Tile t : row) {
                if (t.isEmpty()) {return null;}
            }
        }

        setOver(true); // If here, that means all spaces taken
        return null;
    }

    private void swapPlayers() {
        if (player.equals("X")) {
            player="O";
        } else {
            player="X";
        }
    }
}
