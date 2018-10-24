/*
    This class creates a tile object which contains a collidable property as wel as a texture image. 
    It is used in the World class for creating tile based worlds from Tiled tmx files.
 */

package customgame.tiled;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile 
{
    private boolean collidable;
    private BufferedImage texture;
    
    public Tile(BufferedImage texture, boolean collidable)
    {
        this.texture = texture;
        this.collidable = collidable;   
    }
    
    public void update()
    {
        
    }
    
    public void render(Graphics g, int x, int y, int size)
    {
        g.drawImage(texture, x, y, size, size, null);
    }
    
    public boolean isCollidable()
    {
        return collidable;
    }
    
    public void setCollidable(boolean collidable)
    {
        this.collidable = collidable;
    }
}
