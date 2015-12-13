package ar.edu.utn.model.prototype;

public class Color {
	private String color;

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	public Color clone(){
		Color colorClone = new Color();
		colorClone.setColor(this.getColor());
		return colorClone;
	}
	
}
