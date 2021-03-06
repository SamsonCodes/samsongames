/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myowntests;

import customgame.Game;
import samsongamestest.states.UIState;


public class UITester 
{
    private void uiTest()
    {
        System.out.println("UITest");
        Game game = new Game("UI Test", 1200, 900);
        game.getStateMachine().add("ui", new UIState(game.getGui()));
        game.getStateMachine().change("ui");
        game.run();
        System.out.println("");
    }
    

    public static void main(String[] args) 
    {
        UITester uic = new UITester();
        uic.uiTest();
    }

}
