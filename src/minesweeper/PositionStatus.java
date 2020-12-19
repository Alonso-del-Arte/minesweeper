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
 * Indicates the status of a position.
 * @author Alonso del Arte
 */
public enum PositionStatus {
    
    /**
     * Indicates the position has been revealed to not contain a mine. Depending 
     * on the user interface, the player might receive some indication as to the 
     * possible status of neighboring positions.
     */
    REVEALED_EMPTY (' '),
    
    /**
     * Indicates the position has been revealed to not contain a mine, but it 
     * neighbors one mine. Depending on the user interface, the player might 
     * receive some indication as to the possible status of neighboring 
     * positions.
     */
    REVEALED_EMPTY_NEAR_1 ('1'),
    
    /**
     * Indicates the position has been revealed to not contain a mine, but it 
     * neighbors two mines. Depending on the user interface, the player might 
     * receive some indication as to the possible status of neighboring 
     * positions.
     */
    REVEALED_EMPTY_NEAR_2 ('2'),
    
    /**
     * Indicates the position has been revealed to not contain a mine, but it 
     * neighbors three mines. Depending on the user interface, the player might 
     * receive some indication as to the possible status of neighboring 
     * positions.
     */
    REVEALED_EMPTY_NEAR_3 ('3'),
    
    /**
     * Indicates the position has been revealed to not contain a mine, but it 
     * neighbors four mines. Depending on the user interface, the player might 
     * receive some indication as to the possible status of neighboring 
     * positions.
     */
    REVEALED_EMPTY_NEAR_4 ('4'),
    
    /**
     * Indicates the position has been revealed to not contain a mine, but it 
     * neighbors five mines. Depending on the user interface, the player might 
     * receive some indication as to the possible status of neighboring 
     * positions.
     */
    REVEALED_EMPTY_NEAR_5 ('5'),
    
    /**
     * Indicates the position has been revealed to not contain a mine, but it 
     * neighbors six mines. Depending on the user interface, the player might 
     * receive some indication as to the possible status of neighboring 
     * positions.
     */
    REVEALED_EMPTY_NEAR_6 ('6'),
    
    /**
     * Indicates the position has been revealed to not contain a mine, but it 
     * neighbors seven mines. Depending on the user interface, the player might 
     * receive some indication as to the possible status of neighboring 
     * positions.
     */
    REVEALED_EMPTY_NEAR_7 ('7'),
    
    /**
     * Indicates the position has been revealed to not contain a mine, but it 
     * neighbors eight mines. Depending on the user interface, the player might 
     * receive some indication as to the possible status of neighboring 
     * positions.
     */
    REVEALED_EMPTY_NEAR_8 ('8'),
    
    /**
     * Indicates the position is covered. It may or may not have a mine. The 
     * player has not asserted anything about the position.
     */
    COVERED ('?'),
    
    /**
     * Indicates the position has a mine but the mine has not detonated. This 
     * status should only occur when the player has lost the game by detonating 
     * another mine in the field.
     */
    REVEALED_MINED ('x'),
    
    /**
     * Indicates the position has been flagged because the player believes the 
     * position has a mine. Whether or not the position actually does have a 
     * mine, well, that's the fun of the game, isn't it?
     */
    FLAGGED ('!'),
    
    /**
     * Indicates the position was flagged incorrectly. Because of this mistake, 
     * the player probably stepped on a mine in another position.
     */
    WRONGLY_FLAGGED ('w'),
    
    /**
     * Indicates the position has a mine that detonated. The player has lost the 
     * game.
     */
    DETONATED ('X');
    
    private final char symbol;
    
    // STUB TO FAIL THE FIRST TEST
    public char getChar() {
        return this.symbol;
    }

    private PositionStatus(char ch) {
        this.symbol = ch;
    }
    
    
    
}
