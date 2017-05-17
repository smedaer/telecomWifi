package telecomWifi;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Main {
	
	private static int numMaxReflexion;
	private static int numMaxRefraction;
	private static int numRays;
	private static int height;
	private static int width;
	private static Wall[][] parsedMap;
	private static int[] emettorPosition;
	private static int[][] mapRays;
	private static int[][] mapInit;
	private static int powerInit;
	private static ImageToArray imageArray;
	private static ArrayList<Wall> walls = new ArrayList<Wall>();
	private static ArrayList<Integer> wallsInfos = new ArrayList<Integer>();
	private static int[][]mapWalls;
	public static void main(String[] args) {
		
		emettorPosition = new int[2];
		emettorPosition[0] = 6;
		emettorPosition[1] = 6;
		numMaxReflexion = 3;
		numMaxRefraction = 2;
		numRays = 20;
		powerInit=15;
		
		
		BufferedImage Image = null;
		
		try {
			Image = ImageIO.read(ImageToArray.class.getResource("testImageBON4PNG.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		imageArray = new ImageToArray();

		mapInit = imageArray.getMatrix(Image);
		
		Map map = new Map(imageArray.getHeight(),imageArray.getWidth(),mapInit);

		parsedMap parsedMap = new parsedMap(imageArray.getHeight(),imageArray.getWidth());

		parsedMap.createParsedMap(map);

		/*				
		int stop = wallsInfos.size()/8; 
		for (int i = 0; i<stop; i++){
			System.out.println("test :  " + wallsInfos.get(7));

			walls.add(new Wall(wallsInfos.get(8*i),  wallsInfos.get(8*i+1), wallsInfos.get(8*i+2),wallsInfos.get(8*i+3),wallsInfos.get(8*i+4),wallsInfos.get(8*i+5),wallsInfos.get(8*i+6),wallsInfos.get(8*i+7)));
		}
			*/	
		//new RayTracing(numRays, numMaxReflexion, numMaxRefraction, emettorPosition,map, powerInit);
		
		System.out.println("Fin de main()");
	}

}
