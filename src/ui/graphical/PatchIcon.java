/*
 * Copyright (C) 2025 Alonso del Arte
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
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.Icon;

/**
 * Icon for an individual square on the Minesweeper board. If I remember 
 * correctly, the idea here was that this would be a square that hasn't been 
 * revealed to be mined or not mined nor has it been flagged.
 * @author Alonso del Arte
 */
class PatchIcon implements Icon {
    
    // TODO: Write tests for this
    private static final Dimension DEFAULT_DIMENSION 
            = new Dimension(Byte.MAX_VALUE, 128);
    
    public Dimension getDimension() {
        return new Dimension(20, 21);
    }
    
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
    
    PatchIcon() {
        this(new Dimension(1, 1));
    }

    PatchIcon(Dimension dimension) {
        // TODO: Write tests for this
    }
    
}
