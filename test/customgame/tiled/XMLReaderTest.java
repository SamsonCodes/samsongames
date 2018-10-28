/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customgame.tiled;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Samson
 */
public class XMLReaderTest {
    
    private String source, expected;
    
    public XMLReaderTest() {
    }
    
    @Before
    public void setUp()
    {
         source = "blblblal<><>Aadjfa <info company=\"Toshiba\" date=\"25-10-19\"> adfa<Daaf> Daafafd><Ad";
         expected = "<info company=\"Toshiba\" date=\"25-10-19\">";
         System.out.println("Set up complete!");
    }

    /**
     * Test of getElementsPlus method, of class XMLReader.
     */
    @Test
    public void testGetElementsPlus_String_ArrayList() {        
    }

    /**
     * Test of getElementPlus method, of class XMLReader.
     */
    @Test
    public void testGetElementPlus_String_String() {
       
        String output = XMLReader.getElementPlus("info", source);
        assertEquals(expected, output);
    }

    /**
     * Test of getElementPlus method, of class XMLReader.
     */
    @Test
    public void testGetElementPlus_String_ArrayList() {
    }

    /**
     * Test of getElementsPlus method, of class XMLReader.
     */
    @Test
    public void testGetElementsPlus_String_String() {
    }

    /**
     * Test of getAttribute method, of class XMLReader.
     */
    @Test
    public void testGetAttribute() {
    }

    /**
     * Test of stripTags method, of class XMLReader.
     */
    @Test
    public void testStripTags() {
    }

    /**
     * Test of countChar method, of class XMLReader.
     */
    @Test
    public void testCountChar() {
    }
    
}
