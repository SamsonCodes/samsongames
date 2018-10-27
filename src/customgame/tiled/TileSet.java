/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.tiled;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TileSet 
{
    private String name;
    private int firstGid, tileCount;
    private Tile[] tiles;
    
    public TileSet(String tileSetElement, String tileSetPath)
    {
        String name = XMLReader.getElementPlus("name", tileSetElement);
        int tileWidth, tileHeight, spacing, columns;
        firstGid = Integer.parseInt(XMLReader.getAttribute("firstgid", tileSetElement));
        tileWidth = Integer.parseInt(XMLReader.getAttribute("tilewidth", tileSetElement));
        tileHeight = Integer.parseInt(XMLReader.getAttribute("tileheight", tileSetElement));
        String spacingString = XMLReader.getAttribute("spacing", tileSetElement);
        if(!spacingString.equals(""))       
            spacing = Integer.parseInt(spacingString);
        else
            spacing = 0;
        tileCount = Integer.parseInt(XMLReader.getAttribute("tilecount", tileSetElement));
        columns = Integer.parseInt(XMLReader.getAttribute("columns", tileSetElement));
        String imageElement = XMLReader.getElementPlus("image", tileSetElement);
        String imagePath = XMLReader.getAttribute("source", imageElement);
        BufferedImage tileSet = loadImage(tileSetPath + imagePath.replace("../images/", ""));
        tiles = new Tile[tileCount];
        int rows = tileCount/columns;
        for(int j = 0; j < rows; j++)
            for(int i = 0; i < columns; i++)
            {
                tiles[j*columns + i] = new Tile(tileSet.getSubimage(i * (tileWidth + spacing), 
                        j * (tileHeight + spacing), tileWidth, tileHeight), false);
            }      
    }
    
    private BufferedImage loadImage(String fileName)
    {
        BufferedImage image = null;
        try 
        {
            image = ImageIO.read(new File(fileName));
        } 
        catch (IOException ex)
        {
            System.out.println("TilePalet: loadImage: Error loading "+fileName);
        }
        return image;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Tile[] getTiles()
    {
        return tiles;
    }
    
    public int getTileCount()
    {
        return tileCount;
    }

    public int getFirstGid() {
        return firstGid;
    }
}
