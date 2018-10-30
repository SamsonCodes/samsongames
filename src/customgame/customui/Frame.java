/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customgame.customui;

import java.awt.Graphics;
import java.util.ArrayList;

public class Frame extends UIElement
{
    private ArrayList<UIElement> elements;   
    private String layout;

    public Frame(int x, int y, int width, int height)
    {
        super(x, y, width, height);
        elements = new ArrayList();
        layout = "flow";
    }
    
    private void arrangeLayout()
    {
        switch(layout)
        {
            case "flow":
                int totalWidth = 0;
                int maxHeight = 0;
                int currentX = 0, currentY = 0;
                for(int i = 0; i < elements.size(); i++)
                {
                    System.out.println("i = " + i);
                    System.out.println("currentX = " + currentX + ", currentY = " + currentY + ", totalWidth = " + totalWidth);
                    totalWidth += elements.get(i).getWidth() + 5;
                    maxHeight = Math.max(elements.get(i).getHeight(), maxHeight);
                    if(totalWidth <= width + 5)
                    {
                        elements.get(i).setX(currentX);
                        elements.get(i).setY(currentY);
                        currentX += elements.get(i).getWidth() + 5;
                    }
                    else
                    {
                        currentX = 0;
                        currentY += maxHeight + 5;
                        maxHeight = 0;
                        totalWidth = 0;
                        elements.get(i).setX(currentX);
                        elements.get(i).setY(currentY);
                        
                    }
                }
                break;
        }
    }

    public void add(UIElement element)
    {
        if (!elements.contains(element))
        {
            elements.add(element);
            arrangeLayout();
        }
        else
        {
            System.out.println("Frame already contains element: " + element.toString());
        }
    }

    @Override
    public void update()
    {
        for(UIElement element: elements)
        {
            element.update();
        }
    }

    @Override
    public void render(Graphics g)
    {
        for(UIElement element: elements)
        {
            element.render(g);
        }
    }

}
