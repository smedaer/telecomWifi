package telecomWifi;

import java.util.*;  

public class Ray {
	
	private int nbrReflection;
	private int nbrDiffraction;
	private double amplitudeInit;
	private double amplitudeFinal;
	private float direction;
	private int[] positionInit;
	private int[] positionFinal;
	private double frequency;
	private double mu;
	private double airPermittivity;
	
	Ray(double amplitudeInit, float direction, int[] positionInit) {
		this.nbrReflection = 0; 
		this.nbrDiffraction = 0;
		this.amplitudeInit = amplitudeInit;
		this.direction = direction;
		this.positionInit = positionInit;
	}
	
	Ray(double amplitudeInit, float direction, int[] positionInit, int nbreReflection, int nbreRefraction) {
		this.nbrReflection = nbreReflection; 
		this.nbrDiffraction = nbreRefraction;
		this.amplitudeInit = amplitudeInit;
		this.direction = direction;
		this.positionInit = positionInit;
	}

	public int[] getPositionFinal() {
		return positionFinal;
	}

	public void setAmplitudeInit(double amplitudeInit){
		this.amplitudeInit = amplitudeInit;
	}
	
	public int[] getPositionInit() {
		return positionInit;
	}
	
	public void setPositionInit(int[] positionInit){
		this.positionInit = positionInit;
	}

	public void setPositionFinal(int[] positionFinal) {
		this.positionFinal = positionFinal;
	}
	
	public double getDirection() {
		return direction;
	}

	public int getNbrReflection() {
		return nbrReflection;
	}

	public int getNbrDiffraction() {
		return nbrDiffraction;
	}

	public void setNbrReflection(int nbrReflection) {
		this.nbrReflection = nbrReflection;
	}

	public void setNbrDiffraction(int nbrDiffraction) {
		this.nbrDiffraction = nbrDiffraction;
	}

	public double getDescartesAngle (double perm , double angleInit) {
		double refractAngle = Math.asin(Math.sqrt(this.airPermittivity/perm)*Math.sin(angleInit));
		return refractAngle;
	}
	
	
	private double getFresnelReflectCoeff(double z1, double z2, double angleInit, double angleFinal) {
		double reflectCoeff = ((z2*Math.cos(angleInit))-(z1*Math.cos(angleFinal)))/((z2*Math.cos(angleInit))-(z1*Math.cos(angleFinal)));
		return reflectCoeff;
	}
	
	private double getWallRayDistance(double wallThickness, double angleInit, double perm){
			return 	wallThickness/Math.cos(getDescartesAngle(perm, angleInit));
	}

	
	public double getTotalReflectCoeff(double RE_const_prop, double IM_const_prop, double wallThickness,
		    double nbre_onde_beta, double angleInit, double perm, double z1, double z2,
		    double angleFinal) {
		
			
			double s = getWallRayDistance(wallThickness, angleInit,perm);
			double A = -2*s*RE_const_prop;
			double B = -2*s*IM_const_prop + 2*nbre_onde_beta*s*Math.pow((Math.sin(angleInit)),2)*Math.sqrt(this.airPermittivity/perm);
			double Gamma = getFresnelReflectCoeff(z1,z2,angleInit,angleFinal);
			double denominateur = (1-2*Math.pow(Gamma,2)*Math.exp(A)*Math.cos(B)+Math.pow(Gamma,4)*Math.exp(2*A));
			double C = (Math.cos(B)-Math.pow(Gamma,2)*Math.exp(A)*Math.cos(2*B))/denominateur;
			double D = (Math.sin(B)-Math.pow(Gamma,2)*Math.exp(A)*Math.sin(2*B))/denominateur;
			 		
					
			double totalReflectCoeff = Math.sqrt(1+(1-Math.pow(Gamma,2))*(Math.exp(A)*(2*C+Math.exp(2*A)*(Math.pow(C,2) + Math.pow(D,2)))));
			return Gamma*totalReflectCoeff;		
	}
	
	
	public double getTotalTransCoeff (double RE_const_prop, double IM_const_prop, double wallThickness,
								    double nbre_onde_beta, double angleInit, double perm, double z1, double z2,
								   double angleFinal) {
			
		double s = getWallRayDistance(wallThickness, angleInit, perm);
		double F = -RE_const_prop * s;
		double G = -s*IM_const_prop;
		double H = 2*nbre_onde_beta*s*Math.pow((Math.sin(angleInit)),2)*Math.sqrt(this.airPermittivity/perm)+2*G;
		double Gamma = getFresnelReflectCoeff(z1,z2,angleInit,angleFinal);
		double denominateur = 1-2*Math.pow(Gamma,2)*Math.exp(2*F)*Math.cos(H) + Math.pow(Gamma,4)*Math.exp(4*F);
		double I = (Math.cos(G)-Math.cos(G-H)*Math.pow(Gamma,2)*Math.exp(2*F))/denominateur;
		double J = (Math.sin(G)-Math.sin(G-H))/denominateur;
		
		double totalTransCoeff =Math.exp(F)*(1-Math.pow(Gamma,2))*Math.sqrt(Math.pow(I,2)+Math.pow(J,2)) ;
		
		return totalTransCoeff;
	}
	
	public double getReflectAngle () {
		double reflectAngle = 0;
		return reflectAngle;
	}
	
	public double getAngleInit(){
		return 0.0;
	}
	
	public double getTransAngle () {
		double transAngle = 0;
		return transAngle;
	}
	
	public void setAmplitudePropagation(double distance) {
		this.amplitudeFinal = this.amplitudeInit/distance;
	}
	
	public void setDirection (float direction){
		this.direction=direction;
	}
	
}
