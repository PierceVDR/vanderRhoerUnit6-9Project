import java.util.Scanner;

public class ConnectFour extends GridGame {
    private static final Scanner SCAN = new Scanner(System.in); // Because this game takes column #s for input, it needs its own scanner
    private static final int ROWS=6;
    private static final int COLS=7;
    private static final int CONNECT=4; // Even though it's in the name, if for some reason you want to change it to Connect Five, this'll make it easier

    private String player = "X";
    public ConnectFour() {
        super(ROWS,COLS); // Will always have size of three
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
        while (!isTileEmpty(r,c)) {r++;} // Find row of tile to be filled

        setTile(r,c,Letters.getLetter(player));


        String winner = checkIfGameOver(r, c);

        if (isOver()) {
            System.out.println(this);
            System.out.print("Game over!" );
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
        while (checkR<COLS) {
            Tile checkT=getTile(checkR,checkR);
            if (checkT.equals(t)) {
                checkR++;
                checkC++;
            } else {
                break;
            }
        }
        if (colSize>=CONNECT) {return true;}

        // Check if it made a diagonal in the other direction:
        num=1;
        checkR=r+1;
        checkC=c-1;
        while (checkR<COLS && checkC>=0) {
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
        while (checkR<COLS) {
            Tile checkT=getTile(checkR,checkR);
            if (checkT.equals(t)) {
                checkR--;
                checkC++;
            } else {
                break;
            }
        }
        if (colSize>=CONNECT) {return true;}

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

//    private boolean checkIfPlayerWon(String winner) {
//        // Check for rows:
//        for (Tile[] row : getGrid()) {
//
//            Tile lastT = null;
//            int rowCount=0; // Count # of tiles with same value (not empty)
//
//            for (Tile t : row) {
//                if (t.equals(lastT)) {
//                    rowCount++;
//                    if (rowCount>=CONNECT) {return true;}
//                } else {
//                    rowCount=0;
//                }
//                lastT=t;
//            }
//        }
//
//        // Check for columns:
//        for (int c=0; c<COLS; c++) {
//            Tile lastT = null;
//            int colCount=0; // Count # of tiles with same value (not empty)
//            for (int r=0; r<ROWS; r++) {
//
//                Tile t = getTile(r,c);
//
//                if (t.equals(lastT)) {
//                    colCount++;
//                    if (colCount>=CONNECT) {return true;}
//                } else {
//                    colCount=0;
//                }
//                lastT=t;
//            }
//        }
//
//        // Check for diagonals:
//
//    }

//    @Override
//    public String toString() {
//        String txt = super.toString(false); // Use of super. is not actually necessary, but I've included it for clarity
//        String colIndices =
//    }

    private void swapPlayers() {
        if (player.equals("X")) {
            player="O";
        } else {
            player="X";
        }
    }
}
