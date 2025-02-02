package Menu;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import UI.GameoverLayout;
import UI.PauseOverlyout;
import entities.Player;

import platform2D.game;

public class playing extends State implements Statemethod {
	private Player player;
	private PauseOverlyout pauseoverlayout;

	private int Width = 60 * 2, Hieght = 52 * 2;
	private boolean Action;
	private boolean gameOver = false;
	private GameoverLayout Gameover;

	private static boolean paused = false;

	public playing(game gme) {
		super(gme);
		initClasses();
	}

	private void initClasses() {
		int charOption = gme.getOption().getCharOption();
		player = new Player(100, 200, Width, Hieght, this, charOption);
		pauseoverlayout = new PauseOverlyout(this);
		Gameover = new GameoverLayout(this);
	}

	@Override
	public void update() {
		pauseoverlayout.update();
		if (!paused && !gameOver) {

			player.update();

		} else if (paused) {
			pauseoverlayout.update();

		} else if (gameOver) {
			Gameover.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		player.render(g);
		if (paused) {
			pauseoverlayout.draw(g);
		} else if (gameOver) {
			Gameover.draw(g);
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (gameOver)
			Gameover.keyPressed(e);
		else
			switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					Action = true;
					player.setActiving(!Action, 1);

					break;
				case KeyEvent.VK_SPACE:
					Action = true;
					player.setActiving(!Action, 1);

					break;
				case KeyEvent.VK_DOWN:
					Action = true;
					player.setActiving(!Action, 3);
					break;
				case KeyEvent.VK_BACK_SPACE:
					GameState.state = GameState.MENU;
					gme.getAudioPlayer().stop_Animal_sound();
					break;
				case KeyEvent.VK_Q:
					GameState.state = GameState.QUIT;
					break;
				case KeyEvent.VK_ESCAPE:
					paused = !paused;
					gme.getAudioPlayer().stop_Animal_sound();
					break;

			}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (!gameOver)
			switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					Action = true;
					player.setActiving(Action, 1);

				case KeyEvent.VK_DOWN:
					Action = true;
					player.setActiving(Action, 3);
					break;

			}

	}

	public void mouseDragged(MouseEvent e) {
		if (!gameOver)
			if (paused)
				pauseoverlayout.mouseDragged(e);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!gameOver) {
			if (paused)
				pauseoverlayout.mousePressed(e);
		}

		else {
			Gameover.mousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!gameOver) {
			if (paused)
				pauseoverlayout.mouseReleased(e);
		}

		else {
			Gameover.mouseReleased(e);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (!gameOver) {
			if (paused)
				pauseoverlayout.mouseMoved(e);
		}

		else {
			Gameover.mouseMoved(e);
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void unpauseGame() {
		paused = false;

	}

	public void resetAll() {
		gameOver = false;
		paused = false;
		player.resetAll();

	}

	public void Gameover() {
		gameOver = true;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;

	}

}
