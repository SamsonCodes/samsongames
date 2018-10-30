/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MP3Player extends Thread
{
    Player player;
    BufferedInputStream bis;
    private String fileName;
    private boolean startedPlayer;

    public MP3Player(String fileName)
    {
        this.fileName = fileName;
        try
        {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            try
            {
                player = new Player(bis);
            }
            catch(JavaLayerException jle)
            {
                System.out.println("MP3Player: jlayer exception");
                System.err.println(jle.getMessage());
            }
        }
        catch(IOException e)
        {
            System.out.println("MP3Player: ioexception");
            System.err.println(e.getMessage());
        }
        startedPlayer = false;
    }
    
    public void stopPlayer(){
        if(player!=null)
        {
            player.close();
        }
    }
    
    @Override
    public void run()
    {
        try 
        {
            player.play();
        }
        catch (JavaLayerException ex)
        {
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
}
