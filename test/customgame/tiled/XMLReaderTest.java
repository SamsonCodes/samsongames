/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customgame.tiled;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Samson
 */
public class XMLReaderTest {
    
    private String source1, source2;
    private ArrayList<String> list;
    
    public XMLReaderTest() {
    }
    
    @Before
    public void setUp()
    {
         source1 = "blblblal<><>Aadjfa <info company=\"Toshiba\" date=\"25-10-19\"> adfa<Daaf> Daafafd><Ad";
         source2 = "<><Asda><thing> ahahaha, hadfhafhd </thing>da<a>33</a><thing attr=\"12\">dafda7777</thing> stuff";
         list = new ArrayList();
         list.add(source1);
         list.add(source2);
         System.out.println("Set up complete!");
    }

    /**
     * Test of getElementsPlus method, of class XMLReader.
     */
    @Test
    public void testGetElementsPlus_String_ArrayList() {   
        ArrayList<String> expected = new ArrayList();
        expected.add("<thing> ahahaha, hadfhafhd </thing>");
        expected.add("<thing attr=\"12\">dafda7777</thing>");
        ArrayList<String> output = XMLReader.getElementsPlus("thing", list);
        assertEquals(expected, output);
    }

    /**
     * Test of getElementPlus method, of class XMLReader.
     */
    @Test
    public void testGetElementPlus_String_String() {
        String expected = "<info company=\"Toshiba\" date=\"25-10-19\">";
        String output = XMLReader.getElementPlus("info", source1);
        assertEquals(expected, output);
    }

    /**
     * Test of getElementPlus method, of class XMLReader.
     */
    @Test
    public void testGetElementPlus_String_ArrayList() {
        ArrayList<String> expected = new ArrayList();
        expected.add("<info company=\"Toshiba\" date=\"25-10-19\">");
        ArrayList<String> output = XMLReader.getElementsPlus("info", list);
        assertEquals(expected, output);
    }

    /**
     * Test of getElementsPlus method, of class XMLReader.
     */
    @Test
    public void testGetElementsPlus_String_String() {
        String[] expected = {"<thing> ahahaha, hadfhafhd </thing>","<thing attr=\"12\">dafda7777</thing>"};
        ArrayList<String> output = XMLReader.getElementsPlus("thing", source2);
        String[] outputArray = new String[output.size()];
        for(int i = 0; i < output.size(); i++)
        {
            outputArray[i] = output.get(i);
        }
        assertArrayEquals(expected, outputArray);
    }

    /**
     * Test of getAttribute method, of class XMLReader.
     */
    @Test
    public void testGetAttribute() {
        String expected = "12";
        String output = XMLReader.getAttribute("attr", "<thing attr=\"12\">dafda7777</thing>");
        assertEquals(expected, output);
    }

    /**
     * Test of stripTags method, of class XMLReader.
     */
    @Test
    public void testStripTags() {
        String expected = "dafda7777";
        String output = XMLReader.stripTags("thing", "<thing attr=\"12\">dafda7777</thing>");
        assertEquals(expected, output);
    }

    /**
     * Test of countChar method, of class XMLReader.
     */
    @Test
    public void testCountChar() {
        String s1 = "1234567891";
        String s2 = "kk ii kk 88";
        int output1 = XMLReader.countChar('1', s1);
        assertEquals(output1, 2);
        int output2 = XMLReader.countChar('2', s1);
        assertEquals(output2, 1);
        int output3 = XMLReader.countChar(' ', s2);
        assertEquals(output3, 3);
        int output4 = XMLReader.countChar('k', s2);
        assertEquals(output4, 4);
        
    }
    
}
