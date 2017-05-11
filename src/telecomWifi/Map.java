package telecomWifi;

public class Map {
	
	private int[][] map;
	
	Map(int height, int width) {
		this.map = new	int[height][width];
	}
	
	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}

	
}
