package net.volgatech.Cell;

public class Cell {
    public Cell(String value) {
        String str = "";
        boolean readXMode = true;
        for (int i = 0; i < value.length(); ++i) {
            char symbol = value.charAt(i);
            if (symbol >= '0' && symbol <= '9' && readXMode) {
                setX(str);
                str = "";
                readXMode = false;
            }
            str += symbol;
        }
        setY(Integer.parseInt(str));
    }

    public static Boolean isIndexFormat(String arg) {
        if (ALPHABET.indexOf(Character.toUpperCase(arg.charAt(0))) != -1) {
            try {
                String str = arg.substring(1);
                Integer.parseInt(str);
                return true;
            }
            catch (Exception exc) {
                return false;
            }
        }
        return false;
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
    }
    private void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public boolean equals(Cell cell)
    {
        return (cell.getX() == this.getX() && cell.getY() == this.getY());
    }
    private void setX(String strX) {
        String x = strX.toUpperCase();
        for (int i = 0; i < x.length(); ++i) {
            this.x += (ALPHABET.indexOf(x.charAt(i)) * (x.length() - i));
        }
    }
    private int x;
    private int y;
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String getAlphabet() {
        return ALPHABET;
    }

    @Override
    public String toString() {
        String str = "";
        str += ALPHABET.charAt(x);
        str += y;
        return str;
    }
}
