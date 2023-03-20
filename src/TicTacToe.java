public class TicTacToe extends GridGame {
    private static final int SIZE=3;

    private String player = "X";
    public TicTacToe() {
        super(SIZE); // Will always have size of three
        for (int r=0; r<SIZE; r++) {
            for (int c=0; c<SIZE; c++) {
                createTile(r,c, new Tile());
            }
        }
    }

    @Override
    public void play() {
        printLabelled();
        int[] cords = askForEmptyTile("Player " + player + ", enter your move!\n" +
                "Enter in format x,y: ");
        int r=cords[0];
        int c=cords[1];

        setTile(r,c,Letters.getLetter(player));


        String winner = checkIfGameOver();

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

    private String checkIfGameOver() { // Returns winner, or null if nobody has won
        if (checkIfPlayerWon(player)) {
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

    private boolean checkIfPlayerWon(String winner) {
        winner=Letters.getLetter(winner); // Get the version of player that will appear on tile

        for (Tile[] row : getGrid()) { // Check for row
            boolean won=true;
            for (Tile t : row) {
                if (!t.getValue().equals(winner)) {
                    won=false;
                    break;
                }
            }
            if (won) {return true;}
        }

        for (int c=0; c<SIZE; c++) { // Check for column
            boolean won=true;
            for (int r=0; r<SIZE; r++) {
                if (!getTile(r,c).getValue().equals(winner)) {
                    won=false;
                    break;
                }
            }
            if (won) {return true;}
        }

        boolean won=true;
        for (int i=0; i<SIZE; i++) { // Check for TL->BR diagonal
            if (!getTile(i,i).getValue().equals(winner)) {
                won=false;
                break;
            }
        }
        if (won) {return true;}

        won=true;
        for (int i=0; i<SIZE; i++) { // Check for BL->TR diagonal
            if (!getTile(SIZE-1-i, i).getValue().equals(winner)) {
                won=false;
                break;
            }
        }
        if (won) {return true;}

        return false;
    }

    private void swapPlayers() {
        if (player.equals("X")) {
            player="O";
        } else {
            player="X";
        }
    }

}
