/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package customgame.customui;

import java.awt.Graphics;

public abstract class UIElement
{

    /*
    TO DO: add width and height to constructor and make getters for dimensions, 
    then add Frame UIElement Class for better UI alignment options
    Also: Think about better modularization of UI elements
     */
    int x, y, width, height;
    boolean active;

    public UIElement(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public void setActive(boolean b)
    {
        active = b;
    }

    public boolean isActive()
    {
        return active;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

}
