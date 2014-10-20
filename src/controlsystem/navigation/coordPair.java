package controlsystem.navigation;

public class coordPair {
	
	private int x;
	private int y;
	
	//coordPair default Constructor
	public coordPair() {
		x = 0;
		y = 0;
	}
	
	public coordPair( int inX, int inY ){
		x = inX;
		y = inY;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

}
