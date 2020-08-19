package tictactoe.player;

import tictactoe.Position;
import tictactoe.TicTacToe;


public class Player {
    public static Position pos = new Position();
    private String name;
    public int lineNum = 1;
    public int numWin = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void getKeyboardInput() {}
}
