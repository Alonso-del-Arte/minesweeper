/*
 * Copyright (C) 2020 Alonso del Arte
 *
 * This program is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free Software 
 * Foundation, either version 3 of the License, or (at your option) any later 
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more 
 * details.
 *
 * You should have received a copy of the GNU General Public License along with 
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package minesweeper;

import java.util.HashSet;

/**
 * Represents a position on the board. An instance of this class only indicates 
 * a position on the board, it does not indicate whether the position has a mine 
 * nor whether the position has been flagged. Positions with negative 
 * coordinates are not allowed.
 * @author Alonso del Arte
 */
public class Position {
    
    private final int coordX, coordY;
    
    @Override
    public String toString() {
        return "(" + this.coordX + ", " + this.coordY + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) return false;
        Position other = (Position) obj;
        return (this.coordX == other.coordX) 
                && (this.coordY == other.coordY);
    }

    @Override
    public int hashCode() {
        return (this.coordX << 16) + (this.coordY & 65535);
    }
    
    private boolean isOutsideBounds(Position boundingCorner) {
        return (this.coordX > boundingCorner.coordX 
                && this.coordY > boundingCorner.coordY);
    }
    
    // STUB TO FAIL THE FIRST TEST
    public HashSet<Position> getNeighbors(Position boundingCorner) {
        HashSet<Position> set = this.getNeighbors();
//        set.removeIf(pos -> pos.isOutsideBounds(boundingCorner));
        return set;
    }
    
    // STUB TO FAIL THE FIRST TEST
    public HashSet<Position> getNeighbors() {
        return new HashSet<>();
    }
    
    // STUB TO FAIL THE FIRST TEST
    public Position nextColumn() {
        return new Position(this.coordX, this.coordY);
    }
    
    // STUB TO FAIL THE FIRST TEST
    public Position nextRow() {
        return new Position(this.coordX, this.coordY);
    }
    
    // STUB TO FAIL THE FIRST TEST
    public Position nextRowColumnZero() {
        return new Position(this.coordX, this.coordY);
    }
    
    /**
     * Constructs a new instance.
     * @param x An integer, at least 0 but no greater than 
     * <code>Integer.MAX_VALUE</code>. For best results, it should not be 
     * greater than 128.
     * @param y An integer, at least 0 but no greater than 
     * <code>Integer.MAX_VALUE</code>. For best results, it should not be 
     * greater than 128.
     * @throws IllegalArgumentException If either <code>x</code> or 
     * <code>y</code> is negative.
     */
    public Position(int x, int y) {
        if (x < 0 || y < 0) {
            String excMsg = "Coordinates with negative number(s) (" + x + ", " 
                    + y + ") are not allowed";
            throw new IllegalArgumentException(excMsg);
        }
        this.coordX = x;
        this.coordY = y;
    }
    
}
