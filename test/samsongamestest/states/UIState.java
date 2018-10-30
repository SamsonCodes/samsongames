/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package samsongamestest.states;

import customgame.Gui;
import customgame.customui.CustomButton;
import customgame.customui.CustomLabel;
import customgame.customui.Frame;
import customgame.customui.InputBox;
import customgame.customui.MessageBox;
import customgame.customui.OptionPanel;
import customgame.states.IState;
import java.awt.Color;
import java.awt.Graphics;

public class UIState implements IState 
{
    private static final long INPUT_COOLDOWN = 500;
    private Gui gui;
    private CustomButton button;
    private MessageBox msgBox;
    private OptionPanel optionPanel, optionPanel2,optionPanel3;
    private CustomLabel label;
    private long lastInput;
    private InputBox inputBox;
    private Frame frame;
    
    public UIState(Gui gui)
    {
        this.gui = gui;
    }

    @Override
    public void onEnter() 
    {
        System.out.println("Entering UIState");
        lastInput = System.currentTimeMillis();
        int bX = 1;
        int bY = 1;
        int bWidth = 100;
        int bHeight = 50;
        button = new CustomButton(gui, bX, bY, bWidth, bHeight, "Press Me");
        msgBox = new MessageBox(0, 700, 1200, 200, Color.WHITE);
        optionPanel = new OptionPanel(gui, new String[]{"Option 1", "Option 2", "Option 3"}, bX, bY + bHeight*2, bWidth, bHeight, 1, 1);
        optionPanel2 = new OptionPanel(gui, new String[]{"Option 1", "Option 2", "Option 3"}, bX + bWidth + 1, bY + bHeight*2, bWidth, bHeight, 1, 2);
        optionPanel3 = new OptionPanel(gui, new String[]{"Option 1", "Option 2", "Option 3",
            "Option 4", "Option 5", "Option 6"}, bX + bWidth*3 + 1, bY + bHeight*2, bWidth, bHeight, 2, 3);
        label = new CustomLabel(bWidth*2 + bX, bY, bWidth, bHeight, "Label");
        inputBox = new InputBox(gui, bX + bWidth*3 + 1, bY, bWidth, bHeight);
        
        frame = new Frame(0, 0, 205, 100);
        frame.add(button);
        frame.add(label);
        frame.add(inputBox);
        frame.add(optionPanel);
        frame.add(optionPanel2);
        frame.add(optionPanel3);
        frame.add(msgBox);
    }

    @Override
    public void onExit() 
    {
        System.out.println("Exiting UIState");
    }

    @Override
    public void update() 
    {
        msgBox.update();
        if(System.currentTimeMillis() - lastInput > INPUT_COOLDOWN)
        {
            //Button
            button.update();
            if(button.getPressed())
            {
                msgBox.setText("Button press detected", 1000);
                lastInput = System.currentTimeMillis();
            }
            //Panel1
            optionPanel.update();
            if(optionPanel.getChoice(0) != -1)
            {
                switch(optionPanel.getChoice(0))
                {
                    case 0:
                        msgBox.setText("Panel1 Option 1 selected", 2000);
                        break;
                    case 1:
                        msgBox.setText("Panel1 Option 2 selected", 2000);
                        break;
                    case 2:
                        msgBox.setText("Panel1 Option 3 selected", 2000);
                        break;                
                }
                optionPanel.reset();
                lastInput = System.currentTimeMillis();
            }
            //Panel2
            optionPanel2.update();
            if(optionPanel2.allSelected())
            {
                int amount = 0;
                String selection = "";
                for(int i = 0; i < optionPanel2.getChoices().length; i++)
                {
                    if(optionPanel2.getChoice(i) != -1)
                    {
                        switch(optionPanel2.getChoice(i))
                        {
                            case 0:
                                selection += "1";
                                if(amount == 0)
                                {
                                    amount++;
                                    selection += " and ";
                                }
                                break;
                            case 1:
                                selection += "2";
                                if(amount == 0)
                                {
                                    amount++;
                                    selection += " and ";
                                }
                                break;
                            case 2:
                                selection += "3";
                                if(amount == 0)
                                {
                                    amount++;
                                    selection += " and ";
                                }
                                break;
                        }
                    }
                }
                msgBox.setText("Panel2 Option " + selection + " selected", 2000);
                optionPanel2.reset();
                lastInput = System.currentTimeMillis();
            }
            //Panel3
            optionPanel3.update();
            if(optionPanel3.allSelected())
            {
                int amount = 0;
                String selection = "";
                for(int i = 0; i < optionPanel3.getChoices().length; i++)
                {
                    if(optionPanel3.getChoice(i) != -1)
                    {
                        switch(optionPanel3.getChoice(i))
                        {
                            case 0:
                                selection += "1";
                                if(amount != 3)
                                {
                                    amount++;
                                    selection += " and ";
                                }
                                break;
                            case 1:
                                selection += "2";
                                if(amount != 3)
                                {
                                    amount++;
                                    selection += " and ";
                                }
                                break;
                            case 2:
                                selection += "3";
                                if(amount != 3)
                                {
                                    amount++;
                                    selection += " and ";
                                }
                                break;
                             case 3:
                                selection += "4";
                                if(amount != 3)
                                {
                                    amount++;
                                    selection += " and ";
                                }
                                break;
                             case 4:
                                selection += "5";
                                if(amount != 3)
                                {
                                    amount++;
                                    selection += " and ";
                                }
                                break;
                             case 5:
                                selection += "6";
                                if(amount != 3)
                                {
                                    amount++;
                                    selection += " and ";
                                }
                                break;
                            
                        }
                    }
                }
                msgBox.setText("Panel3 Option " + selection + " selected", 2000);
                optionPanel3.reset();
                lastInput = System.currentTimeMillis();
            }
            //InputBox
            inputBox.update();
            if(inputBox.isSent())
            {
                msgBox.setText("Input = " + inputBox.getInput(), 2000);
                inputBox.reset();
                lastInput = System.currentTimeMillis();
            }
        }
        
    }

    @Override
    public void render(Graphics g) 
    {
        frame.render(g);
//        button.render(g);
//        optionPanel.render(g);
//        optionPanel2.render(g);
//        optionPanel3.render(g);
//        inputBox.render(g);
//        msgBox.render(g);
//        label.render(g);
    }

}
