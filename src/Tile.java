public class Tile extends GridGame {
    private static final String EMPTY_CHARACTER = Letters.SPACE;
    private String value;

    public Tile() {
        value = "";
    }

    public Tile(String value) {this.value = value;}

    public void setValue(String value) {this.value = value;}
    public String getValue() {return value;}


    public void empty() {
        value ="";}
    public boolean isEmpty() {return value.equals("");}

    public String toString() {
        if (isEmpty()) {
            return EMPTY_CHARACTER;
        } else {
            return value;
        }
    }

}
