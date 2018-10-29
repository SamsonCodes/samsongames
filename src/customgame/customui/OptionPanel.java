/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.customui;

import customgame.Gui;
import java.awt.Graphics;

public class OptionPanel extends UIElement
{
    private CustomButton[] buttons;
    private int options;
    private int[] choice;
    private int currentIndex;
    private Gui gui;
    
    public OptionPanel(Gui gui, String[] options, int x, int y, int bWidth, int bHeight, int panelWidth, int choiceAmount)
    {
        super(x, y, bWidth * panelWidth, bHeight * (options.length / panelWidth));
        this.gui = gui;
        this.options = options.length;
        buttons = new CustomButton[options.length];
        for(int i = 0; i < options.length; i++)
        {
            buttons[i] = new CustomButton(gui, x+(i%panelWidth)*bWidth, y+(i/panelWidth)*bHeight, bWidth, bHeight, options[i]);
        }
        choice = new int[choiceAmount];
        for(int i = 0; i < choice.length; i++)
        {
            choice[i] = -1;
        }
        currentIndex = 0;
    }
    
    public void reset()
    {
        for(CustomButton b: buttons)
        {
            b.reset();
        }
        for(int i = 0; i < choice.length; i++)
        {
            choice[i] = -1;
        }        
        currentIndex = 0;
    }
    
    public int[] getChoices()
    {
        return choice;
    }
    
    public int getChoice(int i)
    {
        return choice[i];
    }

    @Override
    public void update() 
    {
        for(CustomButton b: buttons)
            b.update();
        for(int i = 0; i < buttons.length; i++)
        {
            if(buttons[i].getPressed() && choice[currentIndex] == -1 && i < options)
            {
                choice[currentIndex] = i;
                buttons[i].setSelected(true);
                currentIndex++;
                if(currentIndex == choice.length)
                {
                    currentIndex = 0;
                    //System.out.println("OptionPanel: last input received.");
                }
//                buttons[choice[currentIndex]].setSelected(false);
//                choice[currentIndex] = -1;
            }
        }
    }

    @Override
    public void render(Graphics g) 
    {
        for (CustomButton button : buttons) {
            button.render(g);
        }
    }
    
    public boolean allSelected()
    {
        boolean allSelected = true;
        for(int c: choice)
        {
            if(c == -1)
                allSelected = false;
        }
        return allSelected;
    }
    
}
