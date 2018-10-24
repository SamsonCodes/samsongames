/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet 
{
    BufferedImage sheet;
    String path;
    
    public SpriteSheet(String path)
    {
        this.path = path;
        try 
        {
            sheet = ImageIO.read(new File(path));
        } 
        catch (IOException ex)
        {
            System.out.println("World: Error loading: " + path);
        }
    }
    
    public BufferedImage all()
    {
        return sheet;
    }
    
    public BufferedImage crop(int x, int y, int width, int height)
    {
        //System.out.println(path+x+", "+y);
        return sheet.getSubimage(x, y, width, height);
    }
}
