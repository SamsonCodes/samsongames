/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customgame.data;

import java.io.File;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author Samson
 */
public class DataHandlerTest
{
    
    public DataHandlerTest()
    {
        File here = new File(".");
        System.out.println(here.getAbsolutePath());
    }
    
    @Test
    public void testSaving()
    {
        ArrayList<String> data = new ArrayList();
        data.add("line1");
        data.add("line2");
        String filePath = "C:\\Users\\Samson\\Documents\\NetBeansProjects\\SamsonGames\\test\\samsongamestest\\tmx\\test.txt";
        DataHandler.saveData(data, filePath);
        if(!DataHandler.loadData(filePath).equals("line1line2"))
        {
            System.out.println("testSavingAndDeleting(): failed to load saved data");
            fail();
        }
    }
    /*
        This test won't work unless you create the file you want to delete first. 
        There isn't enough time to save and the delete it in the same test method. Thread.sleep didn't work either.
        Also, when run in conjunction with the saveTest, it is not clear which file is being deleted, 
        the one that was there already, or the new one? So to run this test in a meaningful
        way, run it after the test.txt is created, but without the testSave method also running.
    */
//    @Test
//    public void testDeleting()
//    {
//        
//        String filePath = "C:\\Users\\Samson\\Documents\\NetBeansProjects\\SamsonGames\\test\\samsongamestest\\tmx\\test.txt";
//        
//        DataHandler.deleteData(filePath);
//        if(DataHandler.loadData(filePath).equals("line1line2"))
//        {
//            System.out.println("testSavingAndDeleting(): failed to delete data");
//            fail();
//        }
//    }

    /**
     * Test of loadData method, of class DataHandler.
     */
    @Test
    public void testLoadData()
    {
        String PATH = "C:\\Users\\Samson\\Documents\\NetBeansProjects\\SamsonGames\\test\\samsongamestest\\tmx\\minimap.tmx";
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<map version=\"1.0\" orientation=\"orthogonal\" renderorder=\"right-down\" width=\"10\" height=\"10\" tilewidth=\"50\" tileheight=\"50\" nextobjectid=\"1\">" +
            " <tileset firstgid=\"1\" name=\"mini_tileset\" tilewidth=\"50\" tileheight=\"50\" spacing=\"1\" tilecount=\"264\" columns=\"22\">" +
            "  <image source=\"mini_tileset.png\" trans=\"ffffff\" width=\"1152\" height=\"648\"/>" +
            " </tileset>" +
            " <layer name=\"Tile Layer 1\" width=\"10\" height=\"10\">" +
            "  <data encoding=\"csv\">" +
            "2,2,2,1,1,1,1,1,1,1," +
            "2,2,2,1,1,1,1,1,1,1," +
            "2,2,2,1,1,1,1,1,1,1," +
            "2,2,2,1,1,24,24,1,1,1," +
            "2,2,2,1,1,24,24,1,1,1," +
            "2,2,2,1,23,24,24,23,1,1," +
            "2,2,2,1,23,24,24,23,1,1," +
            "2,2,2,1,23,23,23,23,1,1," +
            "2,2,2,1,1,1,1,1,1,1," +
            "2,2,2,1,1,1,1,1,1,1" +
            "</data>" +
            " </layer>" +
            "</map>" +
            "";
        assertEquals(expected, DataHandler.loadData(PATH));
    }
    
    public void testLoadImage()
    {
        String PATH = "C:\\Users\\Samson\\Documents\\NetBeansProjects\\SamsonGames\\test\\samsongamestest\\images\\tileset-advance.png";
        if(DataHandler.loadImage(PATH) == null)
            fail();
    }
    
}
