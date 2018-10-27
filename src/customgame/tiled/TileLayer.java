/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.tiled;

public class TileLayer 
{
    private String name;
    private int width, height;
    private int[] tileNumbers;
    
    
    public TileLayer(String name, int width, int[] tiles)
    {
        this.name = name;
        this.width = width;
        height = tiles.length/width;        
        if(tiles.length % width != 0)
            System.out.println("TileLayer: fishy dimensions detected! width = " 
                    + width + " height = " + height + " tileAmount = " + tiles.length);
        this.tileNumbers = tiles;
    }
    
    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getTileNumbers(){
        return tileNumbers;
    }
}
