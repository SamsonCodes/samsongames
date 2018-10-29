package customgame.tiled.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class TileMapEntity
{

    private String name;
    protected int x, y, width, height;
    protected Rectangle bounds;
    private boolean active;

    public TileMapEntity(String name, int x, int y, int width, int height)
    {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle(0, 0, this.width, this.height);
        active = true;
    }

    public abstract void update();

    public abstract void render(Graphics g, int xOfset, int yOfset);

    public boolean checkEntityCollisions(TileMapEntityManager entityManager, int xOfset, int yOfset)
    {
        for (TileMapEntity e : entityManager.getEntities())
        {
            if (e.equals(this))
            {
                continue;
            }
            if (e.getBounds(0, 0).intersects(getBounds(xOfset, yOfset)))
            {
                return true;
            }
        }
        return false;
    }

    public void drawBounds(Graphics g, TileMapEntityManager entityManager, int xOfset, int yOfset)
    {
        if (!checkEntityCollisions(entityManager, 0, 0))
        {
            g.setColor(Color.GREEN);
        }
        else
        {
            g.setColor(Color.RED);
        }

        g.drawRect(x - xOfset, y - yOfset, bounds.width, bounds.height);
    }

    public Rectangle getBounds(int xOfset, int yOfset)
    {
        Rectangle r = new Rectangle(x + bounds.x + xOfset, y + bounds.y + yOfset,
                bounds.width, bounds.height);
        return r;
    }

    public String getName()
    {
        return name;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public boolean isActive()
    {
        return active;
    }

}
