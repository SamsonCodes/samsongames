/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.customui;

import java.awt.Graphics;

public abstract class UIElement 
{
    int x, y, width, height;
    boolean active;
    
    public UIElement(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public abstract void update();
    public abstract void render(Graphics g);
    
    public void setActive(boolean b)
    {
        active = b;
    }
    
    public boolean getActive()
    {
        return active;
    }
}
