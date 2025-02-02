package entities;

import static utilz.Constants.playerConstants.*;

import java.awt.Graphics;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import Menu.playing;
import audio.AudioPlayer;

import utilz.loadsave;

public class Player extends Entity {
	private float x = 100, y = 100;

	private BufferedImage img;
	private BufferedImage[][] runAnimation;
	private int anitick, aniIndex, aniSpeed = 15, animalIndex;
	private int PlayerAction = RUNNING_1;
	public boolean Running = false, Dash = false;
	public int groundX, animalX = 900, animalX2 = 1000;
	private int rand;
	private BufferedImage ground;

	private playing play_prossesing;
	private BufferedImage[] digitImages;
	private int change;

	private float gravity = 0.9f;
	private float jumpSpeed = -20f;
	private float yVelocity = 0f;
	private float groundLevel = 250;

	private boolean hasScore = true, getfile = true;
	private int Score, topScore;
	private int scoreTimer = 0;
	private int scoreIncrementDelay = 30;

	private BufferedImage Numbers, ScoreImage, topScoreImage;
	private int seleted;

	private Rectangle2D.Float animalHitbox1, animalHitbox2;

	private int[] milestones = { 100, 250, 500, 1000, 2500, 10000 }; // Define score milestones
	private Set<Integer> printedMilestones = new HashSet<>(); // Track printed milestones

	public Player(float x, float y, int width, int height, playing play, int selected) {
		super(x, y, width, height);
		this.play_prossesing = play;
		this.seleted = selected;
		loadGround();
		Animal();
		loadAnimation();
		loadDigitImages();
		inihitbox(x, y, width / 2, height);

	}

	public void Animal() {
		animalHitbox1 = AnimalHitbox(animalX);
		// animalHitbox2 = AnimalHitbox(animalX2);
		loadAnimal(animalX, aniIndex);
		// loadAnimal(animalX2, aniIndex);

	}

	public void update() {

		if (checkCollision(playerHitbox, animalHitbox1)) {
			play_prossesing.setGameOver(true);
			play_prossesing.getGame().getAudioPlayer().plaEffects(seleted, AudioPlayer.DIE);
			play_prossesing.getGame().getAudioPlayer().stopSong();
			play_prossesing.getGame().getAudioPlayer().stop_Animal_sound();
			getfile = true;
			writeTopScoreToFile("platform2D\\rsc\\score.txt");
			hasScore = false;

			return;
		}
		if (Score > topScore) {
			getfile = false;
			writeTopScoreToFile("platform2D\\rsc\\topscore.txt");
		}

		updateAnimation();
		groundupdate();
		updateHitbox();

		if (checkCollision(Animalhitbox, endhitbox)) {
			change = (int) (Math.random() * 4);
			play_prossesing.getGame().getAudioPlayer().plaEffects(seleted, AudioPlayer.MISSED_ME);
			play_prossesing.getGame().getAudioPlayer().play_Animal_sound(change);

		}

		if (hasScore) {
			scoreTimer++;
			if (scoreTimer >= scoreIncrementDelay) {
				Score++;
				// System.out.println("this:"+Score);
				scoreTimer = 0; // Reset the timer

				for (int milestone : milestones) {
					if (Score == milestone && !printedMilestones.contains(milestone)) {
						play_prossesing.getGame().getAudioPlayer().plaEffects(seleted, AudioPlayer.I_wont_lose);
						printedMilestones.add(milestone); // Mark milestone as printed
					}
				}
			}

		}

	}

	protected void updateHitbox() {
		playerHitbox.x = x + 30;
		playerHitbox.y = y;
	}

	private void groundupdate() {
		rand = (int) (1400 + (Math.random() * (1900 - 1400 + 1)));
		int k = 4;
		for (int mil : milestones) {
			if (Score >= mil) {
				k++;
			}
		}

		animalX -= k;
		groundX -= k - 1;
		if (groundX < -ground.getWidth()) {
			groundX = 0;
		}

		if (animalX < 0) {
			animalX = 1200;

		}
		// animalX2 -= k;
		// if (animalX2 < 0) {
		// if (rand > 1500) {
		// animalX2 = (int) rand;
		// }
		// }
		// animalHitbox2.x = animalX2;

		animalHitbox1.x = animalX;

	}

	private void loadGround() {
		ground = loadsave.Getsprite(loadsave.Background);

	}

	private void drawBackGround(Graphics g) {
		g.drawImage(ground, groundX, 10, ground.getWidth(), ground.getHeight(), null);
		g.drawImage(ground, groundX + ground.getWidth(), 10, ground.getWidth(), ground.getHeight(), null);
		// g.drawImage(ground, groundX + ground.getWidth() * 2, 10, ground.getWidth(),
		// ground.getHeight(), null);

	}

	private void loadAnimation() {
		img = loadsave.Getsprite(loadsave.PLAYER_ATLAS[seleted]);
		int totalRows = 3; // Total number of rows in the sprite sheet
		int totalCols = 6; // Total number of columns in the sprite sheet
		int frameWidth = img.getWidth() / totalCols; // Calculate width of each frame
		int frameHeight = img.getHeight() / totalRows; // Calculate height of each frame

		runAnimation = new BufferedImage[totalRows][totalCols];

		for (int row = 0; row < totalRows; row++) {
			for (int col = 0; col < totalCols; col++) {
				runAnimation[row][col] = img.getSubimage(col * frameWidth, row * frameHeight, frameWidth, frameHeight);
			}
		}

	}

