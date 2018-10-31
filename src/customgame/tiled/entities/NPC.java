/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.tiled.entities;

import java.awt.Color;
import java.awt.Graphics;

public class NPC extends Actor
{

    public NPC(String name, int x, int y, int width, int height, int pixelsPerFrame, int tileSize)
    {
        super(name, x, y, width, height, pixelsPerFrame, tileSize);
    }

    @Override
    public void update()
    {
        
    }

    @Override
    public void render(Graphics g, int xOfset, int yOfset)
    {
        g.setColor(Color.BLUE);
        g.fillRect(x - xOfset, y - yOfset, width, height);
    }

    @Override
    public String getSaveData()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
