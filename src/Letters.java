public class Letters {
    /*
     * A utility class to compile the full-width unicode
     * characters, used for printing out the board.
     * If full-width characters weren't used, the tiles would be misaligned
     *
     * Copy-and-pasted from this website: http://xahlee.info/comp/unicode_full-width_chars.html
     *
     * Note: I didn't end up using some of the characters I added to this class
     */

    public static final String CROSS = "＋";
    public static final String HORIZONTAL = "－";
    public static final String VERTICAL = "｜";
    public static final String SPACE = "　";
    public static final String X = "Ｘ";
    public static final String O = "Ｏ";
    public static final String WHITE = "⬜";
    public static final String YELLOW = "🟡";
    public static final String BLACK = "⬛";
    public static final String RED = "🔴";

    // I used this page to get the colored text: https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/\
    // But I never actually used it
    private static final String COLOR_RESET = "\u001B[0m";
    private static final String COLOR_WHITE = "\u001B[37m";
    private static final String COLOR_RED = "\u001B[31m";
    private static final String COLOR_YELLOW = "\u001B[33m";

    public static final String RED_STAR = COLOR_RED + "★" + COLOR_RESET;
    public static final String WHITE_STAR = COLOR_WHITE + "★" + COLOR_RESET;
    public static final String YELLOW_STAR = COLOR_YELLOW + "★" + COLOR_RESET;

    public static final String[] digits = {"０","１","２","３","４","５","６","７","８","９"};
    public static String getDigit(int d) {
        return digits[d];
    }

    public static String getLetter(String l) {
        if (l.equals("X")) {
            return X;
        } else if (l.equals("O")) {
            return O;
        }
        return null;
    }
}
