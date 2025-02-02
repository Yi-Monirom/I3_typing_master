package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import utilz.loadsave;

public class Animal {
    protected BufferedImage snake;
    protected BufferedImage[][] snakeAnimation, wolfAnimation;
    protected Rectangle2D.Float animalhitbox;
    private BufferedImage wolf;

    public Animal(int animalX, int index) {
        loadSnake(animalX);
        loadWolf(animalX);
        // Populate the array

    }

    protected void loadAnimalGIF() {

    }

    protected void loadSnake(int animalX) {
        snake = loadsave.Getsprite(loadsave.SNAKE);
        int row = 2;
        int col = 11;
        int frameWidth = snake.getWidth() / col;
        int frameHeight = snake.getHeight() / row;

        snakeAnimation = new BufferedImage[row][col];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                snakeAnimation[r][c] = snake.getSubimage(c * frameWidth, r * frameHeight, frameWidth, frameHeight);
            }
        }

    }

    protected void loadWolf(int animalX) {
        wolf = loadsave.Getsprite(loadsave.WOLF);
        int row = 2;
        int col = 10;
        int frameWidth = wolf.getWidth() / col;
        int frameHeight = wolf.getHeight() / row;

        wolfAnimation = new BufferedImage[row][col];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                wolfAnimation[r][c] = wolf.getSubimage(c * frameWidth, r * frameHeight, frameWidth, frameHeight);
            }
        }
    }

    public void drawAnimal(int index, int animalX, Graphics g, int change) {

        int snakeX = animalX - 40;
        if (change == 1) {

            g.drawImage(snakeAnimation[0][index], snakeX, 240, snake.getWidth() / 11 * 2, snake.getHeight(), null);

        } else if (change == 0) {
            g.drawImage(snakeAnimation[1][index], snakeX, 240, snake.getWidth() / 11 * 2, snake.getHeight(), null);
        }

        else if (change == 3) {
            // g.drawImage(wolfAnimation[1][index], snakeX, 240, snake.getWidth() / 10*2,
            // snake.getHeight(), null);
            g.drawImage(wolfAnimation[0][index], snakeX, 240, snake.getWidth() / 10 * 2, snake.getHeight(), null);

        } else if (change == 2) {
            g.drawImage(wolfAnimation[1][index], snakeX, 240, snake.getWidth() / 10 * 2, snake.getHeight(), null);
        }

    }

}
