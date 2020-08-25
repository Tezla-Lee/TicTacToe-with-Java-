package tictactoe;

import tictactoe.player.Player;

public interface Winnable {
    void verticalCheck(Player player, int win);

    void horizontalCheck(Player player, int win);

    void diagonalCheck(Player player, int win);

    void skewDiagonalCheck(Player player, int win);
}