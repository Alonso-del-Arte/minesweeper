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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of the Board class.
 * @author Alonso del Arte
 */
public class BoardTest {
    
    private static final Position POSITION_ZERO = new Position(0, 0);
    
    /**
     * Test of query function, of class Board. At the beginning of the game, all 
     * positions should be covered.
     */
    @Test
    public void testInitialQuery() {
        Position maxPos = PositionTest.makePosition();
        HashSet<Position> mineLocs = new HashSet<>();
        Board board = new Board(maxPos, mineLocs);
        for (Position curr = POSITION_ZERO;
                curr.isWithinBounds(POSITION_ZERO);
                curr = curr.nextColumnWithReset(maxPos)) {
            assertEquals(PositionStatus.COVERED, board.query(curr));
        }
    }
    
    /**
     * Test of gameUnderway function, of class Board.
     */
    @Test
    public void testGameUnderway() {
        System.out.println("gameUnderway");
        Position maxPos = PositionTest.makePosition();
        HashSet<Position> mineLocs = new HashSet<>();
        Board board = new Board(maxPos, mineLocs);
        String msg = "Game should be considered to be underway";
        assert board.gameUnderway() : msg;
    }
    
    /**
     * Test of query function, of class Board.
     */
    @Test
    public void testQuery() {
        System.out.println("query");
        Position centerSquare = POSITION_ZERO.nextColumn().nextRow();
        Position sizingCorner = centerSquare.nextColumn().nextRow();
        HashSet<Position> edgeSquares = centerSquare.getNeighbors();
        ArrayList<Position> neighbors = new ArrayList<>(edgeSquares);
        HashSet<Position> mineLocs;
        Board board;
        Optional<Mine> option;
        PositionStatus[] statuses = PositionStatus.values();
        for (int n = 0; n <= edgeSquares.size(); n++) {
            mineLocs = new HashSet<>(neighbors.subList(0, n));
            board = new Board(sizingCorner, mineLocs);
            option = board.reveal(centerSquare);
            assert !option.isPresent() : "Center square should be empty";
            assertEquals(statuses[n], board.query(centerSquare));
        }
    }

    /**
     * Test of reveal function, of class Board.
     */
    @Test
    public void testReveal() {
        System.out.println("reveal");
        HashSet<Position> mineLocs = new HashSet<>();
        mineLocs.add(POSITION_ZERO);
        Board board = new Board(POSITION_ZERO, mineLocs);
        Optional<Mine> option = board.reveal(POSITION_ZERO);
        if (option.isPresent()) {
            assertEquals(POSITION_ZERO, option.get().getPosition());
        } else {
            fail("reveal() function should have revealed a mine");
        }
    }

    /**
     * Another test of reveal function, of class Board.
     */
    @Test
    public void testRevealEmptySwath() {
        int minedColumnEndX = 28;
        int minedColumnY = (int) Math.floor(Math.random() * 50) + 14;
        Position minedColumnStart = new Position(0, minedColumnY);
        Position minedColumnEnd = new Position(minedColumnEndX, minedColumnY);
        HashSet<Position> mineLocs = new HashSet<>();
        for (Position loc = minedColumnStart;
                loc.isWithinBounds(minedColumnEnd);
                loc = loc.nextRow()) {
            mineLocs.add(loc);
        }
        Board board = new Board(minedColumnEnd, mineLocs);
        board.reveal(POSITION_ZERO);
        Position presumedEmpty;
        String msg;
        for (int x = 0; x <= minedColumnEndX; x++) {
            for (int y = 0; y < minedColumnY - 1; y++) {
                presumedEmpty = new Position(x, y);
                msg = "Position " + presumedEmpty.toString() 
                        + " should be empty and without neighbors";
                assertEquals(msg, PositionStatus.REVEALED_EMPTY, 
                        board.query(presumedEmpty));
            }
            presumedEmpty = new Position(x, minedColumnY - 1);
            msg = "Position " + presumedEmpty.toString() 
                    + " should be empty but it should neighbor at least two mines";
            char neighborCount = board.query(presumedEmpty).getChar();
            assert neighborCount > '1' && neighborCount < '4' : msg;
        }
    }

