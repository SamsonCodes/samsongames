/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myowntests;

import customgame.sound.MP3Player;


public class MP3Tester 
{
    private final static String PATH = "C:\\Users\\Samson\\Documents\\NetBeansProjects\\SamsonGames\\test\\samsongamestest\\songs\\";

    public static void main(String[] args) 
    {
       MP3Player mp3 = new MP3Player(PATH + "opening.mp3");
       mp3.start();
       
       System.out.println("Starting Player");
       long startTime = System.currentTimeMillis();
       boolean loop = true;
       boolean playing = true;
       while(loop)
       {
            if(System.currentTimeMillis() - startTime > 10000 && playing)
            {
                System.out.println("Closing Player");
                mp3.stopPlayer();
                playing = false;
            }
            if(System.currentTimeMillis() - startTime > 20000)
            {
                loop = false;
                System.out.println("Closing Loop");
            }
       } 
    }

}
