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
package ui.text;

import minesweeper.Position;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of the Game class.
 * @author Alonso del Arte
 */
public class GameTest {
    
    /**
     * Test of parsePosition method, of class Game. Since the board is supposed 
     * to be 10 &times; 26, with <i>x</i> labeled by a single digit and <i>y</i> 
     * labeled by a single letter (from A to Z), the combination of a letter and 
     * a digit should be recognized as referring to the appropriate position on 
     * the board.
     */
    @Test
    public void testParsePosition() {
        System.out.println("parsePosition");
        String s;
        Position expected, actual;
        for (char letter = 'A'; letter < '\u005B'; letter += '\u0001') {
            for (char digit = '0'; digit < ':'; digit += '\u0001') {
                s = "" + letter + digit;
                expected = new Position(digit - 48, letter - 65);
                actual = Game.parsePosition(s);
                assertEquals(expected, actual);
            }
        }
    }

    /**
     * Another test of parsePosition method, of class Game. Since it's entirely 
     * possible that the player might refer to a position using a digit and 
     * letter combination rather than a letter and digit combination, the 
     * parsing function should be able to recognize those as valid.
     */
    @Test
    public void testParsePositionDigitLetter() {
        String s;
        Position expected, actual;
        for (char letter = 'A'; letter < '\u005B'; letter += '\u0001') {
            for (char digit = '0'; digit < ':'; digit += '\u0001') {
                s = "" + digit + letter;
                expected = new Position(digit - 48, letter - 65);
                actual = Game.parsePosition(s);
                assertEquals(expected, actual);
            }
        }
    }

    /**
     * Test of main method, of class Game.
     */
    @Test
    public void testMain() {
        System.out.println("main");
//        String[] args = null;
//        Game.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
