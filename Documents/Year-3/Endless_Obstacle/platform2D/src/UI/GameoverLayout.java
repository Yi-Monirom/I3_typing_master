package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Menu.GameState;
import Menu.playing;
import utilz.loadsave;

public class GameoverLayout {
	private playing play;
	private int SizeImage=168/2;
	
	private UrmButton menu, toplay;
	private BufferedImage Game_OverImage;
		
	public GameoverLayout(playing play) {
		
		this.play =play;
		createUrmButton();
		loadGameOverImag();
		
	}
	private void createUrmButton() {
		
		int menuX = (int) (450);
		int playX = (int) (650);
		int y = (int) (200 );
		toplay = new UrmButton(playX, y, SizeImage,SizeImage, 0);
		menu = new UrmButton(menuX, y, SizeImage, SizeImage, 2);
	}
	private void loadGameOverImag() {
		Game_OverImage=loadsave.Getsprite(loadsave.GAME_OVER);
		
	}
	public void update() {
		menu.update();		
		toplay.update();
	}
	public void draw(Graphics g) {
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(0, 0,1200,400);

		g.drawImage(Game_OverImage, 350, 50,Game_OverImage.getWidth()/2,Game_OverImage.getHeight()/2, null);
		menu.draw(g);
		toplay.draw(g);
		
		
	}
	
	public void mousePressed(MouseEvent e) {
		if (isIn(e, menu))
			menu.setMousePressed(true);
		else if (isIn(e, toplay))
			toplay.setMousePressed(true);
		
	}

	public void mouseReleased(MouseEvent e) {
		 if (isIn(e, menu)) {
			if (menu.isMousePressed()) {
				play.resetAll();
				play.setGameState(GameState.MENU);
				
			}
		} else if (isIn(e, toplay)) {
			if (toplay.isMousePressed())
				play.setGameState(GameState.PlAYING);
				play.resetAll();
		}

		
		menu.resetBools();
		toplay.resetBools();
		

	}

	public void mouseMoved(MouseEvent e) {
		
		menu.setMouseOver(false);
		toplay.setMouseOver(false);
		

		if (isIn(e, menu))
			menu.setMouseOver(true);
		else if (isIn(e, toplay))
			toplay.setMouseOver(true);
		

	}

	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			play.resetAll();
			play.setGameState(GameState.MENU);
			
		}
	}
	public void mouseDragged(MouseEvent e) {
			
			
	}
}
