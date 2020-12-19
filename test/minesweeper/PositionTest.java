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

import java.awt.Point;
import java.util.HashSet;
import java.util.Random;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author Alonso del Arte
 */
public class PositionTest {
    
    private static final Random RANDOM = new Random();
    
    /**
     * Uses a pseudorandom number generator to choose a position on a 128 
     * &times; 128 board. This function is not meant for a production context.
     * @return A pseudorandomly chosen <code>Position</code>, one of (0, 0), (0, 
     * 127), (127, 0), (127, 127) or any position in the square area bounded by 
     * the aforementioned positions.
     */
    static Position makePosition() {
        int x = RANDOM.nextInt(128);
        int y = RANDOM.nextInt(128);
        return new Position(x, y);
    }
    
    @Test
    public void testToString() {
        System.out.println("toString");
        int x = RANDOM.nextInt(32);
        int y = RANDOM.nextInt(32);
        Position position = new Position(x, y);
        String expected = "(" + x + "," + y + ")";
        String actual = position.toString().replace(" ", "");
        assertEquals(expected, actual);
    }
    
    @Test
    public void testReferentialEquality() {
        Position position = makePosition();
        assertEquals(position, position);
    }
    
    @Test
    public void testNotEqualsNull() {
        Position position = makePosition();
        assertNotEquals(position, null);
    }
    
    @Test
    public void testNotEqualsDiffClass() {
        int x = RANDOM.nextInt(1920);
        int y = RANDOM.nextInt(1280);
        Position position = new Position(x, y);
        Point point = new Point(x, y);
        assertNotEquals(position, point);
    }
    
    @Test
    public void testNotEqualsDiffX() {
        int x = RANDOM.nextInt(1024);
        int y = RANDOM.nextInt(1024);
        int translation = RANDOM.nextInt(64) + 1;
        Position positionA = new Position(x, y);
        Position positionB = new Position(x + translation, y);
        assertNotEquals(positionA, positionB);
    }
    
    @Test
    public void testNotEqualsDiffY() {
        int x = RANDOM.nextInt(1024);
        int y = RANDOM.nextInt(1024);
        int translation = RANDOM.nextInt(64) + 1;
        Position positionA = new Position(x, y);
        Position positionB = new Position(x, y + translation);
        assertNotEquals(positionA, positionB);
    }
    
    @Test
    public void testEquals() {
        System.out.println("equals");
        int x = RANDOM.nextInt(960);
        int y = RANDOM.nextInt(720);
        Position somePosition = new Position(x, y);
        Position samePosition = new Position(x, y);
        assertEquals(somePosition, samePosition);
    }
    
    // TODO: Rest of equals() tests
    
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        HashSet<Position> positions = new HashSet<>();
        HashSet<Integer> hashes = new HashSet<>();
        Position curr;
        for (int x = 0; x < 32; x++)  {
            for (int y = 0; y < 32; y++) {
                curr = new Position(x, y);
                positions.add(curr);
                hashes.add(curr.hashCode());
            }
        }
        String msg = "Position set and hash code set should be the same size";
        assertEquals(msg, positions.size(), hashes.size());
    }
    
    @Test
    public void testConstructorRejectsNegativeX() {
        try {
            Position badPosition = new Position(-1, 0);
            String msg = "Should not have been able to create position " 
                    + badPosition.toString();
            fail(msg);
        } catch (IllegalArgumentException iae) {
            System.out.println("Position with negative x was correctly rejected");
            System.out.println("\"" + iae.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = re.getClass().getName() 
                    + " is the wrong exception to throw for negative x";
            fail(msg);
        }
    }
    
    @Test
    public void testConstructorRejectsNegativeY() {
        try {
            Position badPosition = new Position(0, -1);
            String msg = "Should not have been able to create position " 
                    + badPosition.toString();
            fail(msg);
        } catch (IllegalArgumentException iae) {
            System.out.println("Position with negative y was correctly rejected");
            System.out.println("\"" + iae.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = re.getClass().getName() 
                    + " is the wrong exception to throw for negative y";
            fail(msg);
        }
    }
    
}
