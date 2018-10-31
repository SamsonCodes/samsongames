/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.tiled.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class TileMapEntityManager 
{
    private ArrayList<TileMapEntity> entities, garbageCan;
//    private Comparator<Entity> renderSorter = new Comparator<Entity>() {
//
//        @Override
//        public int compare(Entity a, Entity b) 
//        {
//            if(a.getY() < b.getY())
//                return -1;
//            return 1;
//        }
//    };
    
    public TileMapEntityManager()
    {
        entities = new ArrayList<>();
        garbageCan = new ArrayList<>();
    }
    
    public void update()
    {
        Iterator<TileMapEntity> itr = entities.iterator();
        while(itr.hasNext())
        {
            TileMapEntity e = itr.next();
            if(!e.isActive())
            {
                if(!garbageCan.contains(e))
                    garbageCan.add(e);
                System.out.println("EntityManager: " + e.getName() + " was removed.");
                itr.remove();
            }
            else
                e.update();
        }
        //Collections.sort(entities, renderSorter);
    }
    
    public void render(Graphics g, int xOfset, int yOfset)
    {
        for(TileMapEntity e: entities)
        {
            e.render(g, xOfset, yOfset);
        }
    }
    
    public ArrayList<TileMapEntity> getEntities()
    {
        return entities;
    }
    
    public ArrayList<TileMapEntity> getGarbageCan()
    {
        return garbageCan;
    }
    
    public void addEntity(TileMapEntity e)
    {
        entities.add(e);
    }
    
    public void empty()
    {
        entities.clear();
    }
    
    public String getSaveData()
    {
        String saveData = "<entities>";
        for(TileMapEntity e: entities)
        {
            saveData += e.getSaveData();
        }
        saveData += "</entities>";
        return saveData;
    }
}
