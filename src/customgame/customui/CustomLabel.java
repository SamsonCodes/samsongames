/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.customui;

import java.awt.Color;
import java.awt.Graphics;

public class CustomLabel extends UIElement
{
    private String text;
    
    public CustomLabel(int x, int y, int width, int height, String text) 
    {
        super(x, y);
        this.width = width;
        this.height = height;
        this.text = text;
    }

    @Override
    public void update() 
    {
        
    }

    @Override
    public void render(Graphics g) 
    {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawString(text, x + 10, y + height/2);
    }

}
