package telecomWifi;

public class Map {
	
	private int[][] map;
	
	Map(int high, int width) {
		this.map = new	int[high][width];
	}
	
	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}

	
}
