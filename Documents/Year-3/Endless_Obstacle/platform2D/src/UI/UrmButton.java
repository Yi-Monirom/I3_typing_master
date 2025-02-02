package UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.loadsave;

public class UrmButton extends PauseButton {
	
	private BufferedImage[] imgs;
	private BufferedImage temp;
	public int SizeImage;
	private int rowIndex, index;
	private boolean mouseOver, mousePressed;
	
	
	
	public UrmButton(int x, int y, int width, int height,int rowIndex) {
		super(x, y, width, height);
		this.rowIndex=rowIndex;
		loadImage();
		
	}
	private void loadImage() {
		
		temp = loadsave.Getsprite(loadsave.URM_BUTTONS);
		SizeImage=temp.getWidth()/3;
		
		imgs = new BufferedImage[3];
		
			for (int i = 0; i < imgs.length; i++)
				imgs[i]=temp.getSubimage(i*SizeImage,rowIndex*SizeImage,SizeImage,SizeImage);
		
		
	}
	public void update() {
		index = 0;
		if (mouseOver)
			index = 1;
		if (mousePressed)
			index = 2;
	}
	public void draw(Graphics g) {
		g.drawImage(imgs[index], x, y, SizeImage, SizeImage, null);
	}
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
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
}
