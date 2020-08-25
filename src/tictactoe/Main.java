package tictactoe;

public class Main {
    public static void main(String[] args) {
        TicTacToe ticTacToe = TicTacToe.getInstance();
        ticTacToe.initialize();
        ticTacToe.run();
    }
}
