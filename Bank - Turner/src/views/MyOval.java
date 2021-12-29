package views;

public class MyOval {

	private int x,y,diameter,xMouse,yMouse, turn;
	
	public MyOval(int x, int y, int diameter, int turn) {
		this.x = x;
		this.y = y;
		this.turn = turn;
		this.diameter = diameter;
	}

	public boolean ovalFx() { 
		boolean bool = false;
		float r = diameter/4;
		float a = x + r;
		float b = y + r;
		float y = -1*(float)Math.sqrt(r*r - Math.pow((xMouse - a), 2)) + b;
		float y1 = (float)Math.sqrt(r*r - Math.pow((xMouse - a), 2)) + b;
		if(xMouse >= x && xMouse <= x+diameter/2 && yMouse <= y1
				&& yMouse >= y) {			
			bool = true;
		}
		return bool;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDiameter() {
		return diameter;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	public int getxMouse() {
		return xMouse;
	}

	public int getyMouse() {
		return yMouse;
	}

	public void setxMouse(int xMouse) {
		this.xMouse = xMouse;
	}

	public void setyMouse(int yMouse) {
		this.yMouse = yMouse;
	}

	public int getTurn() {
		return turn;
	}

}
