package tictactoe;

import tictactoe.player.Player;

public interface Winnable {
    void vertical(Player player, int win);

    void horizontal(Player player, int win);

    void diagonal(Player player, int win);

    void skewDiagonal(Player player, int win);
}
