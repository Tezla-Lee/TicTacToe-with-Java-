package tictactoe;

import java.util.List;

public interface Printable {
    void printStatus();
    List<Position> availablePosition();
}
