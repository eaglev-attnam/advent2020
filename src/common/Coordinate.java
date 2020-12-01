package common;

import java.util.Objects;

public class Coordinate {
    protected int x;
    protected int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public Coordinate[] getNeighbours() {
    	return new Coordinate[] {
    			new Coordinate(x-1, y),
    			new Coordinate(x+1, y),
    			new Coordinate(x, y-1),
    			new Coordinate(x, y+1)
    	};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    
    @Override
    public String toString() {
    	return "(" + x + "," + y + ")";
    }
 }
