/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.sound;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayerThread extends Player implements Runnable
{

    public PlayerThread(InputStream in) throws JavaLayerException
    {
        super(in);
    }

    @Override
    public void run()
    {
        try
        {
            play();
        }
        catch (JavaLayerException ex)
        {
            Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
