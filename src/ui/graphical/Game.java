/*
 * Copyright (C) 2024 Alonso del Arte
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
package ui.graphical;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import minesweeper.Board;
import minesweeper.Position;

/**
 *
 * @author Alonso del Arte
 */
public class Game extends JFrame {
    
    // TODO: Implement the Minesweeper game as a Java Swing program
    
    Game(Board board) {
        this.setTitle("WRONG TITLE TO FAIL FIRST TEST");
        JPanel panel = new JPanel();
        String text = "Sorry, graphical UI is not ready yet";
        panel.add(new JLabel(text));
        panel.add(new JLabel(text));
        panel.add(new JLabel(text));
        Dimension dimension = new Dimension(360, 240);
        this.setPreferredSize(dimension);
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        Position maxPosition = new Position(10, 10);
        Board board = Board.makeBoard(10, maxPosition);
        Game game = new Game(board);
    }
    
}
