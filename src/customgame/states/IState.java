package customgame.states;

/*
    The template for making your own states. Every game state neeeds these methods
    in order to work for the Game class in this library.
 */

import java.awt.Graphics;

public interface IState 
{
    public void onEnter();
    public void onExit();
    public void update();
    public void render(Graphics g);
}
