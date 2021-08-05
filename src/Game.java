import java.awt.desktop.ScreenSleepEvent;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private static final char BOT = 'O';
    private static final char PLAYER = 'X';
    private Bot bot = new Bot();
    private char board[][];
    private boolean gameOn = true;
    private char winner = 0;

    public void initBoard() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }
    }

    public Position parseMove(String move) {
        String[] pos = move.split(" ");
        return new Position(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
    }

    public boolean isLegalMove(String move) {
        // TODO: make this work lol
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

    public void makeMove(int turn) {
        Position move;
        if (turn == BOT) {
            move = bot.getMove();
        } else if (turn == PLAYER) {
            move = getPlayerMove();
        }
    }

    public static void display_game() {

    }

    public void main(String[] args) {
        initBoard();

        int currPlayer = PLAYER;
        // Check if winner/can keep playing
        while (gameOn) {
            // Take turn
            makeMove(currPlayer);
            // Change player
        }

        if (winner == 0) {
            // Draw
            System.out.println("Game over: draw.");
        } else if (winner == BOT){
            // Winner is ___
            System.out.println("Game over: you lose!");
        } else {
            System.out.println("Game over: you win!");
        }

    }
}
