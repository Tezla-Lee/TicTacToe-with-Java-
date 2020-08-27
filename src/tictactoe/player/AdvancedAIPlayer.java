package tictactoe.player;

import tictactoe.TicTacToe;

public class AdvancedAIPlayer extends Player {
    public void getKeyboardInput() {
        minimax(0);
        System.out.println("AI 실행: (" + Player.pos.getX() + ", " + Player.pos.getY() + ")");
    }

    public int minimax(int depth) {
        TicTacToe ticTacToe = TicTacToe.getInstance();
        int count = ticTacToe.count;
        if (ticTacToe.availablePosition().isEmpty()) {
            Player.pos.setX(pos.getX());
            Player.pos.setY(pos.getY());
        }

        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;

        for (int i = 0; i < ticTacToe.availablePosition().size(); i++) {
            pos = ticTacToe.availablePosition().get(i);

            if (count % 2 == 0) {
                ticTacToe.play();
                int currentScore = minimax(depth + 1);
                max = Math.max(currentScore, max);

                if (depth == 0) {
                    System.out.println("좌표 가치 " + pos + ": " + currentScore);
                }

                if (currentScore >= 0) {
                    if (depth == 0) {
                        Player.pos.setX(pos.getX());
                        Player.pos.setY(pos.getY());
                    }
                }

                if (currentScore == 1) {
                    ticTacToe.board[pos.getY()][pos.getY()] = 0;
                    break;
                }

                if (i == ticTacToe.availablePosition().size() - 1 && max < 0) {
                    if (depth == 0) {
                        Player.pos.setX(pos.getX());
                        Player.pos.setY(pos.getY());
                    }
                }
            } else if (count % 2 == 1) {
                Player.pos.setX(pos.getX());
                Player.pos.setY(pos.getY());
                ticTacToe.play();
                int currentScore = minimax(depth + 1);
                min = Math.min(currentScore, min);

                if (min == -1) {
                    ticTacToe.board[pos.getY()][pos.getY()] = 0;
                    break;
                }
            }
            ticTacToe.board[pos.getY()][pos.getY()] = 0;
        }
        return (count % 2 == 0) ? max : min;
    }
}