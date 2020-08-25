package tictactoe.player;

import tictactoe.TicTacToe;

public class ProfessionalAIPlayer extends Player{
    public void getKeyboardInput() {
        TicTacToe ticTacToe = TicTacToe.getInstance();
        if (ticTacToe.count == 0) {
            Player.pos.setX(1);
            Player.pos.setY(1);
        } else if (ticTacToe.count == 1) {

        }
    }
}
