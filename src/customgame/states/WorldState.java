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
import customgame.tiled.entities.NPC;
import customgame.tiled.entities.Player;
import java.awt.Graphics;

/**
 * This is a basic world state that you can use in your games.
 * @author Samson
 */
public class WorldState implements IState
{
    private TileMap map;
    private Camera camera;
    private Player player;
    private EntityManager entityManager;
    private final int FRAME_WIDTH, FRAME_HEIGHT, TILE_SIZE;
    
    public WorldState(Gui gui, String mapName, String mapData, String tilesetPath, int tileSize)
    {
        FRAME_WIDTH = gui.getWidth();
        FRAME_HEIGHT = gui.getHeight();
        TILE_SIZE = tileSize;
        map = new TileMap(mapName, mapData, tilesetPath);
        camera = new Camera(map.getWidth()*tileSize, map.getHeight()*tileSize, 
                gui.getWidth(), gui.getHeight());
        camera.setCheckBlankSpace(true);
        player = new Player(gui.getKeyManager(), "player", 0, 0, 
                1, 1, 4, tileSize);  
        NPC npc = new NPC("player", 2, 2, 
                1, 1, 4, tileSize);  
        entityManager = new EntityManager();
        entityManager.add(player);
        entityManager.add(npc);
        player.setEntityMananger(entityManager);
        player.setWorldDimensions(map.getWidth(), map.getWidth());
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
        entityManager.update();
        camera.centerOnEntity(player);
    }

    @Override
    public void render(Graphics g) 
    {
        map.render(g, camera.getxOfset(), camera.getyOfset(), FRAME_WIDTH, 
                FRAME_HEIGHT, TILE_SIZE);
        entityManager.render(g, camera.getxOfset(), camera.getyOfset());
    }
    
    public EntityManager getEntityManager()
    {
        return entityManager;
    }
    
    public void setMap(TileMap map)
    {
        this.map = map;
    }
}
