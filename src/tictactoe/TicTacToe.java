package tictactoe;

import tictactoe.player.RandomAIPlayer;
import tictactoe.player.HumanPlayer;
import tictactoe.player.Player;
import tictactoe.player.ProfessionalAIPlayer;

import java.util.Scanner;

public class TicTacToe implements Simulatable, Printable, Winnable {

    public int count = 0;
    private static TicTacToe ticTacToe = null;

    private TicTacToe() {
    }

    public static TicTacToe getInstance() {
        if (ticTacToe == null) {
            ticTacToe = new TicTacToe();
        }
        return ticTacToe;
    }

    public int[][] board = new int[3][3];
    int tieNum = 0;
    //    int count = 0;
    int n = 0;
    Scanner sc = new Scanner(System.in);
    String p1, p2;
    Player player1;
    Player player2;

    public void play() {
        if (count % 2 == 0) {
            ticTacToe.board[Player.pos.getX()][Player.pos.getY()] = 1;
        } else {
            ticTacToe.board[Player.pos.getX()][Player.pos.getY()] = 2;
        }
        printStatus();
    }

    public void run() {
        input();
        printStatus();
    }

    @Override
    public void printStatus() {
        System.out.println("  1 2 3");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    System.out.print("O ");
                } else if (board[i][j] == 2) {
                    System.out.print("X ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println();
        isFinished();
    }

    @Override
    public void initialize() {
        System.out.print("n선 승제, n 입력: ");
        n = sc.nextInt();
        sc.nextLine();
        System.out.println("사람은 h, AI는 ai를 입력하세요.");

        while (true) {
            System.out.print("Player 1: ");
            p1 = sc.nextLine();
            if (p1.equals("h")) {
                player1 = new HumanPlayer();
                player1.setName("player1(Human)");
                break;
            } else if (p1.equals("ai")) {
                player1 = new RandomAIPlayer();
                player1.setName("player1(AI)");
                break;
            } else if (p1.equals("pro")) {
                player1 = new ProfessionalAIPlayer();
                player1.setName("player1(ProAI)");
                break;
            } else {
                System.out.println("다시 입력하세요.");
            }
        }
        while (true) {
            System.out.print("Player 2: ");
            p2 = sc.nextLine();
            if (p2.equals("h")) {
                player2 = new HumanPlayer();
                player2.setName("player2(Human)");
                break;
            } else if (p2.equals("ai")) {
                player2 = new RandomAIPlayer();
                player2.setName("player2(AI)");
                break;
            } else if (p2.equals("pro")) {
                player2 = new ProfessionalAIPlayer();
                player2.setName("player2(ProAI)");
                break;
            } else {
                System.out.println("다시 입력하세요.");
            }
        }
        System.out.println();
    }

    @Override
    public void isFinished() {
        if (count % 2 == 0) {
            allCheck(player1, 3);
        } else if (count % 2 == 1) {
            allCheck(player2, 3);
        }
        if (count == 8) {
            System.out.println("무승부 !");
            tieNum++;
            reStart();
        }
        count++;
        input();
    }

    public void input() {
        if (count % 2 == 0) {
            player1.getKeyboardInput();
            System.out.println(player1.getName() + " 착수");
        } else {
            player2.getKeyboardInput();
            System.out.println(player2.getName() + " 착수");
        }
        play();
    }

    public void allCheck(Player player, int win) {
        verticalCheck(player, win);
        horizontalCheck(player, win);
        diagonalCheck(player, win);
        skewDiagonalCheck(player, win);
    }

    @Override
    public void reStart() {
        System.out.println(player1.getName() + " " + player1.numWin + " vs " + player2.numWin + " " + player2.getName() + " 무승부 : " + tieNum);
        System.out.println();
        count = 0;
        end();
        board = new int[3][3];
        input();
    }

    @Override
    public void end() {
        if (player1.numWin == n) {
            System.exit(0);
        } else if (player2.numWin == n) {
            System.exit(0);
        }
    }

    @Override
    public void verticalCheck(Player player, int win) {
        player.lineNum = 1;
        for (int i = 0; i < win - 1; i++) {
            if (Player.pos.getX() + i + 1 >= win)
                break;
            if (ticTacToe.board[Player.pos.getX() + i + 1][Player.pos.getY()] == ticTacToe.board[Player.pos.getX()][Player.pos.getY()]) {
                player.lineNum++;
            } else
                break;
        }
        for (int i = 0; i < win - 1; i++) {
            if (Player.pos.getX() - i - 1 < 0)
                break;
            if (ticTacToe.board[Player.pos.getX() - i - 1][Player.pos.getY()] == ticTacToe.board[Player.pos.getX()][Player.pos.getY()]) {
                player.lineNum++;
            } else
                break;
        }
        if (player.lineNum == win) {
            player.numWin += 1;
            System.out.println(player.getName() + " : 승리 !");
            reStart();
        }
    }

    @Override
    public void horizontalCheck(Player player, int win) {
        player.lineNum = 1;
        for (int i = 0; i < win - 1; i++) {
            if (Player.pos.getY() + i + 1 >= win)
                break;
            if (ticTacToe.board[Player.pos.getX()][Player.pos.getY() + i + 1] == ticTacToe.board[Player.pos.getX()][Player.pos.getY()]) {
                player.lineNum++;
            } else
                break;
        }
        for (int i = 0; i < win - 1; i++) {
            if (Player.pos.getY() - i - 1 < 0)
                break;
            if (ticTacToe.board[Player.pos.getX()][Player.pos.getY() - i - 1] == ticTacToe.board[Player.pos.getX()][Player.pos.getY()]) {
                player.lineNum++;
            } else
                break;
        }
        if (player.lineNum == win) {
            player.numWin += 1;
            System.out.println(player.getName() + " : 승리 !");
            reStart();
        }
    }

    @Override
    public void diagonalCheck(Player player, int win) {
        player.lineNum = 1;
        for (int i = 0; i < win - 1; i++) {
            if (Player.pos.getX() + i + 1 >= win || Player.pos.getY() + i + 1 >= win)
                break;
            if (ticTacToe.board[Player.pos.getX() + i + 1][Player.pos.getY() + i + 1] == ticTacToe.board[Player.pos.getX()][Player.pos.getY()]) {
                player.lineNum++;
            } else
                break;
        }
        for (int i = 0; i < win - 1; i++) {
            if (Player.pos.getX() - i - 1 < 0 || Player.pos.getY() - i - 1 < 0)
                break;
            if (ticTacToe.board[Player.pos.getX() - i - 1][Player.pos.getY() - i - 1] == ticTacToe.board[Player.pos.getX()][Player.pos.getY()]) {
                player.lineNum++;
            } else
                break;
        }
        if (player.lineNum == win) {
            player.numWin += 1;
            System.out.println(player.getName() + " : 승리 !");
            reStart();
        }
    }

    @Override
    public void skewDiagonalCheck(Player player, int win) {
        player.lineNum = 1;
        for (int i = 0; i < win - 1; i++) {
            if (Player.pos.getX() - i - 1 < 0 || Player.pos.getY() + i + 1 >= win)
                break;
            if (ticTacToe.board[Player.pos.getX() - i - 1][Player.pos.getY() + i + 1] == ticTacToe.board[Player.pos.getX()][Player.pos.getY()]) {
                player.lineNum++;
            } else
                break;
        }
        for (int i = 0; i < win - 1; i++) {
            if (Player.pos.getX() + i + 1 >= win || Player.pos.getY() - i - 1 < 0)
                break;
            if (ticTacToe.board[Player.pos.getX() + i + 1][Player.pos.getY() - i - 1] == ticTacToe.board[Player.pos.getX()][Player.pos.getY()]) {
                player.lineNum++;
            } else
                break;
        }
        if (player.lineNum == win) {
            player.numWin += 1;
            System.out.println(player.getName() + " : 승리 !");
            reStart();
        }
    }
}