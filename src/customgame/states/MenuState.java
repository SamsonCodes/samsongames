/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.states;

import customgame.Gui;
import customgame.customui.OptionPanel;
import java.awt.Graphics;

/**
 * This is a very basic MenuState that you can use in your games.
 * @author Samson
 */
public class MenuState implements IState
{
    private final String[] options = {"Play","Exit"};
    private OptionPanel optionPanel;
    private StateMachine stateMachine;
    private String gameState;
    
    public MenuState(Gui gui, StateMachine stateMachine, String gameState)
    {
        optionPanel = new OptionPanel(gui, options, 0, 0, 100, 50, 1, 1);
        this.stateMachine = stateMachine;
        this.gameState = gameState;
    }

    @Override
    public void onEnter()
    {
        System.out.println("Entering MenuState");
    }

    @Override
    public void onExit()
    {
        System.out.println("Exiting MenuState");
    }

    @Override
    public void update()
    {
        optionPanel.update();
        if(optionPanel.getChoice(0) != -1)
        {
            switch(optionPanel.getChoice(0))
            {
                case 0:
                    stateMachine.change(gameState);
                    break;
                case 1:
                    System.exit(0);
                    break;
            }
        }
    }

    @Override
    public void render(Graphics g)
    {
        optionPanel.render(g);
    }

}
