package keyInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Menu.GameState;
import platform2D.gamepanel;

public class keybordInput implements KeyListener {
	private gamepanel gpanel;
	boolean Action = true;

	public keybordInput(gamepanel gpanel) {
		this.gpanel = gpanel;
		this.gpanel.setFocusable(true);
		this.gpanel.requestFocusInWindow();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (GameState.state) {
			case MENU:
				gpanel.getGame().getMenu().keyPressed(e);
				break;
			case PlAYING:
				gpanel.getGame().getplaying().keyPressed(e);
				break;
			case OPTION:
				gpanel.getGame().getOption().keyPressed(e);
			default:
				break;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		switch (GameState.state) {
			case MENU:
				gpanel.getGame().getMenu().keyReleased(e);
				break;

			case PlAYING:
				gpanel.getGame().getplaying().keyReleased(e);
				break;

			default:
				break;

		}
	}

}
