public class Bot {
    private Board board;

    public Bot(Board board) {
        this.board = board;
    }

    public Position getMove() {
        int row = 0;
        Position bestMove = new Position(-1, -1);
        int bestScore = Integer.MIN_VALUE;
        int currScore;

        while (row < board.getDimension()) {
            int col = 0;
            while (col < board.getDimension()) {
                if (board.spaceAvailable(new Position(row, col))) {
                    board.makeMove(new Position(row, col), Board.BOT);

                    currScore = minimax(0, false);
                    if (currScore > bestScore) {
                        bestScore = currScore;
                        bestMove = new Position(row, col);
                    }

                    board.undoMove(new Position(row, col));
                }
                col++;
            }
            row++;
        }

        return bestMove;
    }

    public int minimax(int depth, boolean isBot) {
        // TODO: make this work
        // Use minimax algorithm.
        // Bot is the maximiser, human is the minimiser.

        char winner = board.getWinner();

        if (winner == Board.BOT) {
            return 10 - depth;
        } else if (winner == Board.PLAYER) {
            return (-10 - depth);
        } else if (winner == 0 && !board.isGameOn()) { // Draw
            return 0;
        }

        // Otherwise game is not over.
        int row = 0;
        int bestScore = (isBot ? Integer.MIN_VALUE : Integer.MAX_VALUE);
        int currScore;
        char playerToken = (isBot ? Board.BOT : Board.PLAYER);

        while (row < board.getDimension()) {
            int col = 0;
            while (col < board.getDimension()) {
                if (board.spaceAvailable(new Position(row, col))) {
                    board.makeMove(new Position(row, col), playerToken);

                    currScore = minimax(depth + 1, !isBot);
                    if ((isBot && currScore > bestScore) ||
                            (!isBot && currScore < bestScore)) {
                        bestScore = currScore;
                    }

                    board.undoMove(new Position(row, col));
                }
                col++;
            }
            row++;
        }
        return bestScore;
    }
}
