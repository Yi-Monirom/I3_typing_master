package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Menu.GameState;
import Menu.playing;

import utilz.loadsave;

public class PauseOverlyout {
	private UrmButton menuB;
	public static UrmButton replayB;
	private UrmButton unpauseB;
	int SizeImage = 168 / 3;
	int index;
	private playing play;
	private BufferedImage PauseImage;

	public PauseOverlyout(playing play) {
		this.play = play;
		createUrmButton();
		loadPauseImag();
	}

	private void createUrmButton() {

		int menuX = 460;
		int replayX = 560;
		int unpuaseX = 650;
		int Ally = 300 - SizeImage;

		menuB = new UrmButton(menuX, Ally, SizeImage, SizeImage, 2);
		replayB = new UrmButton(replayX, Ally, SizeImage, SizeImage, 1);
		unpauseB = new UrmButton(unpuaseX, Ally, SizeImage, SizeImage, 0);
	}

	private void loadPauseImag() {
		PauseImage = loadsave.Getsprite(loadsave.PAUSE_IMAGE);

	}

	public void update() {
		menuB.update();
		replayB.update();
		unpauseB.update();
	}

	public void draw(Graphics g) {
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(0, 0, 1200, 400);
		menuB.draw(g);
		replayB.draw(g);
		unpauseB.draw(g);

		g.drawImage(PauseImage, 350, 50, PauseImage.getWidth() / 2, PauseImage.getHeight() / 2, null);
	}

	public void mousePressed(MouseEvent e) {
		if (isIn(e, menuB))
			menuB.setMousePressed(true);
		else if (isIn(e, replayB))
			replayB.setMousePressed(true);
		else if (isIn(e, unpauseB))
			unpauseB.setMousePressed(true);

	}

	public void mouseReleased(MouseEvent e) {
		if (isIn(e, menuB)) {
			if (menuB.isMousePressed()) {
				play.resetAll();
				play.getGame().getAudioPlayer().stopSong();
				play.setGameState(GameState.MENU);
				play.unpauseGame();
			}
		} else if (isIn(e, replayB)) {
			if (replayB.isMousePressed())

				play.resetAll();
			play.setGameState(GameState.PlAYING);
			play.unpauseGame();

		} else if (isIn(e, unpauseB)) {
			if (unpauseB.isMousePressed())
				play.unpauseGame();
		}

		menuB.resetBools();
		replayB.resetBools();
		unpauseB.resetBools();

	}

	public void mouseMoved(MouseEvent e) {

		menuB.setMouseOver(false);
		replayB.setMouseOver(false);
		unpauseB.setMouseOver(false);

		if (isIn(e, menuB))
			menuB.setMouseOver(true);
		else if (isIn(e, replayB))
			replayB.setMouseOver(true);
		else if (isIn(e, unpauseB))
			unpauseB.setMouseOver(true);

	}

	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

	public void mouseDragged(MouseEvent e) {

	}
}
