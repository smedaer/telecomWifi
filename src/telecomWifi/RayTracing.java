package telecomWifi;

import java.util.ArrayList;

public class RayTracing { //solve the map
	
	private Map map = new Map(10,10);
	private ArrayList<Ray> rays = new ArrayList<Ray>();
	private ArrayList<Wall> walls = new ArrayList<Wall>();

	public void rayTrip() {
		int index = 0; //index to know which ray in rays has been checked
		int newPosition[];
		//hypothèse que d'office une réflexion, transmission , il faudrait checker avant si c'est pas une diffraction
		while (index < rays.size()) {
			if (rays.get(index).getNbrDiffraction()<2 && rays.get(index).getNbrReflection()<3) {
				newPosition = map.nextStop(rays.get(index).getDirection(), rays.get(index).getPositionInit());
				rayThroughWall (index, map.getElement(newPosition[0], newPosition[1]), newPosition);
			}
			index++;
		}
	}
	
	private void rayThroughWall (int index, int typeWall, int[] newPosition) {
			rays.get(index).setNbrReflection(rays.get(index).getNbrReflection()+1);
			rays.get(index).setPositionFinal(newPosition);
			rays.get(index).setAmplitudePropagation(map.distance(rays.get(index).getPositionInit(), rays.get(index).getPositionFinal()));
			Ray rayReflected = new Ray(rays.get(index).getReflectAmplitude(), rays.get(index).getReflectAngle(), rays.get(index).getPositionFinal());
			rays.add(rayReflected);
			Ray rayTransmitted = new Ray(rays.get(index).getTransAmplitude(), rays.get(index).getTransAngle(), walls.get(typeWall).getPositionTransRay());
			rays.add(rayTransmitted);
	}

}
