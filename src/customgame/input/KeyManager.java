/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
    All this class does is keep track of which keys are pressed and which keys aren't 
    in the boolean array keys, ordered by key code.
    This allows you to check for whatever key press you need by reading this array
    as long as you import the KeyEvent library or get the key codes some other way.
    Used in the Gui class.
*/
public class KeyManager implements KeyListener
{
    private boolean[] keys;
    
    public KeyManager()
    {
        keys = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent ke){}

    @Override
    public void keyPressed(KeyEvent ke) 
    {
        if(!keys[ke.getKeyCode()])
        {
            keys[ke.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) 
    {
        if(keys[ke.getKeyCode()])
        {
            keys[ke.getKeyCode()]=false;
        }
        
    }
    
    public boolean[] getKeys()
    {
        return keys;
    }
}
