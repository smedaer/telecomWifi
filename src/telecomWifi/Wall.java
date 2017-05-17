package telecomWifi;

import java.util.ArrayList;

public class Wall {
	private double permittivity;
	private double conductivity;
	private float thickness;
	private double RE_constProp;
	private double IM_constProp;
	// RED IS BRICK
	// GREEN IS BETON
	// BLUE IS CLOISON
	private int repetitions;
	private int length;
	private int spacing;
	private int[] upponExtrem;
	private int[] bottomExtrem;
	private double normale;
	
	public Wall(int typeWall, int repetitions, int length, int spacing, int upponExtremX, int upponExtremY, int bottomExtremX,int bottomExtremY){
		if (typeWall==1){
			this.permittivity = 4.6;
			this.conductivity = 0.02;	
			this.RE_constProp = 17.853;
			this.IM_constProp = -0.7795;
		}
		if (typeWall==2){
			this.permittivity = 5;
			this.conductivity = 0.014;	
			this.RE_constProp = 63.574;
			this.IM_constProp = -2.41;
		}
		if (typeWall==3){
			this.permittivity = 2.25;
			this.conductivity = 0.04;	
			this.RE_constProp = 42.81;
			this.IM_constProp = -4.024;
		}
		this.repetitions = repetitions;
		this.length = length;
		this.spacing = spacing;
		this.upponExtrem[0] = upponExtremX;
		this.upponExtrem[1] = upponExtremY;
		this.bottomExtrem[0] = bottomExtremX;
		this.bottomExtrem[1] = bottomExtremY;
		this.thickness = 3;
	}
	
	public double getPermittivity() {
		return permittivity;
	}
	public void setPermittivity(double permittivity) {
		this.permittivity = permittivity;
	}
	public double getConductivity() {
		return conductivity;
	}
	public void setConductivity(double conductivity) {
		this.conductivity = conductivity;
	}
	public float getThickness() {
		return thickness;
	}
	public void setThickness(float thickness) {
		this.thickness = thickness;
	}
	public double getRE_constProp() {
		return RE_constProp;
	}
	public void setRE_constProp(float rE_constProp) {
		RE_constProp = rE_constProp;
	}
	public double getIM_contProp() {
		return IM_constProp;
	}
	public void setIM_contProp(float iM_contProp) {
		IM_constProp = iM_contProp;
	}
	
	
	

}