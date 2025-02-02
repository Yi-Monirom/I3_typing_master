package utilz;

public class Constants {
	
	public static class UI{
			
		public static class Button{
			public static final int B_Width=140;
			public static final int B_Hieght=56;
			
			
		}
	}
	
	
	public static class playerConstants{
		public static final int RUNNING_1=0;
		public static final int JUMPING=1;
		public static final int DASHING=2;
		
		
		
		public static int getNSprite(int player_action){
			
			switch(player_action) {
			case RUNNING_1:
				return 6;
			case JUMPING:
				return 4;
			case DASHING :
				return 2;
			}
			return player_action;
		}
		
	}
}
