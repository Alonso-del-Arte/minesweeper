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

/**
 * Represents a position on the board. An instance of this class only indicates 
 * a position on the board, it does not indicate whether the position has a mine 
 * nor whether the position has been flagged.
 * @author Alonso del Arte
 */
public class Position {
    
    private final int coordX, coordY;
    
    @Override
    public String toString() {
        return "(" + this.coordX + ", " + this.coordY + ")";
    }
    
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
