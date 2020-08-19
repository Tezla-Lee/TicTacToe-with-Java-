package tictactoe;

import tictactoe.player.Player;

public interface Simulatable {
    void initialize();
    void isFinished();
    void reStart();
    void end();
}
