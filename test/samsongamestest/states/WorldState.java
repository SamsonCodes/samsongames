/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package samsongamestest.states;

import customgame.Gui;
import customgame.graphics.Camera;
import customgame.states.IState;
import customgame.tiled.TileMap;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import myowntests.WordRenderTester;

/**
 * This is a world state that actually has a JavaDoc! Good stuff.
 * @author Samson
 */
public class WorldState implements IState
{
    private final static long CAMERA_COOLDOWN = 15;
    private final static int CAMERA_STEP = 5;
    private TileMap world;
    private Gui gui;
    private Camera camera;
    private long lastInput;
    
    public WorldState(Gui gui)
    {
        this.gui = gui;
        world = new TileMap("orilcity", WordRenderTester.PACKAGE_PATH + "tmx\\Oril City.tmx", WordRenderTester.PACKAGE_PATH + "images\\");
        camera = new Camera(world.getWidth()*WordRenderTester.TILE_SIZE, world.getHeight()*WordRenderTester.TILE_SIZE, WordRenderTester.FRAME_WIDTH, WordRenderTester.FRAME_HEIGHT);
        camera.setCheckBlankSpace(false);
    }
    
    @Override
    public void onEnter() 
    {
        System.out.println("Entering WorldState");
        lastInput = System.currentTimeMillis();
    }

    @Override
    public void onExit() 
    {
        System.out.println("Exiting WorldState");
    }

    @Override
    public void update() 
    {
        if(System.currentTimeMillis() - lastInput > CAMERA_COOLDOWN)
        {
            if(gui.getKeyManager().getKeys()[KeyEvent.VK_LEFT])
            {
                camera.move(-CAMERA_STEP, 0);
                lastInput = System.currentTimeMillis();
            }
            if(gui.getKeyManager().getKeys()[KeyEvent.VK_RIGHT])
            {
                camera.move(CAMERA_STEP, 0);
                lastInput = System.currentTimeMillis();
            }
            if(gui.getKeyManager().getKeys()[KeyEvent.VK_UP])
            {
                camera.move(0, -CAMERA_STEP);
                lastInput = System.currentTimeMillis();
            }
            if(gui.getKeyManager().getKeys()[KeyEvent.VK_DOWN])
            {
                camera.move(0, CAMERA_STEP);
                lastInput = System.currentTimeMillis();
            }
        }
        
    }

    @Override
    public void render(Graphics g) 
    {
        world.render(g, camera.getxOfset(), camera.getyOfset(), WordRenderTester.FRAME_WIDTH, WordRenderTester.FRAME_HEIGHT, WordRenderTester.TILE_SIZE);
    }

    @Override
    public final String toString() {
        return "WorldState{" + "world=" + world + ", gui=" + gui + ", camera=" + camera + ", lastInput=" + lastInput + '}';
    }
    
    
}
