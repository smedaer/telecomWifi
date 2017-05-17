package telecomWifi;

import java.util.ArrayList;

public class RayTracing { //solve the map
	
	private Map map;
	private ArrayList<Ray> rays = new ArrayList<Ray>();
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private Ray Ray;
	private int height;
	private int width;
	private int[] emettorPosition;
	private Emettor emettor;


	public RayTracing(int numRays, int numMaxReflexion, int numMaxRefraction, int[] emettorPosition, int[][] map, int powerInit) {		
	
		emettor = new Emettor(emettorPosition, powerInit);
		
		setRays(numMaxReflexion, numMaxRefraction, emettorPosition, powerInit);
	}
	
	
	
	private void setRays(int numMaxReflection, int numMaxRefraction, int[] emettorPosition, int power){
		
		int i = 0;
		float direction = 360/rays.size();
		while (i< rays.size()){
			Ray ray = new Ray(7.0,direction*i, emettorPosition, numMaxReflection, numMaxRefraction);
			rays.add(Ray);
		}
	}
	
	public void rayTrip() {
		int indexRay = 0; //indexRay to know which ray in rays has been checked
		int newPosition[];
		//hypothèse que d'office une réflexion, transmission , il faudrait checker avant si c'est pas une diffraction
		while (indexRay < rays.size()) {
			if (rays.get(indexRay).getNbrDiffraction() != 0 && rays.get(indexRay).getNbrReflection() != 0) {
				newPosition = map.nextStop(rays.get(indexRay).getDirection(), rays.get(indexRay).getPositionInit());
				rayThroughWall (indexRay, map.getElement(newPosition[0], newPosition[1]), newPosition);
			}
			indexRay++;
		}
	}
	
	private double getReflectedAmplitude(int indexRay, int indexWall){
		return 0.0;
		//return rays.get(indexRay).getTotalReflectCoeff(walls.get(indexWall).getRE_constProp(), walls.get(indexWall).getIM_contProp(), walls.get(indexWall).getThickness(), nbre_onde_beta, rays.get(indexRay).getA, perm, z1, z2, angleFinal)*rays.get(indexRay).getAmplitudePropagation()
	}
	
	private void rayThroughWall (int indexRay, int indexWall, int[] wallPosition) {
			rays.get(indexRay).setNbrReflection(rays.get(indexRay).getNbrReflection()-1);
			rays.get(indexRay).setPositionFinal(wallPosition);
			rays.get(indexRay).setAmplitudePropagation(map.distance(rays.get(indexRay).getPositionInit(), rays.get(indexRay).getPositionFinal()));
			
			
			//Ray rayReflected = new Ray(rays.get(indexRay).getAmplitudePropagation(), rays.get(indexRay).getReflectAngle(), rays.get(indexRay).getPositionFinal());
			
			//rays.add(rayReflected);
			
			//Ray rayTransmitted = new Ray(rays.get(indexRay).getTransAmplitude(), rays.get(indexRay).getTransAngle(), walls.get(typeWall).getPositionTransRay());
			//rays.add(rayTransmitted);
	}
	

}
