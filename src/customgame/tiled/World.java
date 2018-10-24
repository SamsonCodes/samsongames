/*
    This class allows you to create world objects from Tiled tmx files and render them.
 */

package customgame.tiled;

import customgame.XMLReader;
import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class World 
{
    private Tile[] palet;
    private ArrayList<int[]> tiles;
    private int width, height, tileSize;
    private String name;
    
    public World(Tile[] palet, int tileSize, String filePath, String name)
    {
        //loads from tmx file
        this.palet = palet;
        this.tileSize = tileSize;
        this.name = name; //Just for differentiating between them and saving.
        tiles = new ArrayList();
        loadTmx(filePath);
    }
    
    private void loadTmx(String path)
    {
        try 
        { 
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int j = 0;      
            int jStart = 0;
            width = 0; 
            int[] tileLayer = null;
            boolean newLayer = false;
            while((line = bufferedReader.readLine()) != null) 
            {
                
                //splits line in individual Strings by tab entries
                String[] words = line.split(" ");
                //remove any empty strings caused by multiple tabs             
                                   
                for(int i = 0; i < words.length; i++)
                {
                    //Set map size
                    if(words[0].startsWith("<map")&&words[i].startsWith("width"))
                    {
                        width = Integer.parseInt(words[i].replaceAll("\\D+",""));
                    }
                    if(words[0].startsWith("<map")&&words[i].startsWith("height"))
                    {
                        height =  Integer.parseInt(words[i].replaceAll("\\D+",""));
                        
                    }
                    //Read: if line starts with number --> create map layer
                    if(!words[0].replaceAll("\\D+","").isEmpty())
                    {                        
                        if(jStart == 0)
                        {
                            jStart = j;
                            tileLayer = new int[width*height];
                            newLayer = true;
                            //System.out.println("jStart set to "+j);
                        }
                            
                        String[] numbers = words[i].split(",");
                        for(int q = 0; q < numbers.length; q++)
                        {
                            //System.out.println("("+width+") < width*height = "+(((j-jStart)*width+q) < width*height));
                            if(j-jStart < height)
                            {
                                tileLayer[(j-jStart)*width+q] = Integer.parseInt(numbers[q]);  
                                //System.out.print(q+","+(j-jStart)+"="+numbers[q]+ "\t"+(j-jStart)+"\t");
                            }                            
                        }                        
                    }
                    else if(newLayer)
                    {
                        jStart = 0;
                        tiles.add(tileLayer);
                        //System.out.println("Layer added");
                        tileLayer = new int[width*height];
                        newLayer = false;
                    }
                }  
                //System.out.println("");
                j++;
            }
        } 
        catch (IOException ex)
        {
            System.out.println("Error reading file "+ path);
        }
    }
    
    public void render(Graphics g, int xOfset, int yOfset, int frameWidth, int frameHeight)
    {
        int xStart = Math.max(0, xOfset / tileSize);
        int yStart = Math.max(0, yOfset / tileSize);;
        int xEnd = Math.min(width, (xOfset+frameWidth) / tileSize+1);
        int yEnd = Math.min(height, (yOfset+frameHeight) / tileSize+1);
        for(int l = 1; l < tiles.size(); l++)
        {
            int[] layer = tiles.get(l);
//            }
            for(int j = yStart; j < yEnd; j++)
            {
                for(int i = xStart; i < xEnd; i++)
                {
                    palet[layer[j*width + i]].render(g, i*tileSize - xOfset, j*tileSize - yOfset, tileSize);
                }
            }
        }
        
    }
    
    public void renderCollisionData(Graphics g, int xOfset, int yOfset, int frameWidth, int frameHeight)
    {
        int xStart = Math.max(0, xOfset / tileSize);
        int yStart = Math.max(0, yOfset / tileSize);;
        int xEnd = Math.min(width, (xOfset+frameWidth) / tileSize+1);
        int yEnd = Math.min(height, (yOfset+frameHeight) / tileSize+1);
        int[] layer = tiles.get(0);
        for(int j = yStart; j < yEnd; j++)
        {
            for(int i = xStart; i < xEnd; i++)
            {
                if(palet[layer[j*width + i]].isCollidable())
                {
                    g.setColor(Color.RED);
                    g.drawRect(i*tileSize - xOfset, j*tileSize - yOfset, tileSize, tileSize);
                }
            }
        }
    }
    
    public int getTileId(int x, int y)
    {
        return tiles.get(0)[y*width + x];
    }
    
    public boolean collisionWithTile(int x, int y)
    {
        if((y*width + x) < tiles.get(0).length)
        {
            if(palet[tiles.get(0)[y*width + x]].isCollidable())
                return true;
        }
        return false;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getTileSize() {
        return tileSize;
    }
}
