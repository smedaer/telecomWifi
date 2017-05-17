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
		ArrayList<Integer> allLeftSide = new ArrayList<Integer>();
		ArrayList<Integer> outsideList = new ArrayList<Integer>();
		ArrayList<Integer> insideList = new ArrayList<Integer>();
		ArrayList<Integer> leftSideList = new ArrayList<Integer>();
		ArrayList<Integer> rightSideList = new ArrayList<Integer>();

		finalList.add(typeWalls);
		int iter = 0;
		int typeWall;	
		boolean isLeftOrRight;			// true is right, false is left
		int indexX1; int indexY1;		//Last wall index (!=-1) on the same line, right side
		int[] index2;

		boolean runningList;
		for (int indexY0=0; indexY0<this.height; indexY0++){
			for (int indexX0=0; indexX0<this.width; indexX0++){
				//Scanne toute la matrice
				
				if (this.mapMatrix[indexX0][indexY0] != -1){
					//Ne slectionne que les valeurs où il y a un mur (sol = -1)
				
					runningList = true;
					if (!allLeftSide.isEmpty()){
						//vérifie si la coordonnée actuelle n'existe pas deja dans une liste de leftside
					
						for (int i = 0; i<allLeftSide.size()/2;i++){
							//System.out.println("Je suis à la position ("+indexX0+","+indexY0+") avant le if");
							if (indexX0 == allLeftSide.get(2*i) && indexY0 == allLeftSide.get(2*i+1)){
								//System.out.println("Je suis à la position ("+indexX0+","+indexY0+") dans le if de la leftSide");

								//System.out.println("Je suis à la position ("+indexX0+","+indexY0+") et j'ai rencontré un mur de la LeftSide");
								runningList = false;
								while (isInMap(indexX0,indexY0) && this.mapMatrix[indexX0][indexY0]!=-1){
									indexX0++;
								}
								//System.out.println("RunningList = " + runningList);
							}
						}
						//System.out.println("Je suis à la position ("+indexX0+","+indexY0+") et je n'ai rencontré aucun mur de la leftSide -> Nouveau mur ! :");	
					}
					//System.out.println("Je suis à la position ("+indexX0+","+indexY0+") et je vais construire les listes d'un mur");
					
					if (runningList == true){
						leftSideList.clear();
						rightSideList.clear();
						insideList.clear();
						outsideList.clear();
						
						typeWall = mapMatrix[indexX0][indexY0];
						indexX1 = getIndexX1(indexX0, indexY0);
						indexY1 = indexY0;
						index2 = getIndex2(indexX0, indexX1, indexY0);
						isLeftOrRight = returnDirection(indexX0, index2[0]);
						
						if (isLeftOrRight == true){ //Right
							leftSideList = rainDropInside(indexX0, indexY0, isLeftOrRight, insideList);
							rightSideList = rainDropOutside(indexX1,indexY1, isLeftOrRight,outsideList);
							//System.out.println("Je suis à la position ("+indexX0+","+indexY0+") à un mur droite j'ai construit ses listes");
							
						} else { //Left
							rightSideList = rainDropInside(indexX1, indexY1, isLeftOrRight, insideList);
							leftSideList = rainDropOutside(indexX0,indexY0,isLeftOrRight,outsideList);
							//System.out.println("Je suis à la position ("+indexX0+","+indexY0+") à un mur gauche j'ai construit ses listes");
						}
						

						finalList.get(0).add(typeWall);
						finalList.add(leftSideList);
						finalList.add(rightSideList);
						
						for (int k =0; k<leftSideList.size();k++){
							allLeftSide.add(leftSideList.get(k));
						}
						for (int j=0; j<leftSideList.size()/2;j++){
							System.out.println("Element de left sideList : ("+leftSideList.get(2*j)+","+leftSideList.get(2*j+1)+")");
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
		return finalList;
	}
	
	private ArrayList<Integer> rainDropOutside(int xInit, int yInit, boolean direction, ArrayList<Integer> outsideList){
		// get the points list of the outside of the wall
		int dir;
		outsideList.add(xInit);
		outsideList.add(yInit);
		int xCurrent = xInit;
		int yCurrent = yInit+1;
		
		if (direction == true){ //RIGHT
			dir = 1;
		} else {	//LEFT
			dir = -1;
		}
		int iter = 0;
		while (isInMap(xCurrent+dir,yCurrent-1) && isInMap(xCurrent,yCurrent) && this.mapMatrix[xCurrent+dir][yCurrent-1] != -1 && this.mapMatrix[xCurrent][yCurrent] != -1){
			//System.out.println("ciondition d arret iteration outside"+ iter);
			iter++;
			while(isInMap(xCurrent+dir, yCurrent) && this.mapMatrix[xCurrent+dir][yCurrent] == -1){
				outsideList.add(xCurrent);
				outsideList.add(yCurrent);
				yCurrent++;
			}
			
			while (isInMap(xCurrent+dir, yCurrent) && this.mapMatrix[xCurrent+dir][yCurrent] != -1){
				xCurrent+=dir;
			}
			outsideList.add(xCurrent);
			outsideList.add(yCurrent);
			yCurrent++;
		}
		return outsideList;
	}
	
	private ArrayList<Integer> rainDropInside(int xInit, int yInit, boolean direction,ArrayList<Integer> insideList){
		// get the points list of the inside of the wall
		int dir;
		insideList.add(xInit);
		insideList.add(yInit);
		int xCurrent = xInit;
		int yCurrent = yInit+1;
		
		if (direction == true){ //RIGHT
			dir = 1;
		} else {	//LEFT
			dir = -1;
		}
		int iter = 0;
		int iter1 = 0;
		//System.out.println("nombre defois que il rentre: " + iter1);
		iter1++;
		while(isInMap(xCurrent+dir,yCurrent-1) && isInMap(xCurrent,yCurrent) && this.mapMatrix[xCurrent+dir][yCurrent-1] != -1 && this.mapMatrix[xCurrent][yCurrent] != -1){
			//System.out.println("condition d arret iteration inside"+ iter);
			iter++;
			while(isInMap(xCurrent, yCurrent) && this.mapMatrix[xCurrent][yCurrent] != -1){
				insideList.add(xCurrent);
				insideList.add(yCurrent);
				yCurrent++;
			}

			while(isInMap(xCurrent, yCurrent) && this.mapMatrix[xCurrent][yCurrent] == -1){
				xCurrent+=dir;
			}
			insideList.add(xCurrent);
			insideList.add(yCurrent);
			yCurrent++;
		}	
		return insideList;
	}
	

	private boolean returnDirection(int indexX0, int indexX2){
		//find the orientation of the wall
		boolean direction = false;//Left
		if (indexX0 != indexX2){
			direction = true;//right
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
