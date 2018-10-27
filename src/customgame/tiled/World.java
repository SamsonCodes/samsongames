/*
    This class allows you to create world objects from Tiled tmx files and render them.
 */

package customgame.tiled;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class World 
{
    private Tile[] palet;
    private ArrayList<TileLayer> tileLayers;
    private int width, height, tileRenderSize;
    private String name;
    
    public World(Tile[] palet, int tileRenderSize, ArrayList<String> file, String name)
    {
        //loads from tmx file
        this.palet = palet;
        this.tileRenderSize = tileRenderSize;
        this.name = name; //Just for differentiating between them and saving.
        loadTmx2(file);
    }
    
    private void loadTmx2(ArrayList<String> file)
    {
        String mapElement = XMLReader.getElementPlus("map", file);
        String widthStr = XMLReader.getAttribute("width", mapElement);
        width = Integer.parseInt(widthStr);
        String heightStr = XMLReader.getAttribute("height", mapElement);
        height = Integer.parseInt(heightStr);
        ArrayList<String> layerElements = XMLReader.getElementsPlus("layer", file);
        ArrayList<String> layerTileNumbers = new ArrayList();
        for(int l = 0; l < layerElements.size(); l++)
        {
            layerTileNumbers.add(XMLReader.stripTags("data", XMLReader.getElementPlus("data", layerElements.get(l))));
        }
        tileLayers = new ArrayList();
        for(int l = 0; l < layerElements.size(); l++)
        {
            String[] numberStrings = layerTileNumbers.get(l).split(",");
            int[] tileNumbers = new int[numberStrings.length];
            for(int q = 0; q < numberStrings.length; q++)
            {
                tileNumbers[q] = Integer.parseInt(numberStrings[q]);
            }
            String layerName = XMLReader.getAttribute("name",layerElements.get(l));
            int layerWidth = Integer.parseInt(XMLReader.getAttribute("width", layerElements.get(l)));
            TileLayer tileLayer = new TileLayer(layerName, layerWidth, tileNumbers);      
            tileLayers.add(tileLayer);
        }
    }
    
//    private void loadTmx(String path)
//    {
//        try 
//        { 
//            FileReader fileReader = new FileReader(path);
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            String line;
//            int j = 0;      
//            int jStart = 0;
//            width = 0; 
//            int[] tileLayer = null;
//            boolean newLayer = false;
//            while((line = bufferedReader.readLine()) != null) 
//            {
//                
//                //splits line in individual Strings by tab entries
//                String[] words = line.split(" ");
//                //remove any empty strings caused by multiple tabs             
//                                   
//                for(int i = 0; i < words.length; i++)
//                {
//                    //Set map size
//                    if(words[0].startsWith("<map")&&words[i].startsWith("width"))
//                    {
//                        width = Integer.parseInt(words[i].replaceAll("\\D+",""));
//                    }
//                    if(words[0].startsWith("<map")&&words[i].startsWith("height"))
//                    {
//                        height =  Integer.parseInt(words[i].replaceAll("\\D+",""));
//                        
//                    }
//                    //Read: if line starts with number --> create map layer
//                    if(!words[0].replaceAll("\\D+","").isEmpty())
//                    {                        
//                        if(jStart == 0)
//                        {
//                            jStart = j;
//                            tileLayer = new int[width*height];
//                            newLayer = true;
//                            //System.out.println("jStart set to "+j);
//                        }
//                            
//                        String[] numbers = words[i].split(",");
//                        for(int q = 0; q < numbers.length; q++)
//                        {
//                            //System.out.println("("+width+") < width*height = "+(((j-jStart)*width+q) < width*height));
//                            if(j-jStart < height)
//                            {
//                                tileLayer[(j-jStart)*width+q] = Integer.parseInt(numbers[q]);  
//                                //System.out.print(q+","+(j-jStart)+"="+numbers[q]+ "\t"+(j-jStart)+"\t");
//                            }                            
//                        }                        
//                    }
//                    else if(newLayer)
//                    {
//                        jStart = 0;
//                        tileLayers.add(tileLayer);
//                        //System.out.println("Layer added");
//                        tileLayer = new int[width*height];
//                        newLayer = false;
//                    }
//                }  
//                //System.out.println("");
//                j++;
//            }
//        } 
//        catch (IOException ex)
//        {
//            System.out.println("Error reading file "+ path);
//        }
//    }
    
    public void render(Graphics g, int xOfset, int yOfset, int frameWidth, int frameHeight)
    {
        int xStart = Math.max(0, xOfset / tileRenderSize);
        int yStart = Math.max(0, yOfset / tileRenderSize);;
        int xEnd = Math.min(width, (xOfset+frameWidth) / tileRenderSize+1);
        int yEnd = Math.min(height, (yOfset+frameHeight) / tileRenderSize+1);
        for(int l = 1; l < tileLayers.size(); l++)
        {
            int[] layer = tileLayers.get(l).getTileNumbers();
//            }
            for(int j = yStart; j < yEnd; j++)
            {
                for(int i = xStart; i < xEnd; i++)
                {
                    palet[layer[j*width + i]].render(g, i*tileRenderSize - xOfset, j*tileRenderSize - yOfset, tileRenderSize);
                }
            }
        }
        
    }
    
    public void renderCollisionData(Graphics g, int xOfset, int yOfset, int frameWidth, int frameHeight)
    {
        int xStart = Math.max(0, xOfset / tileRenderSize);
        int yStart = Math.max(0, yOfset / tileRenderSize);;
        int xEnd = Math.min(width, (xOfset+frameWidth) / tileRenderSize+1);
        int yEnd = Math.min(height, (yOfset+frameHeight) / tileRenderSize+1);
        int[] layer = tileLayers.get(0).getTileNumbers();
        for(int j = yStart; j < yEnd; j++)
        {
            for(int i = xStart; i < xEnd; i++)
            {
                if(palet[layer[j*width + i]].isCollidable())
                {
                    g.setColor(Color.RED);
                    g.drawRect(i*tileRenderSize - xOfset, j*tileRenderSize - yOfset, tileRenderSize, tileRenderSize);
                }
            }
        }
    }
    
    public int getTileId(int x, int y)
    {
        return tileLayers.get(0).getTileNumbers()[y*width + x];
    }
    
    public boolean collisionWithTile(int x, int y)
    {
        if((y*width + x) < tileLayers.get(0).getTileNumbers().length)
        {
            if(palet[tileLayers.get(0).getTileNumbers()[y*width + x]].isCollidable())
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
    
    public int getTileRenderSize() {
        return tileRenderSize;
    }
}
