public class Board {
    static final char BOT = 'O';
    static final char PLAYER = 'X';
    private int dimension;
    private int numPossibleMoves;
    private int numMoves = 0;
    private char[][] board;
    private char winner = 0;

    public void initBoard(int dim) {
        this.dimension = dim;
        this.numPossibleMoves = dim * dim;
        board = new char[dim][dim];
        // Populate empty board
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                board[i][j] = '.';
            }
        }
    }

    public char getWinner() {
        return winner;
    }

    public boolean isGameOn() {
        return numMoves < numPossibleMoves && winner == 0;
    }

    public boolean isMovesLeft() {
        return numMoves < dimension * dimension;
    }

    public boolean spaceAvailable(Position pos) {
        return board[pos.getRow()][pos.getCol()] == '.';
    }

    public void makeMove(Position pos, char player) {
        // Have already confirmed that move is legal. Can now make move.
        board[pos.getRow()][pos.getCol()] = player;
        numMoves++;

        // Check for winner
        checkRecentForWinner(pos, player);
    }

    // TODO: make this better, for now it works - also test for draw!
    public void checkRecentForWinner(Position lastMove, char lastPlayer) {
        // Need an algorithm to check for winner.
        // We know that we only have to check if the last move has made someone
        // a winner.
        // If game is over, then lastPlayer is winner.

        // Check row
        int col = 0;
        while (col < dimension) {
            if (board[lastMove.getRow()][col] != lastPlayer) {
                break;
            }
            col++;
        }
        if (col == dimension) {
            winner = lastPlayer;
        }

        // Check col
        int row = 0;
        while (winner == 0 && row < dimension) {
            if (board[row][lastMove.getCol()] != lastPlayer) {
                break;
            }
            row++;
        }
        if (row == dimension) {
            winner = lastPlayer;
        }

        // Check diagonal/s
        if (winner == 0 && lastMove.getCol() == lastMove.getRow()) { // Forward diagonal
            row = 0;
            col = 0;
            while (row < dimension) {
                if (board[row][col] != lastPlayer) {
                    break;
                }
                row++;
                col++;
            }
            if (row == dimension) {
                winner = lastPlayer;
            }
        }

        if (winner == 0 && lastMove.getRow() == lastMove.getCol() - dimension + 1) { // Backwards diagonal
            col = 0;
            row = dimension - 1;
            while (col < dimension) {
                if (board[row][col] != lastPlayer) {
                    break;
                }
                col++;
                row--;
            }
            if (col == dimension) {
                winner = lastPlayer;
            }
        }
    }

    public void displayBoard() {
        System.out.println("---------------");
        System.out.println("    1   2   3  ");

        for (int row = 0; row < 3; row++) {
            System.out.print((row + 1) + " | ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
        }

        System.out.println("---------------");
    }
}
