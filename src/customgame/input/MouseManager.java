/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/*
    This class keeps track of where the mousepointer is and whether the buttons
    are currently being pressed or not. Used in the Gui class.
*/

public class MouseManager implements MouseListener, MouseMotionListener
{
    private int x;
    private int y;
    private boolean leftClick, midClick, rightClick;
    
    @Override
    public void mouseClicked(MouseEvent me){}

    @Override
    public void mousePressed(MouseEvent me) 
    {
        //System.out.println("pressed");
        if(me.getButton() == MouseEvent.BUTTON1)
            leftClick = true;
        if(me.getButton() == MouseEvent.BUTTON2)
            midClick = true;
        if(me.getButton() == MouseEvent.BUTTON1)
            rightClick = true;
    }

    @Override
    public void mouseReleased(MouseEvent me) 
    {
        //System.out.println("released");
        if(me.getButton() == MouseEvent.BUTTON1)
            leftClick = false;
        if(me.getButton() == MouseEvent.BUTTON2)
            midClick = false;
        if(me.getButton() == MouseEvent.BUTTON1)
            rightClick = false;
    }

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me){}

    @Override
    public void mouseDragged(MouseEvent me) {}

    @Override
    public void mouseMoved(MouseEvent me) 
    {
        //System.out.println("moved");
        x = me.getX();
        y = me.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isLeftClick() {
        return leftClick;
    }

    public boolean isMidClick() {
        return midClick;
    }

    public boolean isRightClick() {
        return rightClick;
    }
}
