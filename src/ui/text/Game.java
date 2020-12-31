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

import minesweeper.Board;
import minesweeper.Position;
import minesweeper.PositionStatus;

import java.util.Optional;
import java.util.Scanner;

/**
 * WORK IN PROGRESS: Play the Minesweeper game on the command line. I'm thikning 
 * the board will be 10 &times; 26 or 26 &times; 10...
 * @author Alonso del Arte
 */
public class Game {
    
    // TODO: Implement the Minesweeper game as a command line program
    
    private static Board gameBoard;
    
    private static void writeBoard() {
        Position curr;
        PositionStatus status;
        System.out.println(" ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        for (int x = 0; x < 10; x++) {
            System.out.print(x);
            for (int y = 0; y < 26; y++) {
                curr = new Position(x, y);
                status = gameBoard.query(curr);
                System.out.print(status.getChar());
            }
            System.out.println();
        }
    }
    
    /**
     * Parses a <code>String</code> to determine a <code>Position</code> on the 
     * 10 &times; 26 board. This function is package private, rather than fully 
     * class private, to enable unit testing.
     * @param s The <code>String</code> to parse. For example, "A7".
     * @return The corresponding <code>Position</code>. For example, (7, 0).
     * @throws IllegalArgumentException If parsing leads to an attempt to 
     * construct a <code>Position</code> object with negative coordinates.
     */
    static Position parsePosition(String s) {
        char letter = s.charAt(0);
        char digit = s.charAt(1);
        if (Character.isAlphabetic(digit)) {
            char swap = letter;
            letter = digit;
            digit = swap;
        }
        return new Position(digit - 48, letter - 65);
    }
    
    private static void parseCommand(String command) {
        command = command.replace(" ", "").toUpperCase();
    }
    
    public static void main(String[] args) {
        System.out.println();
        System.out.println("MINESWEEPER");
        System.out.println();
        Position maxPosition = new Position(9, 25);
        gameBoard = Board.makeBoard(0, maxPosition);
        writeBoard();
        try (Scanner input = new Scanner(System.in)) {
            while (gameBoard.gameUnderway()) {
                //
            }
        }
    }
    
}