    /**
     * Another test of reveal function, of class Board.
     */
    @Test
    public void testNoRevealForAlreadyRevealed() {
        Position mineLoc = PositionTest.makePosition();
        HashSet<Position> mineLocs = new HashSet<>();
        mineLocs.add(mineLoc);
        Position mineNeighbor = mineLoc.nextRow();
        Board board = new Board(mineNeighbor.nextColumn().nextRow(), mineLocs);
        Optional<Mine> option = board.reveal(mineNeighbor);
        String msg = "Position " + mineNeighbor + " should be empty";
        assert !option.isPresent() : msg;
        assertEquals(PositionStatus.REVEALED_EMPTY_NEAR_1, 
                board.query(mineNeighbor));
        try {
            option = board.reveal(mineNeighbor);
            msg = "Position " + mineNeighbor.toString() 
                    + " should not have been revealed a second time as " 
                    + option.toString();
            fail(msg);
        } catch (IllegalStateException ise) {
            System.out.println("Trying to reveal " + mineNeighbor.toString() 
                    + " twice correctly caused IllegalStateException");
            System.out.println("\"" + ise.getMessage() + "\"");
        } catch (RuntimeException re) {
            msg = re.getClass().getName() 
                    + " is the wrong exception to throw for trying to reveal " 
                    + mineNeighbor.toString() + " twice";
            fail(msg);
        }
    }

    /**
     * Another test of reveal function, of class Board.
     */
    @Test
    public void testNoRevealAfterGameOver() {
        Position mineLoc = PositionTest.makePosition();
        HashSet<Position> mineLocs = new HashSet<>();
        mineLocs.add(mineLoc);
        Position mineNeighbor = mineLoc.nextRow();
        Board board = new Board(mineNeighbor.nextColumn().nextRow(), mineLocs);
        Optional<Mine> option = board.reveal(mineLoc);
        if (option.isPresent()) {
            assertEquals(mineLoc, option.get().getPosition());
        } else {
            fail("reveal() function should have revealed a mine");
        }
        try {
            option = board.reveal(mineNeighbor);
            String msg = "Position " + mineNeighbor.toString() 
                    + " should not have been revealed as " + option.toString() 
                    + " after game over";
            fail(msg);
        } catch (IllegalStateException ise) {
            System.out.println("Trying to reveal " + mineNeighbor.toString() 
                    + " after game over correctly caused IllegalStateException");
            String excMsg = ise.getMessage();
            System.out.println("\"" + excMsg + "\"");
            String msg 
                    = "Exception message should contain the words \"Game Over\"";
            assert excMsg.toLowerCase().replace(" ", "").contains("gameover") 
                    : msg;
        } catch (RuntimeException re) {
            String msg = re.getClass().getName() 
                    + " is the wrong exception to throw for trying to reveal " 
                    + mineNeighbor.toString() + " after game over";
            fail(msg);
        }
    }

    /**
     * Test of flag procedure, of class Board.
     */
    @Test
    public void testFlag() {
        System.out.println("flag");
        HashSet<Position> mineLocs = new HashSet<>();
        mineLocs.add(POSITION_ZERO);
        Board board = new Board(POSITION_ZERO, mineLocs);
        board.flag(POSITION_ZERO);
        assertEquals(PositionStatus.FLAGGED, board.query(POSITION_ZERO));
    }

    /**
     * Test of unflag procedure, of class Board.
     */
    @Test
    public void testUnflag() {
        System.out.println("unflag");
        HashSet<Position> mineLocs = new HashSet<>();
        mineLocs.add(POSITION_ZERO);
        Board board = new Board(POSITION_ZERO, mineLocs);
        board.flag(POSITION_ZERO);
        assertEquals(PositionStatus.FLAGGED, board.query(POSITION_ZERO));
        board.unflag(POSITION_ZERO);
        assertEquals(PositionStatus.COVERED, board.query(POSITION_ZERO));
    }

