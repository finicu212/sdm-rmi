package interfaces;

import java.io.Serializable;

public class Seat implements Serializable {
    private final int pos_row, pos_column;

    public Seat(int x, int y) {
        pos_row = x;
        pos_column = y;
    }

    public int getRow() {
        return pos_row;
    }

    public int getCol() {
        return pos_column;
    }

    @Override
    public String toString() {
        return pos_row + "," + pos_column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;
        Seat s = (Seat) o;

        return this.pos_row == s.pos_row &&
                this.pos_column == s.pos_column;
    }
}
