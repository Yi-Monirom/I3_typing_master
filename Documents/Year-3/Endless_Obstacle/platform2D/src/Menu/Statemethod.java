package Menu;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Statemethod {
	public void update() ;
	public void draw(Graphics g);
	public void keyPressed(KeyEvent e);
	public void keyReleased(KeyEvent e);
	public void mousePressed(MouseEvent e);
	public void mouseReleased(MouseEvent e);
	public void mouseMoved(MouseEvent e);
	
}
