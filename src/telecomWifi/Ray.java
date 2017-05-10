package telecomWifi;

public class Ray {
	
	private int nbrReflection;
	private int nbrDiffraction;
	private double power;
	private int[] direction;
	private int[] currentPosition;

	Ray(int nbrReflection, int nbrDiffraction, double power, int directionX, int directionY, int currentPosX, int currentPosY) {
			this.nbrReflection = nbrReflection;
			this.nbrDiffraction = nbrDiffraction;
			this.power = power;
			this.direction = new int[2];
			this.direction[0] = directionX;
			this.direction[1] = directionY;
			this.currentPosition = new int[2];
			this.currentPosition[0] = currentPosX;
			this.currentPosition[1] = currentPosY;
	}

	Ray(double power, int directionX, int directionY, int currentPosX, int currentPosY) {
		this.nbrReflection = 0; 
		this.nbrDiffraction = 0;
		this.power = power;
		this.direction = new int[2];
		this.direction[0] = directionX;
		this.direction[1] = directionY;
		this.currentPosition = new int[2];
		this.currentPosition[0] = currentPosX;
		this.currentPosition[1] = currentPosY;
	}
	
	Ray() {
		this.nbrReflection = 0; 
		this.nbrDiffraction = 0;
		this.power = 0;
		this.direction = new int[2];
		this.direction[0] = 0;
		this.direction[1] = 0;
		this.currentPosition = new int[2];
		this.currentPosition[0] = 0;
		this.currentPosition[1] = 0;
	}
	
	
	
	public int getNbrReflection() {
		return nbrReflection;
	}

	public void setNbrReflection(int nbrReflection) {
		this.nbrReflection = nbrReflection;
	}

	public int getNbrDiffraction() {
		return nbrDiffraction;
	}

	public void setNbrDiffraction(int nbrDiffraction) {
		this.nbrDiffraction = nbrDiffraction;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public int[] getDirection() {
		return direction;
	}

	public void setDirection(int[] direction) {
		this.direction = direction;
	}

	public int[] getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int[] currentPosition) {
		this.currentPosition = currentPosition;
	}

	private void changeCurrentPos() {
		if ((currentPosition[0]+direction[0])>0 && (currentPosition[1]+direction[1])>0) {
				currentPosition[0] = currentPosition[0]+direction[0];
				currentPosition[1] = currentPosition[1]+direction[1];
		}
	}
	
	private void changePower() {
		
	}
	
	private void changeDirection() {
		
	}
}
