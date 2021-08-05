/*
 * Basic tic tac toe game.
 *
 * Ideas for to improve:
 * - Allow players to chose name/token
 * - Allow players to chose board size
 * - Let player restart during/after a game
 * - Implement game with different sized pieces to allow players to place over
 *      other player's piece.
 * - Create a GUI
 *
 */

import java.util.Scanner;

public class Game {
    static final char BOT = 'O';
    static final char PLAYER = 'X';
    static int dimension;
    static Bot bot;
    static Board board;

    public static Position getPlayerMove() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Make a move: ");
        String move = sc.nextLine();

        while (!isLegalMove(move)) {
            System.out.print("Illegal move. Make a move: ");
            move = sc.nextLine();
        }

        return Position.parseMove(move);
    }

    public static boolean isLegalMove(String move) {
        Position pos = Position.parseMove(move);

        if (pos.getRow() < 0 || pos.getRow() >= dimension ||
                pos.getCol() < 0 || pos.getCol() >= dimension) {
            return false;
        }

        return board.spaceAvailable(pos);
    }

    public static void makeMove(char currPlayer) {
        Position move;
        if (currPlayer == BOT) {
            move = bot.getMove();
        } else if (currPlayer == PLAYER) {
            move = getPlayerMove();
        } else {
            // What to do here?
            return;
        }

        board.makeMove(move, currPlayer);
    }

    public static void introMessage() {
        // Print a message to player
        System.out.println("Tic Tac Toe!");
        System.out.println("When prompted, make a move in the format \"[row] [column]\".");
        System.out.println("For example, to move in row 2 and column 1, \"2 1\".");
    }

    public static void main(String[] args) {
        dimension = 3;
        board = new Board();
        bot = new Bot(board);
        board.initBoard(dimension);

        char currPlayer = PLAYER;
        // Check if winner/can keep playing
        introMessage();
        while (board.isGameOn()) {
            // Display board
            board.displayBoard();
            // Take turn
            makeMove(currPlayer);
            // Change player
            currPlayer = (currPlayer == PLAYER ? BOT : PLAYER);
        }

        board.displayBoard();
        if (board.getWinner() == 0) {
            // Draw
            System.out.println("Game over: draw.");
        } else if (board.getWinner() == BOT){
            // Winner is ___
            System.out.println("Game over: you lose!");
        } else {
            System.out.println("Game over: you win!");
        }

    }
}
