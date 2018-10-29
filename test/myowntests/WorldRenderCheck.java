/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myowntests;

import customgame.Game;
import customgame.graphics.Camera;
import samsongamestest.states.WorldState;


public class WorldRenderCheck 
{
    public final static String PACKAGE_PATH = "C:\\Users\\Samson\\Documents\\NetBeansProjects\\SamsonGames\\test\\samsongamestest\\";
    public static final int FRAME_WIDTH = 1200, FRAME_HEIGHT = 900, TILE_SIZE = 50;
    
    private void worldRenderTest()
    {
        System.out.println("WorldRenderTest");
        Game game = new Game("GAMETEST", FRAME_WIDTH, FRAME_HEIGHT);
        game.getStateMachine().add("world", new WorldState(game.getGui()));
        game.getStateMachine().change("world");
        game.run();
        System.out.println("");
        Camera c = new Camera(TILE_SIZE, FRAME_WIDTH, FRAME_WIDTH, FRAME_HEIGHT);
    }

    public static void main(String[] args) 
    {
       WorldRenderCheck wrc = new WorldRenderCheck();
       wrc.worldRenderTest();
    }

}