	private void loadDigitImages() {
		Numbers = loadsave.Getsprite(loadsave.NUMBER); // Load the sprite sheet
		ScoreImage = loadsave.Getsprite(loadsave.SCORE);
		topScoreImage = loadsave.Getsprite(loadsave.TOP_SCORE);

		int digitWidth = Numbers.getWidth() / 10; // Width of each digit
		int digitHeight = Numbers.getHeight(); // Height of each digit

		digitImages = new BufferedImage[10];
		for (int i = 0; i < 10; i++) {
			digitImages[i] = Numbers.getSubimage(i * digitWidth, 0, digitWidth, digitHeight);
		}
	}

	private void drawScore(Graphics g, int score, int x, int y) {
		g.drawImage(ScoreImage, 850, 60, ScoreImage.getWidth() / 8, ScoreImage.getHeight() / 8, null);
		g.drawImage(topScoreImage, 850, 15, ScoreImage.getWidth() / 6, ScoreImage.getHeight() / 6, null);

		String scoreStr = String.valueOf(score); // Convert score to string
		int digitWidth = digitImages[0].getWidth(); // Width of a single digit
		int digitHeight = digitImages[0].getHeight();
		for (int i = 0; i < scoreStr.length(); i++) {
			int digit = Character.getNumericValue(scoreStr.charAt(i)); // Get the digit
			g.drawImage(digitImages[digit], x + (i * digitWidth / 3), y, digitWidth / 3, digitHeight / 3, null); // Draw
																													// each
																													// digit
		}
	}

	public boolean checkCollision(Rectangle2D.Float playerHitbox, Rectangle2D.Float snakehitbox2) {
		return playerHitbox.x < snakehitbox2.x + snakehitbox2.width &&
				playerHitbox.x + playerHitbox.width > snakehitbox2.x &&
				playerHitbox.y < snakehitbox2.y + snakehitbox2.height &&
				playerHitbox.y + playerHitbox.height > snakehitbox2.y;
	}

	public void render(Graphics g) {

		applyGravity();

		// g.drawRect(animalX, (int) Animalhitbox.y, (int) Animalhitbox.width, (int)
		// Animalhitbox.height);
		drawBackGround(g);

		drawAnimal(animalIndex, animalX, g, change);
		g.drawImage(runAnimation[PlayerAction][aniIndex], (int) x, (int) y, width, height, null);
		// drawAnimal(aniIndex, animalX2, g, change);

		drawScore(g, Score, 1000, 65);
		displayTopScore(g, 940, 20);

	}

	public void setActiving(boolean action, int row) {

		this.Running = action;
		this.Dash = action;
		if (action && y >= groundLevel) {
			PlayerAction = RUNNING_1;

		} else if (!action && y >= groundLevel && row == 1) {
			yVelocity = jumpSpeed;
			play_prossesing.getGame().getAudioPlayer().plaEffects(seleted, AudioPlayer.JUMP);

			PlayerAction = JUMPING;
			playerHitbox.y += jumpSpeed;
		} else if (!action && y >= groundLevel && row == 3) {
			PlayerAction = DASHING;
		}
	}

	private void applyGravity() {

		if (y < groundLevel || yVelocity < 0) {
			yVelocity += gravity;
			y += yVelocity;
			playerHitbox.y = y;

			if (y > groundLevel) {
				y = groundLevel;
				yVelocity = 0;
				PlayerAction = RUNNING_1;

				playerHitbox.y = groundLevel;

			}
		}

	}

	private void updateAnimation() {
		seleted = play_prossesing.getGame().getOption().getCharOption();
		anitick++;
		if (anitick >= aniSpeed) {
			anitick = 0;
			aniIndex++;
			if (aniIndex >= getNSprite(PlayerAction)) { // Loop through the animation frames
				aniIndex = 0;
			}
			animalIndex++;
			if (animalIndex >= 10) {
				animalIndex = 0;
			}
		}

	}

	public void resetAll() {

		scoreTimer = 0;
		Score = 0;
		hasScore = true;
		animalX = 1200;
		animalHitbox1.x = animalX;
		PlayerAction = RUNNING_1;
		playerHitbox.x = y;
		playerHitbox.y = y;

	}

	public void updateCharOption(int newCharOption) {
		this.seleted = newCharOption;
		reloadAnimation(); // Reload animations with the new Char_option value
	}

	private void reloadAnimation() {
		loadAnimation(); // Re-load the animation based on the current Char_option
		// System.out.println("Animations reloaded for Char_option: " + seleted); //
		// Debugging
	}

	public void writeTopScoreToFile(String filename) {
		try (FileWriter fw = new FileWriter(filename, getfile);
				PrintWriter pw = new PrintWriter(fw)) {
			pw.println(Score);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int readTopScoreFromFile(String filename) {
		int topScore = 0;
		File file = new File(filename);

		if (!file.exists()) {
			return topScore; // Return 0 if the file doesn't exist
		}

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextInt()) {
				int score = scanner.nextInt();
				if (score > topScore) {
					topScore = score;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return topScore;
	}

	public void displayTopScore(Graphics g, int x, int y) {
		topScore = readTopScoreFromFile("platform2D\\rsc\\topscore.txt"); // Replace with your filename
		String topScoreText = "Top Score: " + topScore;

		// Draw the top score on the screen
		// drawString(topScoreText, x, y);
		drawScore(g, topScore, x + g.getFontMetrics().stringWidth(topScoreText), y);
		// System.out.println(topScore);
	}

}