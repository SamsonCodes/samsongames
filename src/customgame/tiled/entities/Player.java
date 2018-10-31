/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.tiled.entities;

import customgame.input.KeyManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Player extends Actor
{
    private KeyManager keyManager;
    
    public Player(KeyManager keyManager, String name, int x, int y, int width, int height, int pixelsPerFrame, int tileSize)
    {
        super(name, x, y, width, height, pixelsPerFrame, tileSize);
        this.keyManager = keyManager;
    }

    @Override
    public void update()
    {
        if(keyManager.getKeys()[KeyEvent.VK_UP])
        {
            setMoveCommand(UP);
        }
        else if(keyManager.getKeys()[KeyEvent.VK_DOWN])
        {
            setMoveCommand(DOWN);
        }
        else if(keyManager.getKeys()[KeyEvent.VK_LEFT])
        {
            setMoveCommand(LEFT);
        }
        else if(keyManager.getKeys()[KeyEvent.VK_RIGHT])
        {
            setMoveCommand(RIGHT);
        }
        else
        {
            setMoveCommand(0);
        }
        move();
    }

    @Override
    public void render(Graphics g, int xOfset, int yOfset)
    {
        g.setColor(Color.RED);
        g.fillRect(x - xOfset, y - yOfset, width, height);
    }

    @Override
    public String getSaveData()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
