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
 * Indicates that a position is believed to have a mine.
 * @author Alonso del Arte
 */
public class Flag {
    
    private final Position position;
    
    private final boolean correctness;
    
    // STUB TO FAIL THE FIRST TEST
    public Position getPosition() {
        return new Position(0, 0);
    }
    
    boolean isCorrect() {
        return this.correctness;
    }
    
    // STUB TO FAIL THE FIRST TEST
    public static Flag placeFlag(Position position) {
        return new Flag(position, true);
    }
    
    Flag(Position pos, boolean correct) {
        this.position = pos;
        this.correctness = correct;
    }
    
}
