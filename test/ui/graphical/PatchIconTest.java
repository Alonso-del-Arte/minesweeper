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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of the PatchIcon class.
 * @author Alonso del Arte
 */
public class PatchIconTest {
    
    @Test
    public void testDefaultDimensionConstant() {
        Dimension expected = new Dimension(16, 16);
        Dimension actual = PatchIcon.DEFAULT_DIMENSION;
        assertEquals(expected, actual);
    }
    
    /**
     * Test of getIconHeight method, of class PatchIcon.
     */
    @Test
    public void testGetIconHeight() {
        System.out.println("getIconHeight");
        PatchIcon instance = new PatchIcon();
        int expResult = 0;
        int result = instance.getIconHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIconWidth method, of class PatchIcon.
     */
    @Test
    public void testGetIconWidth() {
        System.out.println("getIconWidth");
        PatchIcon instance = new PatchIcon();
        int expResult = 0;
        int result = instance.getIconWidth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of paintIcon method, of class PatchIcon.
     */
    @Test
    public void testPaintIcon() {
        System.out.println("paintIcon");
        Component c = null;
        Graphics g = null;
        int x = 0;
        int y = 0;
        PatchIcon instance = new PatchIcon();
        instance.paintIcon(c, g, x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
