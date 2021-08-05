public class Position {
    private int col;
    private int row;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public static Position parseMove(String move) {
        String[] pos = move.split(" ");
        return new Position(Integer.parseInt(pos[0]) - 1, Integer.parseInt(pos[1]) - 1);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
