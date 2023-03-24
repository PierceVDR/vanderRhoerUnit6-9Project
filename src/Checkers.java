public class Checkers extends GridGame {
    private static final int SIZE=8;
    private static final int FILLED_ROWS = 3; // Number of rows filled at the start of the game

    private String player = "X";
    public Checkers() {
        super(SIZE); // Will always have size of eight
        for (int r=0; r<SIZE; r++) {
            for (int c=0; c<SIZE; c++) {
                String value="";
                if (isBlack(r,c)) {
                    // Check if it should be empty:
                    if (r<FILLED_ROWS) {
                        value=Letters.RED;
                    } else if (r>=SIZE-FILLED_ROWS) {
                        value=Letters.YELLOW;
                    } else {
                        value=Letters.BLACK; // Empty, black square
                    }
                }
                createTile(r,c, new Tile(value));
            }
        }
        System.out.println(this);
    }

    private boolean isBlack(int r, int c) {
        return ((r+c)%2 == 1);
    }
}
