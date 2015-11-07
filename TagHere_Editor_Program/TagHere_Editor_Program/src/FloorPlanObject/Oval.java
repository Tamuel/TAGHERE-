package FloorPlanObject;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Oval Object
 * Have position, radius, thickness, type and color
 * @author DongKyu
 *
 */

public class Oval extends FPObject {
	
	/** Dimension for position */
	private Dimension position;
	/** Integer for oval radius */
	private int radius;
	/** Integer for thisckness */
	private int thickness;
	/** LineType for line type */
	private LineType type;
	/** Color for line color */
	private Color color;
	
	
	public Dimension getPosition() {
		return position;
	}
	public void setPosition(Dimension position) {
		this.position = position;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public int getThickness() {
		return thickness;
	}
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}
	public LineType getType() {
		return type;
	}
	public void setType(LineType type) {
		this.type = type;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
