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

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests of the PositionStatus enumeration.
 * @author Alonso del Arte
 */
public class PositionStatusTest {
    
    @Test
    public void testGetChar() {
        System.out.println("getChar");
        assertEquals(' ', PositionStatus.REVEALED_EMPTY.getChar());
        assertEquals('1', PositionStatus.REVEALED_EMPTY_NEAR_1.getChar());
        assertEquals('2', PositionStatus.REVEALED_EMPTY_NEAR_2.getChar());
        assertEquals('3', PositionStatus.REVEALED_EMPTY_NEAR_3.getChar());
        assertEquals('4', PositionStatus.REVEALED_EMPTY_NEAR_4.getChar());
        assertEquals('5', PositionStatus.REVEALED_EMPTY_NEAR_5.getChar());
        assertEquals('6', PositionStatus.REVEALED_EMPTY_NEAR_6.getChar());
        assertEquals('7', PositionStatus.REVEALED_EMPTY_NEAR_7.getChar());
        assertEquals('8', PositionStatus.REVEALED_EMPTY_NEAR_8.getChar());
        assertEquals('?', PositionStatus.COVERED.getChar());
        assertEquals('x', PositionStatus.REVEALED_MINED.getChar());
        assertEquals('!', PositionStatus.FLAGGED.getChar());
        assertEquals('w', PositionStatus.WRONGLY_FLAGGED.getChar());
        assertEquals('X', PositionStatus.DETONATED.getChar());
    }
    
}
