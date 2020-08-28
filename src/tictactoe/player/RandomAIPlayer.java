package tictactoe.player;

import tictactoe.Position;
import tictactoe.TicTacToe;

public class RandomAIPlayer extends Player {

    public void getKeyboardInput() {
        TicTacToe ticTacToe = TicTacToe.getInstance();

        Position tempPos = ticTacToe.availablePosition().get((int) (Math.random() * ticTacToe.availablePosition().size()));
        Player.pos.setX(tempPos.getX());
        Player.pos.setY(tempPos.getY());

        System.out.println("Random : (" + (Player.pos.getX() + 1) + ", " + (Player.pos.getY() + 1) + ")");
    }
}