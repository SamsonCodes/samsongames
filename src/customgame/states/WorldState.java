/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.states;

import customgame.Gui;
import customgame.graphics.Camera;
import customgame.tiled.TileMap;
import customgame.tiled.entities.EntityManager;
import customgame.tiled.entities.Player;
import java.awt.Graphics;

/**
 * This is a world state that actually has a JavaDoc! Good stuff.
 * @author Samson
 */
public class WorldState implements IState
{
    private TileMap world;
    private Camera camera;
    private Player player;
    private EntityManager eManager;
    private final int FRAME_WIDTH, FRAME_HEIGHT, TILE_SIZE;
    
    public WorldState(Gui gui, String mapName, String mapData, String tilesetPath, int tileSize)
    {
        FRAME_WIDTH = gui.getWidth();
        FRAME_HEIGHT = gui.getHeight();
        TILE_SIZE = tileSize;
        world = new TileMap(mapName, mapData, tilesetPath);
        camera = new Camera(world.getWidth()*tileSize, world.getHeight()*tileSize, 
                gui.getWidth(), gui.getHeight());
        camera.setCheckBlankSpace(true);
        player = new Player(gui.getKeyManager(), "player", 0, 0, 
                1, 1, 4, tileSize);        
        eManager = new EntityManager();
        eManager.addEntity(player);
        player.setEntityMananger(eManager);
        player.setWorldDimensions(world.getWidth(), world.getWidth());
    }
    
    @Override
    public void onEnter() 
    {
        System.out.println("Entering WorldState");
    }

    @Override
    public void onExit() 
    {
        System.out.println("Exiting WorldState");
    }

    @Override
    public void update() 
    {
        eManager.update();
        camera.centerOnEntity(player);
    }

    @Override
    public void render(Graphics g) 
    {
        world.render(g, camera.getxOfset(), camera.getyOfset(), FRAME_WIDTH, 
                FRAME_HEIGHT, TILE_SIZE);
        eManager.render(g, camera.getxOfset(), camera.getyOfset());
    }
}
