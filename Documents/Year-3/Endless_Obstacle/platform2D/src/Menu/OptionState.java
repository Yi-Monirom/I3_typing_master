package Menu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import java.awt.event.MouseEvent;

import platform2D.game;
import utilz.loadsave;

public class OptionState extends State implements Statemethod {
    public int Char_option = 0;
    private Image Naruto, Sakura, Sasuke, BG, left_Arrow, Right_Arrow, N_Naruto, N_Sakura, N_Sasuke;
    private Rectangle leftArrowBounds, rightArrowBounds;

    public OptionState(game gme) {
        super(gme);
        LoadImage();
        initializeBounds();

    }

    private void initializeBounds() {
        int arrow_size = left_Arrow.getWidth(null) / 7;
        leftArrowBounds = new Rectangle(400, 200, arrow_size, arrow_size);
        rightArrowBounds = new Rectangle(700, 200, arrow_size, arrow_size);
    }

    public void LoadImage() {
        Naruto = loadsave.getGIF("platform2D\\rsc\\Naruto-GIF.gif").getImage();
        Sasuke = loadsave.getGIF("platform2D\\rsc\\Sasuke-GIF.gif").getImage();
        Sakura = loadsave.getGIF("platform2D\\rsc\\Sakura-GIF.gif").getImage();
        BG = loadsave.getGIF("platform2D\\rsc\\BackGround_Office.png").getImage();
        left_Arrow = loadsave.getGIF("platform2D\\rsc\\Left.png").getImage();
        Right_Arrow = loadsave.getGIF("platform2D\\rsc\\Right.png").getImage();

        N_Naruto = loadsave.getGIF("platform2D\\rsc\\Naruto-Uzumaki.png").getImage();
        N_Sasuke = loadsave.getGIF("platform2D\\rsc\\sasuke-Uchiha.png").getImage();
        N_Sakura = loadsave.getGIF("platform2D\\rsc\\sakura-Haruno.png").getImage();

    }

    public void update() {

    }

    @Override
    public void draw(Graphics g) {

        // g.drawString(String.valueOf(Char_option), 600, 200);
        int arrow_size = left_Arrow.getWidth(null) / 7;
        g.drawImage(BG, -100, 0, 736 * 2, 400, null);
        g.drawImage(left_Arrow, 400, 200, arrow_size, arrow_size, null);
        g.drawImage(Right_Arrow, 700, 200, arrow_size, arrow_size, null);
        if (Naruto != null) {
            int width = Naruto.getWidth(null) * 2;
            int height = Naruto.getHeight(null) * 2;
            if (Char_option == 1) {

                g.drawImage(Naruto, 500, 120, width, height, null);
                g.drawImage(N_Naruto, 455, 50, 256, 68, null);
            } else if (Char_option == 2) {
                g.drawImage(Sasuke, 500, 100, width, height, null);
                g.drawImage(N_Sasuke, 455, 50, 256, 68, null);

            } else {
                g.drawImage(Sakura, 500, 100, width, height, null);
                g.drawImage(N_Sakura, 455, 50, 256, 68, null);

            }

        } else {
            System.out.println("Image not loaded");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            Char_option += 1;
            if (Char_option > 2) {
                Char_option = 0;

            }
            gme.getAudioPlayer().stopEffects();
            gme.getplaying().getPlayer().updateCharOption(Char_option);

        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Char_option -= 1;
            if (Char_option < 0) {
                Char_option = 2;

            }
            gme.getAudioPlayer().stopEffects();
            gme.getplaying().getPlayer().updateCharOption(Char_option);
        }

        gme.getAudioPlayer().stopEffects();
        gme.getAudioPlayer().plaEffects(Char_option, 1);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            gme.getplaying().getPlayer().updateCharOption(Char_option);
            gme.getAudioPlayer().stopEffects();

            setGameState(GameState.MENU);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (leftArrowBounds.contains(e.getPoint())) {
            Char_option += 1;
            if (Char_option > 2) {
                Char_option = 0;
            }
            gme.getAudioPlayer().stopEffects();
            gme.getplaying().getPlayer().updateCharOption(Char_option);
            gme.getAudioPlayer().plaEffects(Char_option, 1);
        }
        if (rightArrowBounds.contains(e.getPoint())) {
            Char_option -= 1;
            if (Char_option < 0) {
                Char_option = 2;
            }
            gme.getAudioPlayer().stopEffects();
            gme.getplaying().getPlayer().updateCharOption(Char_option);
            gme.getAudioPlayer().plaEffects(Char_option, 1);
        }

    }

    public void mouseReleased(MouseEvent e) {

    }

    public int getCharOption() {
        return Char_option;
    }

}
