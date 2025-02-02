package UI;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Menu.GameState;
import utilz.loadsave;

public class MenuButton {

	private int x, y, rowIndex, index;
	private GameState state;
	private BufferedImage[] imgs;
	private int frameWidth, frameHeight;

	private Rectangle bounds;
	private boolean mouseOver;
	private boolean mousePressed;

	public MenuButton(int x, int y, int rowIndex, GameState state) {
		this.x = x;
		this.y = y;
		this.rowIndex = rowIndex;
		this.state = state;
		loadIMg();
		iniBouns();

	}

	private void iniBouns() {
		bounds = new Rectangle(x - frameWidth / 2, y, frameHeight * 2, frameHeight);

	}

	private void loadIMg() {
		imgs = new BufferedImage[3];
		BufferedImage temp = loadsave.Getsprite(loadsave.MENU_BUTTON);
		int row = 3;
		int col = 3;
		frameWidth = temp.getWidth() / col;
		frameHeight = temp.getHeight() / row;

		imgs = new BufferedImage[row];
		for (int c = 0; c < col; c++) {
			imgs[c] = temp.getSubimage(c * frameWidth, rowIndex * frameHeight, frameWidth, frameHeight);
		}

	}

	public void draw(Graphics g) {

		g.drawImage(imgs[index], x - frameWidth / 2, y, frameWidth, frameHeight, null);

	}

	public void update() {
		index = 0;
		if (mouseOver)
			index = 1;
		if (mousePressed)
			index = 2;

	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void applyGamestate() {
		GameState.state = state;
	}

	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}

	public GameState getGameState() {
		return state;
	}

}
