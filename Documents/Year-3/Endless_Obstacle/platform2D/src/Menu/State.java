package Menu;

import java.awt.event.MouseEvent;

import UI.MenuButton;
import audio.AudioPlayer;
import platform2D.game;
import platform2D.gamepanel;

public class State {
	protected game gme;
	protected gamepanel Gamepanel;

	public State(game gme) {
		this.gme = gme;
	}

	public boolean isIn(MouseEvent e, MenuButton mb) {
		return mb.getBounds().contains(e.getX(), e.getY());
	}

	public game getGame() {
		return gme;
	}

	public void mousePressed(MouseEvent e) {
		switch (GameState.state) {
			case MENU:
				Gamepanel.getGame().getMenu().mouseMoved(e);
				break;
			case PlAYING:
				Gamepanel.getGame().getplaying().mouseMoved(e);
				break;
			default:
				break;

		}

	}

	public void mouseClicked(MouseEvent e) {
		switch (GameState.state) {
			case MENU:
				Gamepanel.getGame().getMenu().mouseClicked(e);
				break;
			case PlAYING:
				// Gamepanel.getGame().getplaying().mouseClicked(e);
				break;
			case OPTION:
				break;

			default:
				break;

		}
	}

	public void mouseReleased(MouseEvent e) {
		switch (GameState.state) {
			case MENU:
				Gamepanel.getGame().getMenu().mouseReleased(e);
				break;
			case PlAYING:
				Gamepanel.getGame().getplaying().mouseReleased(e);
				break;
			default:
				break;

		}

	}

	public void setGameState(GameState state) {
		switch (state) {
			case MENU -> gme.getAudioPlayer().playSong(AudioPlayer.MENU);
			case PlAYING -> gme.getAudioPlayer().playSong(AudioPlayer.PLAYING);
			case OPTION -> gme.getAudioPlayer().playSong(AudioPlayer.PLAYING);

		}
		GameState.state = state;

	}
}
