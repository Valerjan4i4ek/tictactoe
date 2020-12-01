package test.tictactoe;

import java.io.Serializable;
import java.util.Arrays;

public class History implements Serializable {
    String pl1;
    String pl2;
    char[][] table;

    public History(String pl1, String pl2, char[][] table)
    {
        this.pl1 = pl1;
        this.pl2 = pl2;
        this.table = table;

    }

    @Override
    public String toString() {
        return "History{" +
                "pl1='" + pl1 + '\'' +
                ", pl2='" + pl2 + '\'' +
                ", table=" + Arrays.toString(table) +
                '}';
    }

    public String getPl1() {
        return pl1;
    }

    public String getPl2() {
        return pl2;
    }

    public char[][] getTable() {
        return table;
    }
}
