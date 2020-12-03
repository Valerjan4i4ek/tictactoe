package test.tictactoe;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
        String result = "";
        initTable();
        printTable();
        while (true) {
            turnX();
            if (checkWin(SIGN_X)) {
                result = "CROSSES WON";
                System.out.println(result);
                break;
            }
            if (isTableFull()) {
                result = "DRAW";
                System.out.println(result);
                break;
            }
            turnO();
            printTable();
            if (checkWin(SIGN_O)) {
                result = "ZEROS WON";
                System.out.println(result);
                break;
            }
            if (isTableFull()) {
                result = "DRAW";
                System.out.println(result);
                break;
            }
        }
        System.out.println("GAME OVER.");
        printTable();

        String final_table = "";
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++)
                final_table += table[row][col] + " ";
            final_table += "\n";
        }

        try (FileOutputStream fos=new FileOutputStream("notes.txt", true)) {
            byte[] buffer = final_table.getBytes();
            fos.write(buffer, 0, buffer.length);
            byte[] x = playerX.getBytes();
            fos.write(x, 0, x.length);
            fos.write("\t".getBytes());
            byte[] o = player0.getBytes();
            fos.write(o, 0, o.length);
            fos.write("\n".getBytes());
            byte[] res = result.getBytes(StandardCharsets.UTF_8);
            fos.write(res, 0, res.length);
            fos.write("\n".getBytes());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

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
            System.out.println("CROSSES RUN");
            System.out.println("Enter coordinates Х or Y (1, 2 or 3 for each):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        table[y][x] = SIGN_X;
    }

    void turnO() {
        int x, y;
        do {
            System.out.println("ZEROS RUN");
            System.out.println("Enter coordinates Х or Y (1, 2 or 3 for each):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        table[y][x] = SIGN_O;
    }

    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= 3|| y >= 3){
            System.out.println("THIS FIELD IS BUSY OR DO NOT EXIST");
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
