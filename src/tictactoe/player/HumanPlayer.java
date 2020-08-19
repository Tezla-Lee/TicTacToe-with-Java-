package tictactoe.player;

import tictactoe.TicTacToe;

import java.util.Scanner;

public class HumanPlayer extends Player {
    // todo : fix (if ~)
    public void getKeyboardInput() {
        System.out.println("좌표(x,y)를 입력하세요: ");
        Scanner sc = new Scanner(System.in);
        while(true) {
            String cor = sc.nextLine();
            String[] corNum = cor.split(",");
            int[] corInt = new int[2];
            for (int i = 0; i < corInt.length; i++) {
                corInt[i] = Integer.parseInt(corNum[i]);
                Player.pos.setX(corInt[0]);
                Player.pos.setY(corInt[1]);
            }
            if (Player.pos.getX() < 0 || Player.pos.getY() < 0 || Player.pos.getX() > 2 || Player.pos.getY() > 2 || TicTacToe.board[pos.getX()][pos.getY()] == 1 || TicTacToe.board[pos.getX()][pos.getY()] == 2) {
                System.out.println("Error, 다시 입력하세요.");
            } else {
                break;
            }
        }
    }
}
