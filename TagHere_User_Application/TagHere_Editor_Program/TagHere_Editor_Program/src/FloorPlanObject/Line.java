package FloorPlanObject;

import java.awt.Color;
import java.awt.Point;

/**
 * Line Object
 * Have start position, end position, thickness, type and color
 * @author DongKyu
 *
 */

public class Line extends FPObject {
	
	/** Point for start position */
	private Point startPosition;
	/** Point for end position */
	private Point endPosition;
	/** Integer for thickness */
	private int thickness;
	/** LineType for line type */
	private LineType type;
	/** Color for line color */
	private Color color;
	
	public Line() { }
	

	public Line(int startPosX, int startPosY) {
		setStartPosition(new Point(startPosX, startPosY));
	}
	
	public Line(Point startPos, Point endPos) {
		setStartPosition(startPos);
		setEndPosition(endPos);
	}
	
	public Point getStartPosition() {
		return startPosition;
	}
	
	public void setStartPosition(Point startPosition) {
		this.startPosition = startPosition;
	}
	
	public Point getEndPosition() {
		return endPosition;
	}
	
	public void setEndPosition(Point endPosition) {
		this.endPosition = endPosition;
	}
	
	public int getThickness() {
		return thickness;
	}
	
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}
	
	public LineType getLineType() {
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
	
	@Override
	public ObjectType getType() {
		return ObjectType.LINE;
	}
}