    /**
     * Another test of flag procedure, of class Board.
     */
    @Test
    public void testNoFlagForAlreadyFlagged() {
        Position mineLoc = PositionTest.makePosition();
        HashSet<Position> mineLocs = new HashSet<>();
        mineLocs.add(mineLoc);
        Board board = new Board(mineLoc.nextColumn(), mineLocs);
        board.flag(mineLoc);
        assertEquals(PositionStatus.FLAGGED, board.query(mineLoc));
        try {
            board.flag(mineLoc);
            String msg = "Should not have been able to flag " 
                    + mineLoc.toString() + " again without first unflagging";
            fail(msg);
        } catch (IllegalStateException ise) {
            System.out.println("Trying to flag " + mineLoc.toString() 
                    + " twice correctly caused IllegalStateException");
            System.out.println("\"" + ise.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = re.getClass().getName() 
                    + " is the wrong exception to throw for trying to flag " 
                    + mineLoc.toString() + " twice";
            fail(msg);
        }
    }

    /**
     * Another test of flag procedure, of class Board.
     */
    @Test
    public void testNoFlagAfterGameOver() {
        Position mineLoc = PositionTest.makePosition();
        HashSet<Position> mineLocs = new HashSet<>();
        mineLocs.add(mineLoc);
        Board board = new Board(mineLoc.nextColumn(), mineLocs);
        Optional<Mine> option = board.reveal(mineLoc);
        String msg = "Position " + mineLoc.toString() + " should have a mine";
        assert option.isPresent() : msg;
        msg = "Detonating mine at " + mineLoc.toString() 
                + " should be game over";
        assert !board.gameUnderway() : msg;
        assertEquals(PositionStatus.DETONATED, board.query(mineLoc));
        try {
            board.flag(mineLoc);
            msg = "Should not have been able to flag position " 
                    + mineLoc.toString() + " after detonating its mine";
            fail(msg);
        } catch (IllegalStateException ise) {
            System.out.println("Trying to flag " + mineLoc.toString() 
                    + " after detonation correctly caused IllegalStateException");
            String excMsg = ise.getMessage();
            System.out.println("\"" + excMsg + "\"");
            msg = "Exception message should contain the words \"Game Over\"";
            assert excMsg.toLowerCase().replace(" ", "").contains("gameover") 
                    : msg;
        } catch (RuntimeException re) {
            msg = re.getClass().getName() 
                    + " is the wrong exception to throw for trying to flag " 
                    + mineLoc.toString() + " after detonation";
            fail(msg);
        }
    }

    /**
     * Another test of unflag procedure, of class Board.
     */
    @Test
    public void testNoUnflagIfNotFlagged() {
        Position mineLoc = PositionTest.makePosition();
        HashSet<Position> mineLocs = new HashSet<>();
        mineLocs.add(mineLoc);
        Board board = new Board(mineLoc.nextColumn(), mineLocs);
        assertEquals(PositionStatus.COVERED, board.query(mineLoc));
        try {
            board.unflag(mineLoc);
            String msg = "Should not have been able to unflag " 
                    + mineLoc.toString() + " again without first flagging";
            fail(msg);
        } catch (IllegalStateException ise) {
            System.out.println("Trying to unflag " + mineLoc.toString() 
                    + " twice correctly caused IllegalStateException");
            System.out.println("\"" + ise.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = re.getClass().getName() 
                    + " is the wrong exception to throw for trying to unflag " 
                    + mineLoc.toString() + " twice";
            fail(msg);
        }
    }

    /**
     * Another test of unflag procedure, of class Board.
     */
    @Test
    public void testNoUnflagAfterGameOver() {
        Position mineLoc = PositionTest.makePosition();
        HashSet<Position> mineLocs = new HashSet<>();
        mineLocs.add(mineLoc);
        Board board = new Board(mineLoc.nextColumn().nextRow(), mineLocs);
        Position mineNeighbor = mineLoc.nextColumn();
        board.flag(mineNeighbor);
        Optional<Mine> option = board.reveal(mineLoc);
        String msg = "Position " + mineLoc.toString() + " should have a mine";
        assert option.isPresent() : msg;
        msg = "Detonating mine at " + mineLoc.toString() 
                + " should be game over";
        assert !board.gameUnderway() : msg;
        assertEquals(PositionStatus.DETONATED, board.query(mineLoc));
        try {
            board.unflag(mineNeighbor);
            msg = "Should not have been able to unflag position " 
                    + mineNeighbor.toString() 
                    + " after detonating neighboring mine";
            fail(msg);
        } catch (IllegalStateException ise) {
            System.out.println("Trying to flag " + mineNeighbor.toString() 
                    + " after game over correctly caused IllegalStateException");
            String excMsg = ise.getMessage();
            System.out.println("\"" + excMsg + "\"");
            msg = "Exception message should contain the words \"Game Over\"";
            assert excMsg.toLowerCase().replace(" ", "").contains("gameover") 
                    : msg;
        } catch (RuntimeException re) {
            msg = re.getClass().getName() 
                    + " is the wrong exception to throw for trying to flag " 
                    + mineLoc.toString() + " after detonating its neighbor";
            fail(msg);
        }
    }

    /**
     * Test of makeBoard function, of class Board.
     */
    @Test
    public void testMakeBoard() {
        System.out.println("makeBoard");
//        int numberOfMines = 0;
//        Position maxPosition = null;
//        Board expResult = null;
//        Board result = Board.makeBoard(numberOfMines, maxPosition);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Another test of gameUnderway function, of class Board.
     */
    @Test
    public void testGameOver() {
        Position maxPos = PositionTest.makePosition();
        HashSet<Position> mineLocs = new HashSet<>();
        mineLocs.add(POSITION_ZERO);
        Board board = new Board(maxPos, mineLocs);
        board.reveal(POSITION_ZERO);
        String msg = "Game should be considered over after detonating mine";
        assert !board.gameUnderway() : msg;
    }
    
}
