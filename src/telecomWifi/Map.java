package telecomWifi;

public class Map {
	
	private int[][] map;
	private int height;
	private int width;
	
	Map(int height, int width) {
		this.height = height;
		this.width = width;
		this.map = new	int[height][width];
	}
	
	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}
	
	public int getElement(int X, int Y) {
		return map[X][Y];
	}
	
	public double distance(int[] positionInit, int[] positionFinal) {
		double result = Math.sqrt(Math.pow((positionFinal[1]-positionInit[1]),2) + Math.pow((positionFinal[0]-positionInit[0]), 2));
		return result;
	}
	
	public int[] nextStop(double angle, int[] positionInit) {
		//get the next wall that a ray is crossing
		int[] result = new int[2];
		double angCoeff = Math.tan(Math.toRadians(angle));
		int iter = 1;
		int startType = map[positionInit[0]][positionInit[1]];
		int nextX = positionInit[0]++;
		int nextY = positionInit[1] + (int) (angCoeff*iter);
		while (nextX<width && nextY<height && map[nextX][nextY]==startType) {
				nextX++;
				nextY = nextY + (int) (angCoeff*iter);
		}
		result[0] = nextX;
		result[1] = nextY;
		return result;
	}

	
}
