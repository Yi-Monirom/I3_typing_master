package platform2D;

import java.awt.*;
import java.awt.Graphics;
import javax.swing.JPanel;

import keyInput.MouseInput;
import keyInput.keybordInput;





public class gamepanel extends JPanel {
   private game gm;
   private MouseInput mouseInputs;
    public gamepanel(game gm) {
    	this.gm=gm;
    	setPanelsize();
    	mouseInputs=new MouseInput(this);
        addKeyListener(new keybordInput(this));
        
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    
        requestFocus();
    }

    
    public void setPanelsize() {
        Dimension size = new Dimension(1200, 400);
        setMinimumSize(size);
        setPreferredSize(size);
     
        
    }
    public void updateGame() {
    	gm.update();
    }
    	
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       
		gm.render(g);

    }
    public game getGame() {
		return gm;
    	
    }


	
		
	
	
}
