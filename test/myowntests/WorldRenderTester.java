/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myowntests;

import customgame.Game;
import customgame.data.DataHandler;
import customgame.states.WorldState;


public class WorldRenderTester 
{
    private final static String PACKAGE_PATH = "C:\\Users\\Samson\\Documents\\NetBeansProjects\\SamsonGames\\test\\samsongamestest\\";
    private static final int FRAME_WIDTH = 1200, FRAME_HEIGHT = 900, TILE_SIZE = 32;
    
    private void worldRenderTest()
    {
        System.out.println("WorldRenderTester");
        Game game = new Game("WORLDRENDERTESTER", FRAME_WIDTH, FRAME_HEIGHT);
        game.getStateMachine().add("world", new WorldState(game.getGui(), "oril city", 
                DataHandler.loadData(PACKAGE_PATH + "tmx\\Oril City.tmx"), PACKAGE_PATH + "images\\", TILE_SIZE));
        game.getStateMachine().change("world");
        game.run();
        System.out.println("");        
    }

    public static void main(String[] args) 
    {
       WorldRenderTester wrc = new WorldRenderTester();
       wrc.worldRenderTest();
    }

}
