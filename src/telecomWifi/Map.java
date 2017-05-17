package telecomWifi;

import java.util.ArrayList;

public class Map {
	
	private int[][] mapMatrix;
	private int height;
	private int width;
	
	Map(int height, int width, int[][] matrix) {
		this.mapMatrix = matrix;
		this.height = height;
		this.width = width;
	}

	private boolean isInMap (int x, int y){
		boolean response = true;
		if (!(x<this.width && x>-1 && y<this.height && y >-1)){
			response = false;
		}
		//System.out.println("booleen : " + response + " valeur de x : " + x );
		//System.out.println("booleen : " + response + " valeur de y : " + y );
		return response;
	}
	
	public ArrayList<ArrayList<Integer>> getWallsInfo(){

		ArrayList<ArrayList<Integer>> finalList = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> typeWalls = new ArrayList<Integer>();
		ArrayList<Integer> rightSide= new ArrayList<Integer>();
		ArrayList<Integer> leftSide = new ArrayList<Integer>();
		ArrayList<Integer> leftSideList = new ArrayList<Integer>();
		ArrayList<Integer> rightSideList = new ArrayList<Integer>();
		ArrayList<Integer> outsideList = new ArrayList<Integer>();
		ArrayList<Integer> insideList = new ArrayList<Integer>();

		finalList.add(typeWalls);
		
		int typeWall;
		boolean isLeftOrRight;			// true is right, false is left
		int indexX1; int indexY1;		//Last wall index (!=-1) on the same line, right side
		int[] index2;

		
		for (int indexY0=0; indexY0<this.height; indexY0++){
			for (int indexX0=0; indexX0<this.width; indexX0++){
				
				if (this.mapMatrix[indexX0][indexY0] != -1){
					
					if (leftSide.size()!=0){	
						boolean runningList = true; //y apasue unleft sde!!!!!!!!
						for (int i = 0; i<leftSide.size()/2;i++){
							if (indexX0 == leftSide.get(2*i) && indexY0 == leftSide.get(2*i+1)){
								runningList = false;
								while (isInMap(indexX0,indexY0) && this.mapMatrix[indexX0][indexY0]!=-1){
									indexX0++;
								}
							}
						}	
						if (runningList == true){
							
								typeWall = mapMatrix[indexX0][indexY0];;
								indexX1 = getIndexX1(indexX0, indexY0);
								indexY1 = indexY0;
								index2 = getIndex2(indexX0, indexX1, indexY0);
								isLeftOrRight = returnDirection(indexX0, index2[0]);
								
								if (isLeftOrRight == true){ //Right
									leftSideList = rainDropInside(indexX0, indexY0, isLeftOrRight, insideList);
									rightSideList = rainDropOutside(indexX1,indexY1, isLeftOrRight,outsideList);
									
								} else { //Left
									rightSideList = rainDropInside(indexX1, indexY1, isLeftOrRight, insideList);
									leftSideList = rainDropOutside(indexX0,indexY0,isLeftOrRight,outsideList);
								}
								
								finalList.add(leftSideList);
								finalList.add(rightSideList);
								
								finalList.get(0).add(typeWall);
								for (int k =0; k<leftSideList.size();k++){
									leftSide.add(leftSideList.get(k));
								}
								for (int j =0; j<rightSideList.size();j++){
									rightSide.add(rightSideList.get(j));
								}
								
								while(isInMap(indexX0,indexY0) && this.mapMatrix[indexX0][indexY0] !=-1){
									// Condition d'arrêt pour que lorsqu'il a commencé à analyse un mur, il n'analyse pas le 1 qui est juste à côté,
									// il faut qu'il reparte au prochain sol sur sa droite.
									indexX0++;
								}
							}
						} else {
						
						typeWall = returnID(indexX0, indexY0);
						indexX1 = getIndexX1(indexX0, indexY0);
						indexY1 = indexY0;	
						index2 = getIndex2(indexX0, indexX1, indexY0);
						isLeftOrRight = returnDirection(indexX0, index2[0]);


						if (isLeftOrRight == true){
							typeWalls.add(typeWall);
							leftSideList = rainDropInside(indexX0, indexY0, isLeftOrRight, insideList);
							rightSideList = rainDropOutside(indexX1,indexY1, isLeftOrRight,outsideList);

							for (int k =0; k<leftSideList.size();k++){
								leftSide.add(leftSideList.get(k));
							}
							
							for (int j =0; j<rightSideList.size();j++){
								rightSide.add(rightSideList.get(j));
							}
							
						} else {
							typeWalls.add(typeWall);
							rightSideList = rainDropInside(indexX1, indexY1, isLeftOrRight, insideList);
							leftSideList = rainDropOutside(indexX0,indexY0,isLeftOrRight,outsideList);

							for (int k =0; k<leftSideList.size();k++){
								leftSide.add(leftSideList.get(k));
							}
							for (int j =0; j<rightSideList.size();j++){
								rightSide.add(rightSideList.get(j));
							}
							
						}
						
						while(isInMap(indexX0,indexY0) && this.mapMatrix[indexX0][indexY0] !=-1){
							// Condition d'arrêt pour que lorsqu'il a commencé à analyse un mur, il n'analyse pas le 1 qui est juste à côté,
							// il faut qu'il reparte au prochain sol sur sa droite.
							indexX0++;
						}
					}
				}
			}
		}
		finalList.add(typeWalls);
		finalList.add(rightSide);
		finalList.add(leftSide);
		return finalList;
	}
	
