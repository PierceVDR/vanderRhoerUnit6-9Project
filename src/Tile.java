public class Tile extends GridGame {
    private static final String EMPTY_CHARACTER = Letters.SPACE;
    private String value;

    public Tile() {
        value = "";
    }

    public void setValue(String value) {this.value = value;}
    public String getValue() {return value;}

    public boolean isEmpty() {return value.equals("");}

    @Override
    public String toString() {
        if (isEmpty()) {
            return EMPTY_CHARACTER;
        } else {
            return value;
        }
    }

    @Override
    public boolean equals(Object obj) { // Two empty tiles will NOT be considered equal
        if (!(obj instanceof Tile)) {return false;}

        return !isEmpty() && this.value.equals(((Tile) obj).value);
    }

}
