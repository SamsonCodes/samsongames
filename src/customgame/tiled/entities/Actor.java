/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customgame.tiled.entities;

public abstract class Actor extends Entity
{

    public final static int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4;
    private int moveX, moveY, moveCommand;
    private int pixelsPerFrame;
    private int worldWidth, worldHeight;
    private EntityManager entityManager;
    private final int TILE_SIZE;
    private boolean traveling;

    public Actor(String name, int x, int y, int width, int height, int pixelsPerFrame, int tileSize)
    {
        super(name, x * tileSize, y * tileSize, width * tileSize, height * tileSize);
        this.pixelsPerFrame = pixelsPerFrame;        
        this.TILE_SIZE = tileSize;
        moveX = 0;
        moveY = 0;
        moveCommand = 0;
        traveling = false;
    }
    
    public void setEntityMananger(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
    
    public void setWorldDimensions(int worldWidth, int worldHeight)
    {
        this.worldWidth = worldWidth*TILE_SIZE;
        this.worldHeight = worldHeight*TILE_SIZE;
    }

    public void move()
    {
        moveX = 0;
        moveY = 0;
        switch (moveCommand)
        {
            case UP:
                moveY = -pixelsPerFrame;
                break;
            case DOWN:
                moveY = pixelsPerFrame;
                break;
            case LEFT:
                moveX = -pixelsPerFrame;
                break;
            case RIGHT:
                moveX = pixelsPerFrame;
                break;
        }
        if (!checkEntityCollisions(entityManager, moveX, moveY))
        {
            x += moveX;
            if (x < 0)
            {
                x = 0;
            }
            if (x > worldWidth)
            {
                x = worldWidth;
            }
            y += moveY;
            if (y < 0)
            {
                y = 0;
            }
            if (y > worldHeight)
            {
                y = worldHeight;
            }
        }
        else
        {
            while (checkEntityCollisions(entityManager, moveX, moveY) & !(moveX == 0 && moveY == 0))
            {
                if (moveX > 0)
                {
                    moveX--;
                }
                else if (moveX < 0)
                {
                    moveX++;
                }
                if (moveY > 0)
                {
                    moveY--;
                }
                else if (moveY < 0)
                {
                    moveY++;
                }
            }
            x += moveX;
            if (x < 0)
            {
                x = 0;
            }
            if (x > worldWidth)
            {
                x = worldWidth;
            }
            y += moveY;
            if (y < 0)
            {
                y = 0;
            }
            if (y > worldWidth)
            {
                y = worldWidth;
            }
        }
        if(!onTile() &! traveling)
            traveling = true;
        else if(onTile())
            traveling = false;
    }
    
    public void setMoveCommand(int i)
    {
        if(onTile())
            moveCommand = i;
    }
    
    private boolean onTile()
    {
        return (x % TILE_SIZE == 0 && y % TILE_SIZE == 0);
    }
}
