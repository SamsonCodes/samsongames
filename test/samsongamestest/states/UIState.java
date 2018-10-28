/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package samsongamestest.states;

import customgame.Gui;
import customgame.customui.CustomButton;
import customgame.customui.InputBox;
import customgame.customui.MessageBox;
import customgame.states.IState;
import java.awt.Color;
import java.awt.Graphics;

public class UIState implements IState
{
    private Gui gui;
    private CustomButton button;
    private MessageBox msgBox;
    private InputBox inpBox;
    private long lastInput;
    
    public UIState(Gui gui) 
    {
        this.gui = gui;
    }
    
    @Override
    public void onEnter()
    {
        System.out.println("Entering UIState");
        button = new CustomButton(gui,0,0,100,50,"Launch Nukes");
        msgBox = new MessageBox(gui, Color.WHITE);
        inpBox = new InputBox(gui, 0, 50, 100, 50);
        lastInput = System.currentTimeMillis();
    }
    
    @Override
    public void onExit()
    {
        System.out.println("Exiting UIState");
    }
    
    @Override
    public void update() 
    {
        if(System.currentTimeMillis() - lastInput > 500)
        {
            button.update();
            if(button.getPressed())
            {
                button.reset();
                msgBox.setText("BOOM!!!", 3000);
                lastInput = System.currentTimeMillis();
            }
        }
        msgBox.update();
    }

    @Override
    public void render(Graphics g) 
    {
        button.render(g);
        msgBox.render(g);
    }

}
