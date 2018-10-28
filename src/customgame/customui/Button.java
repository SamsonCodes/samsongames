/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.customui;

import customgame.Gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Button extends UIElement
{
    private String buttonText;
    private boolean pressed, hover, selected;
    private Font buttonFont;
    private Color color, textColor, hoverColor, selectColor;
    
    public Button(Gui gui, int x, int y, int width, int height, String buttonText)
    {
        super(gui);        
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        if(buttonText != null)
            this.buttonText = buttonText;
        else
            this.buttonText = "";
        this.buttonFont = new Font("TimesRoman", Font.PLAIN, Math.max(height/2, 1));
        this.color = Color.WHITE;
        this.hoverColor = Color.YELLOW;
        this.selectColor = Color.BLUE;
        this.textColor = Color.BLACK;
        active = true;
    }
    
    public boolean getPressed()
    {
        return pressed;
    }

    @Override
    public void update() 
    {
        if(!selected)
        {
            int pointerX = gui.getMouseManager().getX();
            int pointerY = gui.getMouseManager().getY();
            int range = 1;
            Rectangle pointer = new Rectangle(pointerX-range/2, pointerY-range/2, range, range);
            Rectangle buttonBounds = new Rectangle(x,y,width,height);
            if(pointer.intersects(buttonBounds) &! buttonText.equals(""))
            {
                hover = true;
            }
            else
            {
                hover = false;
            }
            if(gui.getMouseManager().isLeftClick() && hover)
            {
                pressed = true;
            }
            else
                pressed = false;
        }
    }
    
    public void setSelected(boolean b)
    {
        selected = b;
        pressed = false;
        hover = false;
    }

    @Override
    public void render(Graphics g) 
    {
        if(active)
        {
            if(selected)
                g.setColor(selectColor);
            else if(hover)
                g.setColor(hoverColor);
            else
                g.setColor(color);
            g.fillRect(x, y, width, height);
            //g.drawImage(Assets.button, x, y, width, height, null);
            g.setColor(textColor);
            g.setFont(buttonFont);
            g.drawString(buttonText, x+10, y+height/2);
            g.drawRect(x, y, width, height);
        }
    }
}
