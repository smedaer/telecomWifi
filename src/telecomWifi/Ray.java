package telecomWifi;

import java.util.*;  

public class Ray {
	
	private int nbrReflection;
	private int nbrDiffraction;
	private double amplitudeInit;
	private double amplitudeFinal;
	private double direction;
	private int[] positionInit;
	private int[] positionFinal;
	private double frequency;
	private double mu;
	private double airPermittivity;
	
	Ray(double amplitudeInit, double direction, int[] positionInit) {
		this.nbrReflection = 0; 
		this.nbrDiffraction = 0;
		this.amplitudeInit = amplitudeInit;
		this.direction = direction;
		this.positionInit = positionInit;
	}

	public int[] getPositionFinal() {
		return positionFinal;
	}

	public int[] getPositionInit() {
		return positionInit;
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
	
	private double getReflectCoeff(double z1, double z2, double angleInit, double angleFinal) {
		double reflectCoeff = ((z2*Math.cos(angleInit))-(z1*Math.cos(angleFinal)))/((z2*Math.cos(angleInit))-(z1*Math.cos(angleFinal)));
		return reflectCoeff;
	}
	
	private double getTotalReflectCoeff() {
		double totalReflectCoeff = 0;
		return totalReflectCoeff;
		
	}
	
	private double getTotalTransCoeff () {
		double totalTransCoeff = 0;
		return totalTransCoeff;
	}
	
	public double getReflectAmplitude () {
		double reflectAmplitude = this.amplitudeFinal*getTotalReflectCoeff();
		return reflectAmplitude;
	}
	
	public double getTransAmplitude () {
		double transAmplitude = this.amplitudeFinal*getTotalTransCoeff();
		return transAmplitude;
	}
	
	public double getReflectAngle () {
		double reflectAngle = 0;
		return reflectAngle;
	}
	
	public double getTransAngle () {
		double transAngle = 0;
		return transAngle;
	}
	
	public void setAmplitudePropagation(double distance) {
		this.amplitudeFinal = this.amplitudeInit/distance;
	}
	
}
