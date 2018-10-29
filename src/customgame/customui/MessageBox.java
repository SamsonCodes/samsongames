/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.customui;

import java.awt.Color;
import java.awt.Graphics;

public class MessageBox extends UIElement
{
    String text;
    boolean timer;
    long displayTime, startTime;
    private Color color;
    
    public MessageBox(int x, int y, int width, int height, Color color)
    {
        super(x, y, width, height);
        this.color = color;
        active = false;
    }
    
    public void setText(String text)
    {
        active = true;
        timer = false;
        this.text = text;
    }
    
    public void setText(String text, long time)
    {
        this.text = text;
        timer = true;
        active = true;
        startTime = System.currentTimeMillis();
        displayTime = time;
    }
    
    @Override
    public void update()
    {
        if(active && timer)
        {
            if(System.currentTimeMillis()-startTime > displayTime)
            {
                timer = false;
                active = false;
            }
        }
        else
        {
            timer = false;
        }
    }
    
    @Override
    public void render(Graphics g)
    {
        if(active)
        {
            g.setColor(color);
            g.fillRect(x, y, width, height);
            
            g.setColor(Color.BLACK);
            g.drawRect(x+5, y+5, width - 10, height - 10);
            g.drawString(text, x+20, y+25);
        }
    }
}
