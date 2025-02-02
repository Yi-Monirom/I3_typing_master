package entities;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.geom.Rectangle2D;

abstract class Entity {
	protected float x, y;
	protected int width, height;
	protected Rectangle2D.Float playerHitbox, Animalhitbox, endhitbox;

	private Animal animal;

	public Entity(float x, float y, int width, int hieght) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = hieght;
		inihitbox(x, y, width, hieght);

	}

	protected void drawHitbox(Graphics g) {
		g.setColor(Color.blue);
		g.drawRect((int) playerHitbox.x, (int) playerHitbox.y, (int) playerHitbox.width, (int) playerHitbox.height);
	}

	protected void inihitbox(float x, float y, int width, int height) {

		playerHitbox = new Rectangle2D.Float(x, y, width, height);
	}

	public Rectangle2D.Float gethitbox() {
		return playerHitbox;
	}

	protected void loadAnimal(int AnimalX, int index) {
		animal = new Animal(AnimalX, index);
	}

	public Rectangle2D.Float getAnimalhitbox() {
		return Animalhitbox;
	}

	public Rectangle2D.Float getendhitbox() {
		return endhitbox;
	}

	protected void drawAnimal(int index, int animalX, Graphics g, int change) {
		animal.drawAnimal(index, animalX, g, change);

	}

	protected Rectangle2D.Float AnimalHitbox(int animalX) {
		Animalhitbox = new Rectangle2D.Float(animalX, 240, 60, 60);
		endhitbox = new Rectangle2D.Float(0, 0, 1, 400);
		return Animalhitbox;
	}

}
