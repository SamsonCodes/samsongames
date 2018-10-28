/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.graphics;

import customgame.entities.Entity;

public class Camera 
{
    private int xOfset, yOfset;
    private int worldWidth, worldHeight, frameWidth, frameHeight;
    private boolean checkBlankSpace;
    
    public Camera(int worldWidth, int worldHeight, int frameWidth, int frameHeight)
    {
        this.xOfset = 0;
        this.yOfset = 0;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        checkBlankSpace = false;
    }
    
    //This keeps the camera from moving out of the world, can be toggled with checkBlankSpace boolean
    private void checkBlankSpace()
    {
        if(xOfset < 0)
        {
            xOfset = 0;
        }
        else if(xOfset > worldWidth - frameWidth)
        {
            xOfset = worldWidth - frameWidth;
        }
        if(yOfset < 0)
        {
            yOfset = 0;
        }
        else if(yOfset > worldHeight - frameHeight)
        {
            yOfset = worldHeight - frameHeight;
        }
    }
    
    public void centerOnEntity(Entity e, int frameWidth, int frameHeight)
    {
        xOfset = e.getX() + e.getWidth()/2 - frameWidth/2;
        yOfset = e.getY() + e.getHeight()/2 - frameHeight/2;
        if(isCheckBlankSpace())
            checkBlankSpace();
    }
    
    public void move(int xAmount, int yAmount)
    {
        xOfset += xAmount;
        yOfset += yAmount;
        if(isCheckBlankSpace())
            checkBlankSpace();
    }

    public int getxOfset() {
        return xOfset;
    }

    public void setXOfset(int xOfset) {
        this.xOfset = xOfset;
    }

    public int getyOfset() {
        return yOfset;
    }

    public void setYOfset(int yOfset) {
        this.yOfset = yOfset;
    }
    
    public int getWorldWidth() {
        return worldWidth;
    }

    public void setWorldWidth(int worldWidth) {
        this.worldWidth = worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public void setWorldHeight(int worldHeight) {
        this.worldHeight = worldHeight;
    }

    public boolean isCheckBlankSpace() {
        return checkBlankSpace;
    }
    
    public void setCheckBlankSpace(boolean checkBlankSpace) {
        this.checkBlankSpace = checkBlankSpace;
    }
}
