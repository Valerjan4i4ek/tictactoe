package test.tictactoe;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Game {
    final char SIGN_X = 'X';
    final char SIGN_O = 'O';
    final char SIGN_EMPTY = '_';
    char[][] table;
    transient Scanner scanner;

    Game() {
        scanner = new Scanner(System.in);
        table = new char[3][3];
    }

    void game(String playerX, String player0) {
        initTable();
        printTable();
        while (true) {
            turnX();
            if (checkWin(SIGN_X)) {
                System.out.println("КРЕСТИКИ ПОБЕДИЛИ!");
                break;
            }
            if (isTableFull()) {
                System.out.println("СОРЯНЧИК, НИЧЬЯ!");
                break;
            }
            turnO();
            printTable();
            if (checkWin(SIGN_O)) {
                System.out.println("НООЛИКИ ПОБЕДИЛИ!");
                break;
            }
            if (isTableFull()) {
                System.out.println("СОРЯНЧИК, НИЧЬЯ!");
                break;
            }
        }
        System.out.println("GAME OVER.");
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tictactoe.txt"))) {
            History history = new History(playerX, player0, table);
            oos.writeObject(history);
        } catch (IOException e) {
            e.printStackTrace();
        }
        printTable();

    }

    void initTable() {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                table[row][col] = SIGN_EMPTY;
    }

    void printTable() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++)
                System.out.print(table[row][col] + " ");
            System.out.println();
        }
    }

    void turnX() {
        int x, y;
        do {
            System.out.println("ХОД КРЕСТИКОВ!");
            System.out.println("Введите кординаты Х и Y (1, 2 или 3 для каждого):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        table[y][x] = SIGN_X;
    }

    void turnO() {
        int x, y;
        do {
            System.out.println("ХОД НОЛИКОВ!");
            System.out.println("Введите кординаты Х и Y (1, 2 или 3 для каждого):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        table[y][x] = SIGN_O;
    }

    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= 3|| y >= 3){
            System.out.println("ДАННОЕ ПОЛЕ УЖЕ ЗАПОЛНЕНО ИЛИ ЕГО ПОПРОСТУ НЕТ");
            return false;
        }
        return table[y][x] == SIGN_EMPTY;
    }

    boolean checkWin(char dot) {
        for (int i = 0; i < 3; i++)
            if ((table[i][0] == dot && table[i][1] == dot &&
                    table[i][2] == dot) ||
                    (table[0][i] == dot && table[1][i] == dot &&
                            table[2][i] == dot))
                return true;
        if ((table[0][0] == dot && table[1][1] == dot &&
                table[2][2] == dot) ||
                (table[2][0] == dot && table[1][1] == dot &&
                        table[0][2] == dot))
            return true;
        return false;
    }

    boolean isTableFull() {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (table[row][col] == SIGN_EMPTY)
                    return false;
        return true;
    }
}
