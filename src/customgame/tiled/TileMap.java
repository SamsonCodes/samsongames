/*
    This class allows you to create TileMap objects from Tiled tmx files and render them.
 */
package customgame.tiled;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class TileMap
{

    private Tile[] palet;
    private ArrayList<TileLayer> tileLayers;
    private int width, height;
    private String name;

    public TileMap(String name, ArrayList<String> file, String tileSetPath)
    {
        this.name = name;
        loadMap(file, tileSetPath);
    }

    public TileMap(String name, String tmxPath, String tileSetPath)
    {
        this.name = name;
        loadMap(loadTmx(tmxPath), tileSetPath);
    }

    private ArrayList<String> loadTmx(String tmxPath)
    {
        ArrayList<String> mapData = new ArrayList();
        try
        {
            FileReader fileReader = new FileReader(tmxPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                mapData.add(line);
            }
        }
        catch (Exception ex)
        {
            System.out.println("TileMap: loadTmx: Error loading " + tmxPath);
        }
        return mapData;
    }

    private void loadMap(ArrayList<String> file, String tileSetPath)
    {
        String mapElement = XMLReader.getElementPlus("map", file);
        String widthStr = XMLReader.getAttribute("width", mapElement);
        width = Integer.parseInt(widthStr);
        String heightStr = XMLReader.getAttribute("height", mapElement);
        height = Integer.parseInt(heightStr);
        ArrayList<String> layerElements = XMLReader.getElementsPlus("layer", file);
        ArrayList<String> layerTileNumbers = new ArrayList();
        for (int l = 0; l < layerElements.size(); l++)
        {
            layerTileNumbers.add(XMLReader.stripTags("data", XMLReader.getElementPlus("data", layerElements.get(l))));
        }
        tileLayers = new ArrayList();
        for (int l = 0; l < layerElements.size(); l++)
        {
            String[] numberStrings = layerTileNumbers.get(l).split(",");
            int[] tileNumbers = new int[numberStrings.length];
            for (int q = 0; q < numberStrings.length; q++)
            {
                tileNumbers[q] = Integer.parseInt(numberStrings[q]);
            }
            String layerName = XMLReader.getAttribute("name", layerElements.get(l));
            int layerWidth = Integer.parseInt(XMLReader.getAttribute("width", layerElements.get(l)));
            TileLayer tileLayer = new TileLayer(layerName, layerWidth, tileNumbers);
            tileLayers.add(tileLayer);
        }
        ArrayList<String> tileSetElements = XMLReader.getElementsPlus("tileset", file);
        ArrayList<TileSet> tileSets = new ArrayList();
        for (int i = 0; i < tileSetElements.size(); i++)
        {
            tileSets.add(new TileSet(tileSetElements.get(i), tileSetPath));
        }
        int tileCount = 0;
        for (TileSet tileSet : tileSets)
        {
            tileCount += tileSet.getTileCount();
        }
        palet = new Tile[tileCount + 1];
        palet[0] = new Tile(null, false);
        for (TileSet tileSet : tileSets)
        {
            for (int i = 0; i < tileSet.getTileCount(); i++)
            {
                palet[i + tileSet.getFirstGid()] = tileSet.getTiles()[i];
            }
        }
    }

    public void render(Graphics g, int xOfset, int yOfset, int frameWidth, int frameHeight, int tileRenderSize)
    {
        int xStart = Math.max(0, xOfset / tileRenderSize);
        int yStart = Math.max(0, yOfset / tileRenderSize);;
        int xEnd = Math.min(width, (xOfset + frameWidth) / tileRenderSize + 1);
        int yEnd = Math.min(height, (yOfset + frameHeight) / tileRenderSize + 1);
        for (int l = 1; l < tileLayers.size(); l++)
        {
            int[] layer = tileLayers.get(l).getTileNumbers();

            for (int j = yStart; j < yEnd; j++)
            {
                for (int i = xStart; i < xEnd; i++)
                {
                    if (palet[layer[j * width + i]] != null)
                    {
                        palet[layer[j * width + i]].render(g, i * tileRenderSize - xOfset, j * tileRenderSize - yOfset, tileRenderSize);
                    }
                }
            }
        }

    }

    public void renderCollisionData(Graphics g, int xOfset, int yOfset, int frameWidth, int frameHeight, int tileRenderSize)
    {
        int xStart = Math.max(0, xOfset / tileRenderSize);
        int yStart = Math.max(0, yOfset / tileRenderSize);;
        int xEnd = Math.min(width, (xOfset + frameWidth) / tileRenderSize + 1);
        int yEnd = Math.min(height, (yOfset + frameHeight) / tileRenderSize + 1);
        int[] layer = tileLayers.get(0).getTileNumbers();
        for (int j = yStart; j < yEnd; j++)
        {
            for (int i = xStart; i < xEnd; i++)
            {
                if (palet[layer[j * width + i]].isCollidable())
                {
                    g.setColor(Color.RED);
                    g.drawRect(i * tileRenderSize - xOfset, j * tileRenderSize - yOfset, tileRenderSize, tileRenderSize);
                }
            }
        }
    }

    public int getTileId(int x, int y)
    {
        return tileLayers.get(0).getTileNumbers()[y * width + x];
    }

    public boolean collisionWithTile(int x, int y)
    {
        if ((y * width + x) < tileLayers.get(0).getTileNumbers().length)
        {
            if (palet[tileLayers.get(0).getTileNumbers()[y * width + x]].isCollidable())
            {
                return true;
            }
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

    public Tile[] getPalet()
    {
        return palet;
    }

}
