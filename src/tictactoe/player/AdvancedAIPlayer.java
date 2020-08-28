package tictactoe.player;

import tictactoe.Position;
import tictactoe.TicTacToe;

import java.util.ArrayList;

public class AdvancedAIPlayer extends Player {
    public void getKeyboardInput() {
        System.out.println("AI 실행: (" + Player.pos.getX() + ", " + Player.pos.getY() + ")");
        TicTacToe ticTacToe = TicTacToe.getInstance();
        minimax(0, ticTacToe.count);
    }

    public int minimax(int depth, int turn) {
        TicTacToe ticTacToe = TicTacToe.getInstance();

        if (ticTacToe.allCheck2(ticTacToe.player1, 3)) {
            return 1;
        }
        if (ticTacToe.allCheck2(ticTacToe.player2, 3)) {
            return -1;
        }

        ArrayList<Position> availableCells = ticTacToe.availablePosition();
        if (availableCells.isEmpty()) {
            return 0;
        }

        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        for (int i = 0; i < availableCells.size(); i++) {
            Position tempPos = availableCells.get(i);

            if (ticTacToe.count % 2 == 0) {
                ticTacToe.board[Player.pos.getX()][Player.pos.getY()] = 1;
                int currentScore = minimax(depth + 1, turn + 1);

                if (depth == 0) {
                    System.out.println("좌표 가치: " + tempPos + " = " + currentScore);
                }
                if (currentScore >= 0) {
                    if (depth == 0) {
                        Player.pos.setX(tempPos.getX());
                        Player.pos.setY(tempPos.getY());
                    }
                }
                if (currentScore == 1) {
                    ticTacToe.board[Player.pos.getX()][Player.pos.getY()] = 0;
                    break;
                }
                if (i == availableCells.size() - 1 && max < 0) {
                    if (depth == 0) {
                        Player.pos.setX(tempPos.getX());
                        Player.pos.setY(tempPos.getY());
                    }
                }

            } else if (ticTacToe.count % 2 == 1) {
                ticTacToe.board[Player.pos.getX()][Player.pos.getY()] = 2;
                int currentScore = minimax(depth + 1, turn + 1);
                min = Math.min(currentScore, min);

                if (min == -1) {
                    ticTacToe.board[Player.pos.getX()][Player.pos.getY()] = 0;
                    break;
                }
            }
            ticTacToe.board[Player.pos.getX()][Player.pos.getY()] = 0;
        }
        return (ticTacToe.count % 2 == 0) ? max : min;
    }
}