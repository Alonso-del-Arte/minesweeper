/*
 * Copyright (C) 2023 Alonso del Arte
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

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

/**
 * Icon for an individual square on the Minesweeper board.
 * @author Alonso del Arte
 */
class PatchIcon implements Icon {
    
    // TODO: Write tests for this
    @Override
    public int getIconHeight() {
        return Integer.MIN_VALUE;
    }
    
    // TODO: Write tests for this
    @Override
    public int getIconWidth() {
        return Integer.MAX_VALUE;
    }
    
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        // TODO: Write tests for this
    }
    
}
