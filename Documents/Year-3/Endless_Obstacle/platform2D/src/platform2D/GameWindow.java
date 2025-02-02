package platform2D;



import javax.swing.*;

public class GameWindow {
	private JFrame window;
	public GameWindow(gamepanel gamepanel) {
		window =new JFrame("Endless Obstacle");
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(gamepanel);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		
		window.setVisible(true);
		
	}
	
}
