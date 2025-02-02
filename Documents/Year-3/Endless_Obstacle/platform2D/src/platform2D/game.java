package platform2D;

import java.awt.Graphics;

import Menu.GameState;
import Menu.MenuState;
import Menu.OptionState;
import Menu.playing;
import audio.AudioPlayer;

public class game implements Runnable {
	private gamepanel gpanel;
	private final int FPS_SET = 60;
	private final int UPS_SET = 150;
	private Thread gamethread;
	private AudioPlayer audioplayer;

	private playing play;
	private MenuState menu;
	private OptionState option;

	public game() {
		initClasses();
		gpanel = new gamepanel(this);
		new GameWindow(gpanel);
		gpanel.requestFocus();

		startgame();

	}

	private void initClasses() {

		option = new OptionState(this);
		menu = new MenuState(this);
		play = new playing(this);

		audioplayer = new AudioPlayer();

	}

	void startgame() {
		gamethread = new Thread(this);
		gamethread.start();

	}

	public void update() {
		switch (GameState.state) {
			case OPTION:
				option.update();
				break;
			case MENU:
				menu.update();
				break;
			case PlAYING:
				play.update();
				break;
			case QUIT:
				System.exit(0);
			default:
				break;

		}

	}

	public void render(Graphics g) {
		// play.render(g);
		switch (GameState.state) {
			case OPTION:
				option.draw(g);
				break;
			case MENU:
				menu.draw(g);
				break;
			case PlAYING:
				play.draw(g);

				break;
			default:
				break;

		}

	}

	@Override
	public void run() {
		double timepf = 1000000000.0 / FPS_SET;
		double timepU = 1000000000.0 / UPS_SET;

		long previousTime = System.nanoTime();

		int frame = 0;
		int update = 0;
		double deltaU = 0;
		double deltaF = 0;

		long lackcheck = System.currentTimeMillis();
		while (true) {

			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timepU;
			deltaF += (currentTime - previousTime) / timepf;
			previousTime = currentTime;

			if (deltaU >= 1) {
				update();
				update++;
				deltaU--;
			}

			if (deltaF >= 1) {
				gpanel.repaint();
				frame++;
				deltaF--;
			}

			if (System.currentTimeMillis() - lackcheck >= 1000) {
				lackcheck = System.currentTimeMillis();
				System.out.println("Fps:" + frame + "|UPS:" + update);
				frame = 0;
				update = 0;
			}
		}
	}

	public playing getplaying() {
		return play;
	}

	public MenuState getMenu() {
		return menu;

	}

	public AudioPlayer getAudioPlayer() {
		return audioplayer;
	}

	public OptionState getOption() {
		return option;
	}

}