	private ArrayList<Integer> rainDropOutside(int xCurrent, int yCurrent, boolean direction, ArrayList<Integer> outsideList){
		int dir;
		
		if (direction == true){ //RIGHT
			dir = 1;
		} else {	//LEFT
			dir = -1;
		}
		
		boolean running1;
		boolean running2;

		while (isInMap(xCurrent+dir,yCurrent) && isInMap(xCurrent,yCurrent+1) && !(this.mapMatrix[xCurrent +dir][yCurrent] == -1 && this.mapMatrix[xCurrent][yCurrent+1] == -1)){
			running1 = true;

			while(isInMap(xCurrent+dir, yCurrent) && running1 == true){
				if (this.mapMatrix[xCurrent+dir][yCurrent] == -1){
					outsideList.add(xCurrent);
					outsideList.add(yCurrent);
					yCurrent++;
				} else {
					running1 = false;
				}
			}
			
			running2 = true;
			
			while (isInMap(xCurrent+dir, yCurrent) && running2 == true){
				if (this.mapMatrix[xCurrent+dir][yCurrent] == -1){
					outsideList.add(xCurrent);
					outsideList.add(yCurrent);
					running2 = false;
				}else {
					xCurrent = xCurrent+dir;
				}
			}
		}
		return outsideList;
	}
	
	private ArrayList<Integer> rainDropInside(int xCurrent, int yCurrent, boolean direction,ArrayList<Integer> insideList){
		int dir;
		
		if (direction == true){ //RIGHT
			dir = 1;
		} else {	//LEFT
			dir = -1;
		}
		
		boolean running1;
		boolean running2;
		
		while(isInMap(xCurrent+dir,yCurrent) && isInMap(xCurrent,yCurrent+1) && !(this.mapMatrix[xCurrent +dir][yCurrent] == -1 && this.mapMatrix[xCurrent][yCurrent+1] == -1)){
			running1 = true;
			
			while(isInMap(xCurrent, yCurrent+1) && running1 == true){
				if (this.mapMatrix[xCurrent][yCurrent+1] != -1){
					insideList.add(xCurrent);
					insideList.add(yCurrent);
					yCurrent++;
				} else {
					insideList.add(xCurrent);
					insideList.add(yCurrent);
					running1 = false;
				}
				
			}
			
			running2 = true;
			
			while(isInMap(xCurrent, yCurrent+1) && running2 == true){
				if (this.mapMatrix[xCurrent][yCurrent+1] == -1){
					xCurrent+=dir;
				} else {
					running2 = false;
					yCurrent++;
				}
			}
		}	
		return insideList;
	}
	

	private boolean returnDirection(int indexX0, int indexX2){
		//find the orientation of the wall
		boolean direction = true;//Right
		if (indexX0 == indexX2){
			direction = false;//Left
		}
		return direction;
	}
	
	private int[] getIndex2(int indexX0, int indexX1, int indexY0){
		//find first point of the next rectangle
		int indexYcurrent = indexY0+1;
		int[] index2 = new int[2];
		while (isInMap(indexX0,indexYcurrent) && this.mapMatrix[indexX0][indexYcurrent] !=-1 && isInMap(indexX1,indexYcurrent) && this.mapMatrix[indexX1][indexYcurrent]!=-1){
			indexYcurrent++;
		}
		if (isInMap(indexX0, indexYcurrent)){
			if (this.mapMatrix[indexX0][indexYcurrent] == -1){
				index2[0] = indexX1;
				index2[1] = indexYcurrent;
				//return index2;
			}
		}
		if (isInMap(indexX1,indexYcurrent)){
			if (this.mapMatrix[indexX1][indexYcurrent] == -1){
				index2[0] = indexX0;
				index2[1] = indexYcurrent;
				//return index2;
			}
		}
		return index2;
	}
	
	
	private int getIndexX1(int indexX0, int indexY0){
		//get last wall pointon the line 
		int indexXcurrent = indexX0+1;
		while (isInMap(indexXcurrent,indexY0) && this.mapMatrix[indexXcurrent][indexY0]!=-1){
			indexXcurrent++;
		}
		return indexXcurrent;
	}
	
	private int returnID(int indexX0, int indexY0){
		int returnID = this.mapMatrix[indexX0][indexY0];
		return returnID;
	}
	
	
	
	
	
	public int[][] getmapMatrix() {
		return mapMatrix;
	}

	public void setmapMatrix(int[][] mapMatrix) {
		this.mapMatrix = mapMatrix;
	}
	
	public int getElement(int X, int Y) {
		return mapMatrix[X][Y];
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
		int startType = mapMatrix[positionInit[0]][positionInit[1]];
		int nextX = positionInit[0]++;
		int nextY = positionInit[1] + (int) (angCoeff*iter);
		while (nextX<width && nextY<height && mapMatrix[nextX][nextY]==startType) {
				nextX++;
				nextY = nextY + (int) (angCoeff*iter);
		}
		result[0] = nextX;
		result[1] = nextY;
		return result;
	}

	
}
