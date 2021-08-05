import java.awt.desktop.ScreenSleepEvent;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    static final char BOT = 'O';
    static final char PLAYER = 'X';
    Bot bot = new Bot();
    char[][] board;
    boolean gameOn = true;
    char winner = 0;

    public void initBoard() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '.';
            }
        }
    }

    public Position parseMove(String move) {
        String[] pos = move.split(" ");
        return new Position(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
    }

    public boolean isLegalMove(String move) {
        // TODO: make this work
        return true;
    }

    public Position getPlayerMove() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Make a move: ");
        String move = sc.nextLine();

        while (!isLegalMove(move)) {
            System.out.print("Illegal move. Make a move: ");
            move = sc.nextLine();
        }

        return parseMove(move);
    }

    public void makeMove(char currPlayer) {
        Position move;
        if (currPlayer == BOT) {
            move = bot.getMove();
        } else if (currPlayer == PLAYER) {
            move = getPlayerMove();
        } else {
            // What to do here?
            return;
        }

        // Have already confirmed that move is legal. Can now make move.
        board[move.getRow()][move.getCol()] = currPlayer;

        // Check for winner
        checkForWinner(move, currPlayer);
    }

    public void checkForWinner(Position lastMove, char lastPlayer) {
        // Need an algorithm to check for winner.
        // We know that we only have to check if the last move has made someone
        // a winner.
        // If game is over, then lastPlayer is winner.
    }

    public void displayGame() {
        System.out.println("---------------");
        System.out.println("    1   2   3  ");

        for (int row = 0; row < 3; row++) {
            System.out.print(row + " | ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
        }

        System.out.println("---------------");
    }

    public void introMessage() {
        // Print a message to player
        System.out.println("Tic Tac Toe!");
        System.out.println("When prompted, make a move in the format \"[row] [column]\".");
        System.out.println("For example, to move in row 2 and column 1, \"2 1\".");
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.initBoard();

        char currPlayer = PLAYER;
        // Check if winner/can keep playing
        game.introMessage();
        while (game.gameOn) {
            // Display board
            game.displayGame();
            // Take turn
            game.makeMove(currPlayer);
            // Change player
            currPlayer = (currPlayer == PLAYER ? BOT : PLAYER);
        }

        if (game.winner == 0) {
            // Draw
            System.out.println("Game over: draw.");
        } else if (game.winner == BOT){
            // Winner is ___
            System.out.println("Game over: you lose!");
        } else {
            System.out.println("Game over: you win!");
        }

    }
}
